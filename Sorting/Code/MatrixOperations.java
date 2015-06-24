package homework3;
/**
* MatrixOperations class for pt 1 and 4
* @author  MM
* @date 2/27/15
*/
public class MatrixOperations implements MatrixAnalysis {
	@Override
	public long analyzeMultiply(double[][] m1, double[][] m2, double[][] m3) {
		long startTime = System.currentTimeMillis();
		m3 = multiplyMatrix(m1, m2, m3);
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	@Override
	public long analyzeInverse(double[][] m1, double[][] m2) {
		long startTime = System.currentTimeMillis();
		m2 = gaussJordanInvers(m1, m2);
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	/**
	 * Private helper method which multiplies the matrices
	 * 
	 * @param m1
	 *            The first square matrix to multiply
	 * @param m2
	 *            The second square matrix to multiply
	 * @param m3
	 *            The result from m1 * m2
	 * @return The matrix m3
	 * 
	 */
	private static double[][] multiplyMatrix(double m1[][], double m2[][],
			double m3[][]) {
		int i = 0;
		int j = 0;
		int k = 0;
		double sum = 0;

		for (i = 0; i < m1.length; i++) {
			for (j = 0; j < m1[0].length; j++) {
				for (k = 0; k < m2.length; k++) {
					sum = sum + m1[i][k] * m2[k][j];
				}

				m3[i][j] = sum;
				sum = 0;
			}
		}
		return m3;
	}

	/**
	 * Private helper method which inverts a matrix
	 * 
	 * @param input
	 *            Square matrix to be inverted
	 * 
	 * @return square inverse matrix of m1
	 */
	private static double[][] gaussJordanInvers(double[][] input, double[][] ret) {
		double[][] temp1 = new double[input.length][input[0].length * 2];
		double[][] temp2 = new double[input.length][input[0].length * 2];

		/**
		 * Temp1 is the input m1 matrix with the identity matrix to the right
		 * like below with a,b,c,d,e,f,g,h,i representing m1
		 * 
		 * {a, b, c, 1, 0 ,0} {d, e, f, 0, 1 ,0} {g, h, i, 0, 0 ,1}
		 */
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length * 2; j++) {
				if (j - i == input.length) {
					temp1[i][j] = 1;
				}

				if (j < input[0].length) {
					temp1[i][j] = input[i][j];
				}
			}
		}

		/**
		 * Perform algorithm to let the values below the diagonal be zero and
		 * placing the correct inverse value in column "0 and 1" of the extended
		 * matrix or really {a, b, c, solved, solved, 0} {0, b, c, solved,
		 * solved, 0} {0, 0, c, solved, solved, 1}
		 * 
		 */
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length; j++) {
				for (int k = 0; k < input[0].length * 2; k++) {
					if (i == j) {
						temp2[i][k] = temp1[i][k] / temp1[i][i];
					}

					else {
						temp2[j][k] = temp1[j][k];
					}
				}
			}

			temp1 = copyMatrixValues(temp2);

			for (int j = i + 1; j < input.length; j++) {
				for (int k = 0; k < input[0].length * 2; k++) {
					temp2[j][k] = temp1[j][k] - temp1[i][k] * temp1[j][i];
				}
			}

			temp1 = copyMatrixValues(temp2);
		}

		/**
		 * Perform algorithm to let the values above the diagonal be zero and
		 * placing the correct inverse value in column "2" of the extended
		 * matrix {a, b, c, solved, solved, solved} {0, b, c, solved, solved,
		 * solved} {0, 0, c, solved, solved, solved}
		 * 
		 */
		for (int i = input[0].length - 1; i > 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				for (int k = 0; k < input[0].length * 2; k++) {
					temp2[j][k] = temp1[j][k] - temp1[i][k] * temp1[j][i];
				}
			}
			temp1 = copyMatrixValues(temp2);

		}

		/**
		 * Now take the right side of the matrix, the solved side and save it in
		 * m2 to return
		 */
		for (int i = 0; i < input.length; i++) {
			for (int j = input[0].length; j < input[0].length * 2; j++) {
				ret[i][j - input[0].length] = temp2[i][j];
			}
		}
		return ret;
	}

	/**
	 * Helper method to copy matrix values, not pointer
	 * 
	 * @param arr
	 *            Matrix to be copied
	 * @return Matrix with the same values as arr
	 */
	private static double[][] copyMatrixValues(double[][] arr) {
		double[][] ret = new double[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				ret[i][j] = arr[i][j];
			}
		}
		return ret;
	}
}
