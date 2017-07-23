package math.basic;
public class FibonacciWithMemoization {
	
	public static void main(String[] args) {
		fiboAll(9);
	}

	public static void fiboAll(int n) {
		int[] memo = new int[n+1];
		for (int i = 0; i <= n; i++) {
			System.out.println(i + ":" + fibonacci(i, memo));
		}
	}

	
	public static int fibonacci(int n, int[] memo){
		if(n == 1)
			return 1;
		else if(n <= 0)
			return 0;
		memo[n] = fibonacci(n-1, memo) + fibonacci(n-2, memo);
		return memo[n];

	}
}
