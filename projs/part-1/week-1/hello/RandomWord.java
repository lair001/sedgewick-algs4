/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {

        String champion = "";
        int i = 1;

        while (!StdIn.isEmpty()) {
            String curr = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / i)) {
                champion = curr;
            }

            i++;
        }

        StdOut.println(champion);
    }
}
