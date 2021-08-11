/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Partner Name:    Ada Lovelace
 *  Partner NetID:   alovelace
 *  Partner Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

public class GuitarHero2 {
    public static void main(String[] args) {
        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);


        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if (key == 'a') {
                    stringA.pluck();
                }
                if (key == 'c') {
                    stringC.pluck();
                }
            }

            // compute the superposition of the samples
            double sample = stringA.sample() + stringC.sample();

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            stringA.tic();
            stringC.tic();
        }
    }
}
