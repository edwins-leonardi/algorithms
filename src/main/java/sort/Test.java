package sort;

public class Test {

    public static void sort(String[] s, int w) {

        int R = 128;
        int N = s.length;
        String[] aux = new String[N];

        for (int j = w - 1; j >= 0; j--) {

            int[] count = new int[R + 1];

            for (int i = 0; i < N; i++)
                count[s[i].charAt(j) + 1]++;

            for (int r = 0; r < R; r++)
                count[r + 1] += count[r];

            for (int i = 0; i < N; i++)
                aux[count[s[i].charAt(j)]++] = s[i];

            for (int i = 0; i < N; i++)
                s[i] = aux[i];
        }

    }

    public static void main(String[] args) {
        String[] codes = new String[] { "ZABC", "DCDE", "FEDI", "LCAP", "BCOR", "YPRC", "QALI", "DSFD", "UTNC",
                "XPNC" };
        Test.sort(codes, 4);
        for (String code : codes)
            System.out.println(code);
    }

}
