package homework3;
/**
* part 1 main method
* @author  MM
* @date 2/27/15
*/
import java.util.Arrays;
import java.util.Random;

public class Part1 {

	public static void main(String[] args) {
		MatrixOperations MO = new MatrixOperations();

		int size = (int) sizeFor1000ms();
		int spacing = 1;
		// long[]timeMatrix = new long[size/spacing+2];
		long[] timeMatrix = new long[size];
		int index = 0;
		// Output the times required for all input sizes starting at 1
		// and ending at sizeFor1000ms(). Need to use spacing since
		// Excel can only graph smaller amounts of data for report
		for (int i = 1; i <= size; i = i + spacing) {
			timeMatrix[index] = timeToCalculateMatrixSize(i);
			index += 1;
			// System.out.println(i);
		}
		System.out.println(Arrays.toString(timeMatrix));

	}

	/**
	 * Private method which doubles the size of the matrices until the time
	 * exceeds 1000 ms. Then uses a binary search to return the matrix size
	 * which the multiplication takes 1000 ms.
	 * 
	 * @return The smallest size of the array which takes 1000 ms to multiply
	 */
	private static long sizeFor1000ms() {
		boolean under1000 = true;
		int size = 1;

		while (under1000) {
			// Find the time to calculate the current size
			long time = timeToCalculateMatrixSize(size);

			if (time < 1000) {
				// Double the size until time < 1000
				size = size * 2;
			}

			else {
				// Found size that takes over 1000 ms
				under1000 = false;

				// Print for visual debugging TODO remove before submitting
				System.out.println("done at " + size);
			}
		}
		// Run a binary search to find the exact size in the range, return the
		// result
		return binarySearch(size / 2, size);
	}

	/**
	 * Binary search to find the first matrix size over 1000 ms
	 * 
	 * @param low
	 *            lower boundary to search
	 * @param high
	 *            upper boundary to search
	 * @return size for the matrix that takes 1000 ms
	 */
	private static long binarySearch(int low, int high) {
		int mid = 0;
		long time;

		// Perform the binary seach
		while (low < high) {
			mid = low + (high - low) / 2;
			time = timeToCalculateMatrixSize(mid);
			if (time == 1000) {
				return mid;
			} else if (time < 1000) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		// TODO decide if this should stay
		mid = mid + 1;
		return mid;
	}

	/**
	 * Private helper method which builds three matrices of a certain size with
	 * random data and returns the time from the analyzeMultiply method
	 * 
	 * @param matrixSize
	 *            size of matrix
	 * @return time taken to multiply arrays of martrixSize
	 * 
	 */
	private static long timeToCalculateMatrixSize(int matrixSize) {
		Random random = new Random();
		double[][] m1 = new double[matrixSize][matrixSize];
		double[][] m2 = new double[matrixSize][matrixSize];
		double[][] m3 = new double[matrixSize][matrixSize];

		long totalTime = 0;
		// Fill the matrices with random data
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				m1[i][j] = random.nextInt(9);
				m2[i][j] = random.nextInt(9);
				m3[i][j] = 0.0;
			}
		}
		MatrixOperations matrixOps = new MatrixOperations();
		// take the average of 5 times from analyzeMultiply
		for (int i = 0; i < 5; i++) {

			totalTime = totalTime + (matrixOps.analyzeMultiply(m1, m2, m3));
		}

		return totalTime / 5;
	}

}
