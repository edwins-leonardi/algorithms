package challenges.strings;

public class StringCompression {
	public static void main(String[] args) {
		System.out.println(compressString("aabcccccaaa"));
	}

	public static String compressString(String str) {

		StringBuilder compressedString = new StringBuilder(str.length());

		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			count++;
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i+1)) {
				compressedString.append(str.charAt(i));
				compressedString.append(count);
				count = 0;
			}
		}

		return str.length() > compressedString.length() ? compressedString.toString() : str;
	}
}
