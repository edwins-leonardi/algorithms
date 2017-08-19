package challenges.math.basic;
public class PowersOf2 {
	
	public static void main(String[] args) {
		powersOf2(1048);
	}

	public static int powersOf2(int n) {
		if (n <= 1) {
			System.out.println(1);
			return 1;
		} else {
			int prev = powersOf2(n/2);
			int cur = prev * 2;
			System.out.println(cur);
			return cur;
		}
	}
}
