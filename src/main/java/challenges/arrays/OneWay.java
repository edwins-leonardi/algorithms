package challenges.arrays;

import java.util.HashMap;
import java.util.HashSet;

public class OneWay {
    public static void main(String[] args) {
        System.out.println("OneString pale -> ale? " + isOneEditAway2("pale", "ale"));
        System.out.println("OneString pale -> ple? " + isOneEditAway2("pale", "ple"));
        System.out.println("OneString pales -> pale? " + isOneEditAway2("pales", "pale"));
        System.out.println("OneString pale -> bale? " + isOneEditAway2("pale", "bale"));
        System.out.println("OneString pale -> bake? " + isOneEditAway2("pale", "bake"));

    }

    /**
     * There are three types of edits that can be performed on strings: insert a character, remove a character, remove
     * a character or replace a character. Given two strings, write a fuction to check if they are one edit (or zero edits) aways.
     *
     * @param String first
     * @param String second
     * @return boolean
     */
    public static boolean isOneEditAway(String first, String second){

        if (Math.abs(first.length() - second.length()) > 1)
            return false;

        HashMap<Character, Integer> dict = new HashMap();

        for (char c : first.toCharArray()){
            Integer n = dict.get(c);
            if (n == null)
                n = 0;
            n++;
            dict.put(c, n);
        }
        for (char c : second.toCharArray()){
            Integer n = dict.get(c);
            if (n == null)
                n = 0;
            n--;
            if (n == 0) {
                dict.remove(c);
                continue;
            }
            dict.put(c, n);
        }

        return dict.size() <= 2;
    }

    public static boolean isOneEditAway2(String firstString, String secondString){

        if (Math.abs(firstString.length() - secondString.length()) > 1)
            return false;

        String s1 = (firstString.length() < secondString.length()) ? firstString : secondString;
        String s2 = (firstString.length() > secondString.length()) ? secondString : firstString;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;
        while(index1 < s1.length() && index2 < s2.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (foundDifference)
                    return false;
                foundDifference = true;
                if (s1.length() == s2.length()) {
                    index1++;
                }
                index2++;
            } else {
                index1++;
                index2++;
            }
        }

        return true;
    }

}
