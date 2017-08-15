package array;

public class MatrixRotation {
	public static void main(String[] args) {
		int[][] original = new int[][] { { 1, 2, 3 }, { 5, 6, 7 }, { 8, 4, 9 }, { 0, 0, 0 } };
		int[][] newMatrix = rotate90Degrees(original);

		printMatrix(newMatrix);

		System.out.println();

		int[][] matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };

		matrix = rotate(matrix, 4);

		printMatrix(matrix);
	}

	private static void printMatrix(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				System.out.print(matrix[row][col] + "\t");
			}
			System.out.println();
		}
	}

	public static int[][] rotate90Degrees(int[][] original) {

		int rows = original.length;
		int cols = original[0].length;
		int[][] newMatrix = new int[cols][rows];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				newMatrix[col][rows - row - 1] = original[row][col];
			}
		}

		return newMatrix;

	}

	public static int[][] rotate(int[][] matrix, int n) {

		for (int layer = 0; layer < matrix.length / 2; ++layer) {
			int first = layer;
			int last = n - 1 - layer;
			for (int i = first; i < last; ++i) {

				int offset = i - first;

				// from top to temp
				int temp = matrix[first][i];

				// from left to top
				matrix[first][i] = matrix[last - offset][first];

				// from bottom to left
				matrix[last - offset][first] = matrix[last][last - offset];

				// from right to bottom
				matrix[last][last - offset] = matrix[i][last];

				// from temp to right
				matrix[i][last] = temp;
			}
		}

		return matrix;
	}

}
