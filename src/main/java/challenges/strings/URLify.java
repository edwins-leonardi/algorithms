package challenges.strings;

public class URLify {

	public static void main(String[] args) {
		String w1 = URLify.urlify("Mr John Smith    ", 13);
		String w2 = URLify.urlify("Jane is a friend of Mine          ", 24);
		System.out.println(w1);
		System.out.println(w2);
		System.out.println("DONE");
	}
	
//	public static String urlify(String str, int size){
//		char copy[] = new char[str.length()];
//
//		for(int pointer = 0, i=0; i<size; i++){
//			char c = str.charAt(i);
//			if(c == ' '){
//				copy[pointer++] = '%';
//				copy[pointer++] = '2';
//				copy[pointer++] = '0';
//			}else{
//				copy[pointer++] = c;
//			}
//		}
//
//		return new String(copy);
//	}

	public static String urlify(String str, int size){
		char[] array = str.toCharArray();
		boolean replace = false;
		int replace_index = str.length()-1;
		for (int i = str.length()-1; i >0; i--){
			if (!replace && array[i] == ' ')
				continue;
			replace = true;
			if (array[i] != ' ') {
				array[replace_index--] = array[i];
			} else {
				array[replace_index--] = '0';
				array[replace_index--] = '2';
				array[replace_index--] = '%';
			}
		}
		return String.valueOf(array);
	}
}
