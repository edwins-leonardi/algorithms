package memoization;

public class Fibonacci {
    public static void main(String[] args) {
        int[] memo = new int[11];
        for(int i =1; i<=10; i++) {
            int f = fib(i, memo);
            System.out.printf("%d = %d\n", i, f);
        }
    }

    public static int fib(int n, int[] memo){
        if(n <= 0)
            return 0;
        if(n == 1)
            return 1;
        if(memo[n] != 0){
            System.out.println("MEMO=" + memo[n]);
            return memo[n];
        }


        memo[n] = fib(n-1, memo) + fib(n-2, memo);
        return memo[n];
    }
}
