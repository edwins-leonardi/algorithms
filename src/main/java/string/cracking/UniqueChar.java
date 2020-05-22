package string.cracking;

import java.util.Arrays;

public class UniqueChar {
    public static void main(String[] args) {
        System.out.println(isAllUnique("albuqer"));
        System.out.println(isAllUnique("albuquerque"));
        System.out.println(isAllUnique("abcdefghijklm"));
        System.out.println(isAllUnique("edwins"));
        System.out.println(isAllUnique("ana"));
        System.out.println(isAllUnique("priscila"));
    }

    public static boolean isAllUnique(String text) {
        System.out.println();
        System.out.println(text + " is all unique?");
        char[] a = text.toCharArray();
        Arrays.sort(a);
        for(int i = 0; i < a.length-1; i++) {
            if (a[i] == a[i+1])
                return false;
        }
        return true;
    }
}
