package array.string;

import java.util.Arrays;


public class Permutation {

	public static void main(String[] args) {
		printAllPermutations("julia", "");
		
		System.out.println(isPermutation("julia", "ana"));
		System.out.println(isPermutation("julia", "lijua"));
	}
	
	public static void printAllPermutations(String str, String prefix){
		
		if("".equals(str))
			System.out.println(prefix);
		
		for(int i = 0; i<str.length(); i++){
			String rem = str.substring(0,i) + str.substring(i+1);
			printAllPermutations(rem, prefix + str.charAt(i));
		}
	}
	
	public static boolean isPermutation(String str, String other){
		
		if(str.length() != other.length())
			return false;
		
		String s1 = sort(str);
		String s2 = sort(other);
		
		return s1.equals(s2);
	}
	
	private static String sort(String str) {
		char[] chars = new char[str.length()];
		str.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

}
