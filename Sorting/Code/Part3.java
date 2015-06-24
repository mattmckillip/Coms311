package homework3;
/**
* part 3 main method
* @author  MM
* @date 2/27/15
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Part3 {

	static boolean worstCase = true;

	public static void main(String[] args) {
		int size = (int) sizeFor1000ms();
		System.out.println("1000 ms at: " + size);
		int spacing = 100;
		long[] timeMatrix = new long[size / spacing + 1];
		int index = 0;

		for (int i = 1; i <= size; i = i + spacing) {
			timeMatrix[index] = timeToSortSize(i);
			System.out.println("Finding length for size " + i + " / " + size);
			index++;
		}
		System.out.println(Arrays.toString(timeMatrix));
	}

	/**
	 * 
	 * @param size
	 * @return
	 */
	private static ArrayList<Integer> generateList(int size) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Random random = new Random();
		for (int i = size; i > 0; i--) {
			if (worstCase) {
				arr.add(i);
			} else {
				arr.add(random.nextInt(size));
			}

		}
		return arr;
	}

	/**
	 * 
	 * @param low
	 * @param high
	 * @return
	 */
	private static long binarySearch(int low, int high) {
		int mid = 0;
		long time;
		while (low < high) {
			mid = low + (high - low) / 2;
			time = timeToSortSize(mid);
			if (time == 1000) {
				return mid;
			} else if (time < 1000) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		mid = mid + 1;
		return mid;
	}

	/**
	 * 
	 * @return
	 */
	private static long sizeFor1000ms() {
		boolean under1000 = true;
		int size = 1;

		while (under1000) {
			long time = timeToSortSize(size);
			if (time < 1000) {
				size = size * 2;
			} else {
				under1000 = false;
			}
		}
		return binarySearch(size / 2, size);
	}

	/**
	 * 
	 * @param size
	 * @return
	 */
	private static long timeToSortSize(int size) {
		QuickSort qs = new QuickSort();
		return qs.analyzeSort(generateList(size));
	}
}
