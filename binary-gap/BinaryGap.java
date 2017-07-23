class BinaryGap {
	public int solution(int N) {
		return calcBinaryGap(N, 0, false, 0);
	}

	private int calcBinaryGap(int N, int currentGap, boolean chain,
			int greaterGap) {

		int remain = N % 2;
		if (remain == 1 && chain) {
			if (currentGap > greaterGap) {
				greaterGap = currentGap;
			}
			currentGap = 0;
		} else if (remain == 1) {
			chain = true;
		} else if (remain == 0 && chain) {
			currentGap++;
		}

		if (N <= 1)
			return greaterGap;
		else
			return calcBinaryGap(N >> 1, currentGap, chain, greaterGap);

	}

}
