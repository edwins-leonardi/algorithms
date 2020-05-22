package challenges.strings;

import java.util.Arrays;

/**
 * Determine if a string has all unique characters
 * 
 * @author eddie
 *
 */
public class UniqueCharacters {

	public static void main(String[] args) {


		System.out.println(UniqueCharacters.isUniqueChars("EDWINS"));
		System.out.println(UniqueCharacters
				.isUniqueChars("Jose HENRIQUE"));
	}

	public static boolean isUniqueChars(String str) {
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			if ((checker & (1 << str.charAt(i))) > 0)
				return false;
			checker |= (1 << str.charAt(i));
		}
		return true;
	}


	public static boolean hasOnlyUniqueCharacters(String str) {

		if (str == null || str.isEmpty())
			return false;

		char[] chars = getSortedCharArrayFromString(str);
		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
		}
		System.out.println();

		char previous = chars[0];
		for (int i = 1; i < chars.length; i++) {
			if (chars[i] == previous)
				return false;
			previous = chars[i];
		}
		return true;
	}

	private static char[] getSortedCharArrayFromString(String str) {
		char[] chars = new char[str.length()];
		str.getChars(0, str.length(), chars, 0);
		Arrays.sort(chars);
		return chars;
	}

}
