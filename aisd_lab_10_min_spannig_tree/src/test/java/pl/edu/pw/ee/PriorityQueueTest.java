package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PriorityQueueTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhen_TryToPutNull() {
        // given
        PriorityQueue priorityQueue = new PriorityQueue();

        // when
        priorityQueue.put(null);

        // then
        assert false;
    }

    @Test
    public void shouldReturnNullWhen_TryToGetObjectFromEmptyQueue() {
        // given
        PriorityQueue priorityQueue = new PriorityQueue();
        Object expected = null;

        // when
        Object actual = priorityQueue.get();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTheOnlyValueInQueue() {
        // given
        PriorityQueue priorityQueue = new PriorityQueue();
        Edge expected = new Edge("A", "D", 4);

        // when
        priorityQueue.put(expected);
        Edge actual = priorityQueue.get();

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSmallestValueWhen_ThereAreTwoIntegersInQueue() {
        // given
        PriorityQueue priorityQueue = new PriorityQueue();
        Edge bigValue = new Edge("A", "B", 3);
        Edge smallValue = new Edge("B", "C", 2);

        // when
        priorityQueue.put(bigValue);
        priorityQueue.put(smallValue);
        Edge actual = priorityQueue.get();

        // then
        assertEquals(smallValue, actual);
    }
}
