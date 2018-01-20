package string.sort;

public class KeyIndexCounting {

    // a
    // b
    // c
    // d
    // e
    // f
    // g
    // h

    private static final int R = 8;

    public static void sort(char[] a) {
        int[] frequency = new int[R + 1];
        char[] aux = new char[a.length];

        for (int i = 0; i < a.length; i++)
            frequency[a[i] - 64]++;

        for (int i = 0; i < R; i++)
            frequency[i + 1] += frequency[i];

        for (int i = 0; i < a.length; i++)
            aux[frequency[a[i] - 65]++] = a[i];

        for (char c : aux) {
            System.out.print(c + "\n");
        }
    }

    public static void main(String[] args) {
        sort(new char[] { 'A', 'B', 'C', 'A', 'C', 'D', 'F', 'B', 'A', 'G', 'F', 'A', 'H', 'E', 'C', 'A', 'H', 'F', 'E',
                'E', 'E' });
    }

}
