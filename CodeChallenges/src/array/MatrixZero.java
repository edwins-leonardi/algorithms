package array;

public class MatrixZero {

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 0, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 0, 12 },
				{ 13, 14, 15, 16 } };

		matrixZero(matrix);

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

	public static int[][] matrixZero(int[][] matrix) {

		boolean[] rowZero = new boolean[matrix.length];
		boolean[] colZero = new boolean[matrix[0].length];

		for (int row = 0; row < rowZero.length; row++) {
			for (int col = 0; col < colZero.length; col++) {
				if (matrix[row][col] == 0) {
					rowZero[row] = true;
					colZero[col] = true;
				}
			}
		}

		for (int i = 0; i < rowZero.length; i++) {
			if (rowZero[i])
				nullifyRows(matrix[i]);
		}

		for (int i = 0; i < colZero.length; i++) {
			if (colZero[i])
				nullifyCols(matrix, i);
		}

		return matrix;

	}

	private static void nullifyRows(int[] rows) {
		for (int i = 0; i < rows.length; i++)
			rows[i] = 0;
	}

	private static void nullifyCols(int[][] matrix, int col) {
		for (int i = 0; i < matrix.length; i++)
			matrix[i][col] = 0;
	}

}
