package homework3;
/**
* QuickSort class for pt 3
* @author  MM
* @date 2/27/15
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class QuickSort implements SortAnalysis {
	@Override
	public long analyzeSort(ArrayList list) {
		long start = System.currentTimeMillis();
		quickSort(list);
		return System.currentTimeMillis() - start;
	}
	
	
	/**
	 * 
	 * Iterative and in-place quicksort algorithm that takes
	 * a generic array list will sort in ascending order
	 * 
	 * @param input ArrayList<E extends Comparable <E>> list to be sorted
	 */
	public static <E extends Comparable<E>> void quickSort(ArrayList<E> input)
    {
		// Create a stack to keep track of the right and left pointer
        Stack<Integer> stack = new Stack<Integer>();
        E pivot;
        
        // pivot pointer starts at the beginning, left at 1, and right at 2
        int pivotPointer = 0;
        int leftPointer = 1;
        int rightPointer = input.size() - 1;

        // Keep track of pointers
        stack.push(pivotPointer);
        stack.push(rightPointer);

        int subSetLeft, subsetRight;
        
        // End when stack is empty
        while (stack.size() > 0)
        {
        	// subset = right and left pointers
            subsetRight = (int)stack.pop();
            subSetLeft = (int)stack.pop();

            // Increment left and update pivot and right
            leftPointer = subSetLeft + 1;
            pivotPointer = subSetLeft;
            rightPointer = subsetRight;

            // Get value of pivot to compare
            pivot = input.get(pivotPointer);

            // Make sure left hasn't surpassed the right pointer
            if (leftPointer > rightPointer)
                continue;

            // Go until left is equal to right
            while (leftPointer < rightPointer)
            {
            	// if left pointer hasn't surpassed right and if value at left pointer is LT or == to pivot value, increment left
                while ((leftPointer <= rightPointer) && (input.get(leftPointer).compareTo(pivot) <= 0))
                {
                	// Try to find element greater than pivot
                    leftPointer++;
                }

                /// if left pointer hasn't surpassed right and if value at right pointer is GT or == to pivot value, decrement right
                while ((leftPointer <= rightPointer) && (input.get(rightPointer).compareTo(pivot) >= 0)){
                	// Try to find element smaller than pivot
                	rightPointer--;
                }
                    
                // Swap if the right pointer is still greater than the left pointer
                if (rightPointer >= leftPointer) {
                	Collections.swap(input, leftPointer, rightPointer);
                }
            }

            // if the pivot pointer is still less than the right pointer and the value at the pivot pointer is GT the valu at the right pointer, swap
            if (pivotPointer <= rightPointer)
                if( input.get(pivotPointer).compareTo(input.get(rightPointer)) > 0)
                {
                    Collections.swap(input, pivotPointer, rightPointer);
            	}
           
            // Handle stack overhead
            if (subSetLeft < rightPointer)
            {
                stack.push(subSetLeft);
                stack.push(rightPointer - 1);
            }

            if (subsetRight > rightPointer)
            {
                stack.push(rightPointer + 1);
                stack.push(subsetRight);
            }
        }
    }
}
