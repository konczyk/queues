import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;
import java.util.NoSuchElementException;

@RunWith(JUnit4.class)
public class RandomizedQueueTest {

    private RandomizedQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new RandomizedQueue<>();
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionWhenEnqueueNull() {
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionWhenDequeueFromEmptyQueue() {
        queue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionWhenSampleFromEmptyQueue() {
        queue.sample();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throwsExceptionWhenRemovingFromIterator() {
        queue.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionWhenCallingNextWithNoMoreItems() {
        queue.iterator().next();
    }

    @Test
    public void enqueues() {
        queue.enqueue(5);

        assertEquals(1, queue.size());
    }

    @Test
    public void dequeues() {
        queue.enqueue(5);

        assertEquals(Integer.valueOf(5), queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    public void samples() {
        queue.enqueue(5);

        assertEquals(Integer.valueOf(5), queue.sample());
        assertEquals(1, queue.size());
    }

}
