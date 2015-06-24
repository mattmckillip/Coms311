package homework3;
/**
* part 2 main method
* @author  MM
* @date 2/27/15
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Part2 {

	static boolean worstCase = false;

	public static void main(String[] args) {
		int size = (int) sizeFor1000ms();
		System.out.println("1000 ms at: " + size);
		int spacing = 10;
		long[] timeMatrix = new long[size / spacing + 1];
		int index = 0;
		// 1) by doubling size find range for binary search to run
		// 2) find exact size with binary search
		// 3) print array of size 1 to exact size

		// is.analyzeSort(generateList(10));
		for (int i = 1; i <= size; i = i + spacing) {
			timeMatrix[index] = timeToSortSize(i);
			System.out.println("Finding length for size " + i + " / " + size);
			index++;
		}
		System.out.println(Arrays.toString(timeMatrix));
	}

	private static ArrayList<Integer> generateList(int size) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Random random = new Random();
		int j = 0;
		for (int i = size; i > 0; i--) {
			if (worstCase) {
				arr.add(i);
			} else {
				arr.add(random.nextInt(size));
			}

			j++;
		}
		// System.out.println(arr);

		return arr;
	}

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
	 * Helper method to find find the size where it first takes >1000 ms
	 * 
	 * @return index of that size
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

	private static long timeToSortSize(int size) {
		InsertionSort insSort = new InsertionSort();
		return insSort.analyzeSort(generateList(size));
	}

}
