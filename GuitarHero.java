/* *****************************************************************************
 *  Name: Hasit
 *  NetID:
 *  Precept:
 *
 *  Partner Name:
 *  Partner NetID:
 *  Partner Precept:
 *
 *  Description: This program models a guitar using the GuitarString class
 *  and supports a total of 37 notes on the chromatic scale from 110 Hz to 880 Hz.
 *  The program plays the guitar in real time, using the keyboard to input notes.
 **************************************************************************** */

public class GuitarHero {
    public static void main(String[] args) {
        int number = 37;
        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


        GuitarString[] myStrings = new GuitarString[number];

        for (int i = 0; i < number; i++) {
            double frequency = 440 * Math.pow(2, ((i - 24) / 12.0));

            myStrings[i] = new GuitarString(frequency);
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();


                if (keyboardString.indexOf(key) >= 0) {
                    (myStrings[keyboardString.indexOf(key)]).pluck();
                }

            }

            // compute the superposition of the samples
            double sample = 0;
            for (int i = 0; i < number; i++) {
                sample = sample + (myStrings[i]).sample();
            }


            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < number; i++) {
                (myStrings[i]).tic();
            }
        }

    }
}
