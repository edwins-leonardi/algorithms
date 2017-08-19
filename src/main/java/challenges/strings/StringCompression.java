package challenges.strings;

public class StringCompression {
	public static void main(String[] args) {
		System.out.println(compressString("aabcccccaaa"));
	}

	public static String compressString(String str) {

		StringBuffer compressedString = new StringBuffer(str.length());

		char lastChar = str.charAt(0);
		int count = 1;
		for (int i = 1; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			if (currentChar != lastChar) {
				compressedString.append(lastChar);
				compressedString.append(count);
				lastChar = currentChar;
				count = 1;
			} else {
				count++;
			}
		}
		compressedString.append(lastChar + "" + count);

		return str.length() > compressedString.length() ? compressedString.toString() : str;
	}
}
