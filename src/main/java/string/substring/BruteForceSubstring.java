package string.substring;

public class BruteForceSubstring {
    public static int substring(String s, String p) {

        int n = s.length();
        int m = p.length();

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            for (; j < m; j++)
                if (s.charAt(i + j) != p.charAt(j))
                    break;
            if (j == m)
                return i;
        }

        return -1;
    }

    public static int substring2(String s, String p) {
        int m = p.length();
        int n = s.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            if (s.charAt(i) == p.charAt(j))
                j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if (j == m)
            return i - m; // found
        else
            return -1; // not found
    }

    public static void main(String[] args) {
        String s = "cabra|camelo|elefante|cavalo|baleia|galinha";
        String p = "cavalo";
        int i = BruteForceSubstring.substring2(s, p);
        System.out.println(i);
        if (i >= 0)
            System.out.println("Matched: " + s.substring(i, i + p.length()));
    }
}
