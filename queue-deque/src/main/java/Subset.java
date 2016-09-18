import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        RandomizedQueue<String> randomQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            randomQueue.enqueue(string);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(randomQueue.dequeue());
        }
    }
}
