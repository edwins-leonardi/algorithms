package challenges.math.basic;

public class Factorial {
	public static void main(String[] args) {
		for (int i = 0; i <= 5; i++) {
			System.out.print(i + "\t");
			System.out.println(factorial(i));
			
		}
	}
	
	public static int factorial(int n){
		
		if(n < 0)
			return -1;
		else if(n == 0)
			return 1;
		else
			return n * factorial(n-1);
	}
}
