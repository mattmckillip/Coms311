package homework3;
/**
* InsertionSort class for pt2
* @author  MM
* @date 2/27/15
*/
import java.util.ArrayList;

public class InsertionSort implements SortAnalysis {

	/**
	 * Generic insertion sort
	 * 
	 * @param list
	 *            arraylist to be sorted
	 */
	private static <E extends Comparable<E>> void insertionSort(
			ArrayList<E> list) {
		int i;
		int j;
		E key;

		for (j = 1; j < list.size(); j++) {
			key = list.get(j);
			for (i = j - 1; (i >= 0) && ((list.get(i)).compareTo(key)) > 0; i--) {
				list.set(i + 1, list.get(i));
			}
			list.set(i + 1, key);
		}
	}

	@Override
	public long analyzeSort(ArrayList list) {
		long start = System.currentTimeMillis();
		insertionSort(list);
		return System.currentTimeMillis() - start;
	}
}
