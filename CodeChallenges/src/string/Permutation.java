package string;


public class Permutation {

	public static void main(String[] args) {
		permutation("julia", "");
	}
	
	public static void permutation(String str, String prefix){
		
		if("".equals(str))
			System.out.println(prefix);
		
		for(int i = 0; i<str.length(); i++){
			String rem = str.substring(0,i) + str.substring(i+1);
			permutation(rem, prefix + str.charAt(i));
		}
	}
}
