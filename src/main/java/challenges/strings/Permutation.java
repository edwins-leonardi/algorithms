package challenges.strings;

import java.util.Arrays;

public class Permutation {

    public static void printAllPermutations(String str, String prefix) {

        if ("".equals(str))
            System.out.println(prefix);

        for (int i = 0; i < str.length(); i++) {
            String rem = str.substring(0, i) + str.substring(i + 1);
            printAllPermutations(rem, prefix + str.charAt(i));
        }
    }

    public static boolean isPermutation(String str, String other) {

        if (str.length() != other.length())
            return false;

        String s1 = sort(str);
        String s2 = sort(other);

        return s1.equals(s2);
    }

    void permutation(String str) {
        permutation(str, "");
    }

    void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println("= " + prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }

    private static String sort(String str) {
        char[] chars = new char[str.length()];
        str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        Permutation p = new Permutation();
        p.permutation("porc");

        // printAllPermutations("julia", "");
        //
        // System.out.println(isPermutation("julia", "ana"));
        // System.out.println(isPermutation("julia", "lijua"));

    }
}
