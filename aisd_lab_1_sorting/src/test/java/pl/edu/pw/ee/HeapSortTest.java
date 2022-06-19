package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class HeapSortTest {
    HeapSort heapSort;

    @Before
    public void init() {
        heapSort = new HeapSort();
    }
    @Test
    public void should_ThrowIllegalArgumentExceptionWhen_ArrayIsNull() {
        // given
        double[] nums = null;

        // when
        try {
            heapSort.sort(nums);
            fail("Tested exception was not thrown during the test");
        }

        // then
        catch (IllegalArgumentException exception) {
            assertEquals("Nums array cannot be null", exception.getMessage());
        }
    }

    @Test
    public void should_SortArrayWhenNumberOfArrayElements_isOdd() {
        // given
        double[] nums = { 3.2, 1.4, 6.3, 12.8, 2.0, 6.1, 5.5 };
        double[] sortedNums = { 1.4, 2.0, 3.2, 5.5, 6.1, 6.3, 12.8 };

        // when
        heapSort.sort(nums);

        // then
        assertTrue(Arrays.equals(nums, sortedNums));
    }

    @Test
    public void should_SortArrayWhenNumberOfArrayElements_isEven() {
        // given
        double[] nums = { 13.1, 2.6, 7.2, 6.5, 1.1, 4.2 };
        double[] sortedNums = { 1.1, 2.6, 4.2, 6.5, 7.2, 13.1 };

        // when
        heapSort.sort(nums);

        // then
        assertTrue(Arrays.equals(nums, sortedNums));    }

    @Test
    public void should_SortArrayWhenArray_hasALotOfTheSameElements() {
        // given
        double[] nums = { 2.0, 2.0, 2.0, 2.5, 2.0, 2.0 };
        double[] sortedNums = { 2.0, 2.0, 2.0, 2.0, 2.0, 2.5 };

        // when
        heapSort.sort(nums);

        // then
        assertTrue(Arrays.equals(nums, sortedNums));
    }

    @Test
    public void should_SortArrayWhenArray_hasAllTheSameElements() {
        // given
        double[] nums = { 7.0, 7.0, 7.0, 7.0, 7.0, 7.0, 7.0, 7.0 };
        double[] sortedNums = { 7.0, 7.0, 7.0, 7.0, 7.0, 7.0, 7.0, 7.0 };
        
        // when
        heapSort.sort(nums);

        // then
        assertTrue(Arrays.equals(nums, sortedNums));

    }

    @Test
    public void TestFor_OneElemInArray() {
        // given
        double[] nums = { 34.0 };
        double[] sortedNums = { 34.0 };
       
        // when
        heapSort.sort(nums);

        // then
        assertTrue(Arrays.equals(nums, sortedNums));

    }

    @Test
    public void shouldSortWhen_ArrayHasRandomizedValues() {
        // given
        Random r = new Random();
        double[] nums = new double[10];
        int seed = 100;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextDouble() * seed;
        }
        double [] sortedNums= new double[10];
        System.arraycopy(nums, 0, sortedNums, 0, 10);
        Arrays.sort(sortedNums);

        // when
        heapSort.sort(nums);

        // then
        assertTrue(Arrays.equals(nums, sortedNums));

    }

    @Test
    public void shouldReturnSortedArrayWhen_ArrayIsSorted() {
        // given
        double [] nums = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};
        double [] sortedNums = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};

        // when
        heapSort.sort(nums);

        // then
        assertTrue(Arrays.equals(nums, sortedNums));
    }

}
