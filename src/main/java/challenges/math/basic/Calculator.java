package challenges.math.basic;

public class Calculator {

	public static void main(String[] args) {
		System.out.println(3 % 5);
		System.out.println("Product 4 * 3 = " +product(4, 3));
		System.out.println("Power 4  3 = " +power(4, 3));
		System.out.println("Div 25 / 4 = " +div(25, 4));
		System.out.println("Mod 67  23 = " +mod(67, 23));
		System.out.println("sqrt 81 = " +sqrt(81));
	}
	
	public static int product(int a, int b){
		int sum = 0;
		for(int i = 0; i< b; i++){
			sum += a;
		}
		return sum;
	}
	
	public static int power(int a, int b){
		if(b < 0)
			return 0;
		else if(b == 0)
			return 1;
		else return a * power(a, b-1);
	
	}

	public static int div(int a, int b){
		int count = 0;
		int sum = b;
		while(sum <= a){
			count++;
			sum += b;
		}
		return count;
	}

	
	public static int mod(int a, int b){
		if(b < 0)
			return -1;
		int div = a / b;
		return a - div * b;
	}
	
	
	public static int sqrt(int n){
		for(int guess = 1; guess * guess <= n; guess++){
			if(guess * guess == n)
				return guess;
		}
		return -1;
	}
	
	
}
