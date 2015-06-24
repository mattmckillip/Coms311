package homework3;
/**
* Test class for assignment 4
* @author  MM
* @date 2/27/15
*/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class testClass {
	QuickSort qs = new QuickSort();
	InsertionSort is = new InsertionSort();
	MatrixOperations mo = new MatrixOperations();

	ArrayList<Integer> arr1 = new ArrayList<Integer>();
	ArrayList<Integer> sortedArr1 = new ArrayList<Integer>();

	ArrayList<String> arr2 = new ArrayList<String>();
	ArrayList<String> sortedArr2 = new ArrayList<String>();

	Random random = new Random();

	@Before
	public void setUp() throws Exception {
		arr1.add(3);
		arr1.add(1);
		arr1.add(4);
		arr1.add(5);
		arr1.add(8);
		arr1.add(9);
		arr1.add(10);
		arr1.add(2);
		arr1.add(7);
		arr1.add(6);

		for (int i = 1; i <= 10; i++) {
			sortedArr1.add(i);
		}

		arr2.add("g");
		arr2.add("c");
		arr2.add("i");
		arr2.add("b");
		arr2.add("e");
		arr2.add("j");
		arr2.add("a");
		arr2.add("f");
		arr2.add("h");
		arr2.add("d");

		sortedArr2.add("a");
		sortedArr2.add("b");
		sortedArr2.add("c");
		sortedArr2.add("d");
		sortedArr2.add("e");
		sortedArr2.add("f");
		sortedArr2.add("g");
		sortedArr2.add("h");
		sortedArr2.add("i");
		sortedArr2.add("j");
	}

	@Test
	public void testInsertionSort1() {
		is.analyzeSort(arr1);
		assertEquals(true, arr1.equals(sortedArr1));
	}

	@Test
	public void testInsertionSort2() {
		is.analyzeSort(arr2);
		assertEquals(true, arr2.equals(sortedArr2));
	}

	@Test
	public void testInsertionSort3() {
		ArrayList<Integer> randomArr = new ArrayList<Integer>();
		for (int i = 0; i < 1000; ++i) {
			randomArr.add(random.nextInt(1001));
			is.analyzeSort(randomArr);
			assertTrue(isSorted(randomArr));
		}
	}

	@Test
	public void quickInsertionSort1() {
		qs.analyzeSort(arr1);
		assertEquals(true, arr1.equals(sortedArr1));
	}

	@Test
	public void quickInsertionSort2() {
		qs.analyzeSort(arr2);
		assertEquals(true, arr2.equals(sortedArr2));
	}

	@Test
	public void quickInsertionSort3() {
		ArrayList<Integer> randomArr = new ArrayList<Integer>();
		for (int i = 0; i < 1000; ++i) {
			randomArr.add(random.nextInt(1001));
			qs.analyzeSort(randomArr);
			assertTrue(isSorted(randomArr));
		}
	}

	@Test
	public void testMatrixMult1() {
		double[][] m1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		double[][] m2 = { { 9, 8, 7 }, { 6, 5, 4 }, { 3, 2, 1 } };
		double[][] resultant = { { 30.0, 24.0, 18.0 }, { 84.0, 69.0, 54.0 },
				{ 138.0, 114.0, 90.0 } };
		double[][] m3 = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

		mo.analyzeMultiply(m1, m2, m3);
		assertEquals(true, matrixEqual(m3, resultant));
	}

	@Test
	public void testMatrixMult2() {
		double[][] m1 = { { 7, 8 }, { 5, 4 } };
		double[][] m2 = { { 1, 8 }, { 3, 7 } };
		double[][] resultant = { { 31, 112 }, { 17, 68 } };
		double[][] m3 = { { 0, 0 }, { 0, 0 } };
		mo.analyzeMultiply(m1, m2, m3);
		assertEquals(true, matrixEqual(m3, resultant));
	}

	@Test
	public void testMatrixInverse1() {
		double[][] m1 = { { 1, 2 }, { 3, 4 } };
		double[][] resultant = { { -2, 1 }, { 1.5, -.5 } };
		double[][] m3 = { { 0, 0 }, { 0, 0 } };
		mo.analyzeInverse(m1, m3);

		assertEquals(true, matrixEqual(m3, resultant));
	}

	/* Helper methods */
	private <E extends Comparable<E>> boolean isSorted(ArrayList<E> list) {
		boolean sorted = true;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1).compareTo(list.get(i)) > 0)
				sorted = false;
		}

		return sorted;
	}

	private boolean matrixEqual(double[][] m1, double[][] m2) {
		if (m1.length != m2.length)
			return false;
		if (m1[0].length != m2[0].length)
			return false;
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1[0].length; j++) {
				if (m1[i][j] != m2[i][j])
					return false;
			}
		}
		return true;

	}

}
