import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        int j = 1;
        while (k > 0 && !StdIn.isEmpty()) {
            if (queue.size() >= k) {
                if (StdRandom.uniform(j) < k) {
                    queue.dequeue();
                    queue.enqueue(StdIn.readString());
                }
                else {
                    StdIn.readString();
                }
            }
            else {
                queue.enqueue(StdIn.readString());
            }
            ++j;
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
