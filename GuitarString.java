/* *****************************************************************************
 *  Name: Hasit
 *  NetID:
 *  Precept:
 *
 *  Partner Name:
 *  Partner NetID:
 *  Partner Precept:
 *
 *  Description: This class models a vibrating guitar string using the RingBuffer
 * variable class.
 *
 *
 **************************************************************************** */

public class GuitarString {

    private RingBuffer guitarString; // items in the buffer

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        double samplingRate = 44100;
        int n = (int) Math.ceil(samplingRate / frequency);
        guitarString = new RingBuffer(n);

        for (int i = 0; i < guitarString.capacity(); i++) {
            guitarString.enqueue(0);
        }

    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        int counter = 0;
        try {
            while (true) {
                init[counter] = init[counter];
                counter += 1;
            }

        }
        catch (ArrayIndexOutOfBoundsException e) {
            guitarString = new RingBuffer(counter);
            for (int i = 0; i < counter; i++) {
                guitarString.enqueue(init[i]);
            }
        }


    }

    // returns the number of samples in the ring buffer
    public int length() {
        return guitarString.size();
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        for (int i = 0; i < guitarString.size(); i++) {
            guitarString.dequeue();
            guitarString.enqueue(StdRandom.uniform(-0.5, 0.5));
        }
    }

    // advances the Karplus-Strong simulation one time step
    public void tic() {
        double decayFactor = 0.996;
        double replaced = guitarString.dequeue();
        guitarString.enqueue(((replaced + guitarString.peek()) / 2) * decayFactor);
    }

    // returns the current sample
    public double sample() {
        return guitarString.peek();
    }


    // tests and calls every constructor and instance method in this class
    public static void main(String[] args) {

        double[] myArray = { 0.0, 0.0, 0.0, 0.0, 0.0 };
        GuitarString testString = new GuitarString(myArray);
        StdOut.println(testString.length());
        StdOut.println(testString.sample());
        testString.pluck();
        StdOut.println(testString.length());
        StdOut.println(testString.sample());

        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testingString = new GuitarString(samples);
        int m = 25; // 25 tics
        for (int i = 0; i < m; i++) {
            double sample = testingString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testingString.tic();
        }


    }

}
