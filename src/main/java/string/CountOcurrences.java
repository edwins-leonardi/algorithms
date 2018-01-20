package string;

import edu.princeton.cs.algs4.Alphabet;

public class CountOcurrences {
    public static void main(String[] args) {
        String term = "ABRACADABRA!";
        Alphabet alphabet = new Alphabet();
        int R = alphabet.radix();
        int[] count = new int[R];
        for (int i = 0; i < term.length(); i++)
            count[alphabet.toIndex(term.charAt(i))]++;

        for (int i = 0; i < R; i++) {
            if (count[i] > 0)
                System.out.println(alphabet.toChar(i) + " = " + count[i]);
        }

    }
}
