package string;

import java.util.HashMap;

public class OneAway {

	public static void main(String[] args) {
		System.out.println(oneAwayOptimized("pale", "bale"));
		System.out.println(oneAwayOptimized("pale", "ple"));
		System.out.println(oneAwayOptimized("pale", "pales"));
		System.out.println(oneAwayOptimized("pale", "bake"));
		System.out.println(oneAwayOptimized("apple", "aple"));
	}

	/**
	 * There are three types of edits that can be perfomed on strings: insert a
	 * character, remove a character or replace a character. Given two strings,
	 * write a function to check if they are one edit (or zero) away
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean oneAway(String s1, String s2) {

		HashMap<Character, Integer> characterCounter = new HashMap<Character, Integer>();

		if (Math.abs(s1.length() - s2.length()) > 1)
			return false;

		for (Character c : s2.toCharArray()) {
			Integer count = characterCounter.get(c);
			if (count == null)
				count = 0;
			count += 1;
			characterCounter.put(c, count);
		}

		int sum = 0;
		for (Character c : s1.toCharArray()) {
			Integer count = characterCounter.get(c);
			if (count != null)
				sum += count;
		}

		if (Math.abs(s1.length() - sum) > 1) {
			return false;
		}

		return true;

	}

	/**
	 * There are three types of edits that can be perfomed on strings: insert a
	 * character, remove a character or replace a character. Given two strings,
	 * write a function to check if they are one edit (or zero) away
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean oneAwayOptimized(String first, String second) {

		if (Math.abs(first.length() - second.length()) > 1)
			return false;

		String s1 = first.length() < second.length() ? first : second;
		String s2 = first.length() < second.length() ? second : first;

		int index1 = 0;
		int index2 = 0;
		boolean differenceFound = false;
		while (index2 < s2.length() && index1 < s1.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				if (differenceFound)
					return false;
				differenceFound = true;
				if (s1.length() == s2.length())
					index1++;
			} else {
				index1++;
			}
			index2++;
		}

		return true;

	}

}
