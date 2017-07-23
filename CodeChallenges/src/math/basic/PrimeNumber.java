package math.basic;

public class PrimeNumber {

	public static void main(String[] args) {
		
		for(int i = 1; i< 30; i++){
			if(isPrime(i))
				System.out.println(i);
		}
		
		
	}
	
	public static boolean isPrime(int n){
		for(int x = 2; x * x <= n; x++){
			if(n % x == 0)
				return false;
		}
		return true;
	}

}
