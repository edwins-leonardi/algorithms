package string.sort;

import java.util.Arrays;

public class LongestRepeatedSubstring {

    public static String longestSubstring(String str) {
        String[] suffix = new String[str.length()];

        for (int i = 0; i < str.length(); i++) {
            suffix[i] = str.substring(i);
        }

        Arrays.sort(suffix);

        for (String s : suffix)
            System.out.println(s);

        System.out.println("------------");

        String longestString = "";
        for (int i = 0; i < suffix.length - 1; i++) {
            String s1 = suffix[i];
            String s2 = suffix[i + 1];
            int len = leastCommonPrefix(s1, s2);
            if (len > longestString.length())
                longestString = s1.substring(0, len);
            // for (int j = 0; j < s1.length() && j < s2.length() &&
            // s1.charAt(j) == s2.charAt(j); j++) {
            // if (j > longestString.length()) {
            // longestString = s1.substring(0, j + 1);
            // }
            // }
        }

        return longestString;
    }

    public static int leastCommonPrefix(String s1, String s2) {
        int lcp = 0;
        while (lcp < s1.length() && lcp < s2.length() && s1.charAt(lcp) == s2.charAt(lcp)) {
            lcp++;
        }
        return lcp;
    }

    public static void main(String[] args) {
        System.out.println(LongestRepeatedSubstring.longestSubstring("aacahdjhajhabacateagtttacaagcabacatedhakhjas"));
    }

}
