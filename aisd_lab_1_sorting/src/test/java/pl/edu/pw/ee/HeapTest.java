package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class HeapTest {
    Heap<Double> heap;

    @Before
    public void setUp() {
        heap = new Heap<>();
    }

    @Test
    public void shouldThrowIndexOutOfBundsExceptionWhen_WeTryToPopFromEmptyHeap() {
        // given
        Double a;
        // when
        try {
            a = heap.pop();
            fail("Tested exception was not thrown during the test");
        }

        // then
        catch (IndexOutOfBoundsException exception) {
            assertEquals("No elements in heap", exception.getMessage());
        }

    }

    @Test
    public void shouldReturn_TheOnlyArgumentInHeap() {
        // given
        Double a = 3.3;

        // when
        heap.put(3.3);

        // then
        assertEquals(a, heap.pop());
    }


    @Test
    public void shouldReturn_TheBiggestArgumentInHeap() {
        // given
        Double a = 5.7;

        // when
        heap.put(4.2);
        heap.put(5.7);
        heap.put(5.5);

        // then
        assertEquals(a, heap.pop());

    }

    @Test
    public void shouldFillTheArray_WithPutArguments() {
        // given
        heap.put(1.3);
        heap.put(4.4);
        heap.put(14.2);
        heap.put(25.7);
        heap.put(65.5);
        heap.put(7.9);
        double [] arrayToFill = new double[6];
        double [] filledArray = {65.5, 25.7, 14.2, 7.9, 4.4, 1.3};

        // when
        for (int i=0; i<arrayToFill.length; i++) {
            arrayToFill[i] = heap.pop();
        }

        // then
        assertTrue(Arrays.equals(arrayToFill, filledArray));

    }
}
