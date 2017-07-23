package math.basic;

public class Fibonacci {

	public static void main(String[] args) {
		
		
		for (int i = 1; i <= 7; i++) {
			System.out.print(i + "\t");
			System.out.println(fibonacci(i));
			
		}

		
	}
	
	public static int fibonacci(int n){
		if(n == 1)
			return 1;
		else if(n <= 0)
			return 0;
		else return fibonacci(n-1) + fibonacci(n-2);
	}
}
