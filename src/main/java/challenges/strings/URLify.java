package challenges.strings;

public class URLify {

	public static void main(String[] args) {
		String u = URLify.urlify("Mr John Smith    ", 13);
		System.out.println(u);
	}
	
	public static String urlify(String str, int size){
		char copy[] = new char[str.length()];
		
		for(int pointer = 0, i=0; i<size; i++){
			char c = str.charAt(i);
			if(c == ' '){
				copy[pointer++] = '%';
				copy[pointer++] = '2';
				copy[pointer++] = '0';
			}else{
				copy[pointer++] = c;
			}
		}
		
		return new String(copy);
	}
}
