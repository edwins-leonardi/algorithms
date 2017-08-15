package array.string;

import java.util.HashMap;

public class Palindrome {

	public static void main(String[] args) {

		System.out.println(isAnyPermutationAPalindrome("subinoonibus"));
		System.out.println(isPermutationOfPalindrome("subinoonibus"));

	}

	public static boolean isPermutationOfPalindrome(String phrase) {
		int bitVector = createBitVector(phrase);
		return bitVector == 0 || checkOnlyOneBitSet(bitVector);
	}

	private static boolean checkOnlyOneBitSet(int bitVector) {
		return (bitVector & (bitVector - 1)) == 0;
	}

	public static int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int A = Character.getNumericValue('A');
		int Z = Character.getNumericValue('Z');

		int val = Character.getNumericValue(c);
		if (a <= val && val <= z)
			return val - a;
		else if (A <= val && val <= Z)
			return val - A;
		return -1;
	}

	public static int createBitVector(String str) {
		int bitVector = 0;
		for (char c : str.toCharArray()) {
			int x = getCharNumber(c);
			bitVector = toogle(bitVector, x);
		}

		return bitVector;
	}

	private static int toogle(int bitVector, int x) {
		if (x < 0)
			return bitVector;

		int mask = 1 << x;
		if ((bitVector & mask) == 0)
			bitVector |= mask;
		else
			bitVector &= ~mask;

		return bitVector;
	}

	public static boolean isAnyPermutationAPalindrome(String str) {

		str = str.toUpperCase();
		HashMap<Character, Integer> charOcurrences = new HashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			Character character = str.charAt(i);
			if (character.equals(' '))
				continue;
			Integer ocurrences = charOcurrences.get(character);
			if (ocurrences == null)
				ocurrences = 0;
			charOcurrences.put(character, ocurrences + 1);
		}

		int numberOfOddsAllowed = str.length() % 2;
		int oddOcurrences = 0;
		for (int ocurrences : charOcurrences.values()) {
			if (ocurrences % 2 == 1)
				oddOcurrences++;
			if (oddOcurrences > numberOfOddsAllowed)
				return false;
		}

		return true;
	}

}
