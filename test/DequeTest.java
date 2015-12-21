import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;
import java.util.NoSuchElementException;

@RunWith(JUnit4.class)
public class DequeTest {

    private Deque<Integer> deque;

    @Before
    public void setUp() {
        deque = new Deque<>();
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionWhenAddingNullAtFront() {
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionWhenAddingNullAtTail() {
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionWhenRemovingFromEmptyDequeHead() {
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionWhenRemovingFromEmptyDequeTail() {
        deque.removeLast();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throwsExceptionWhenRemovingFromIterator() {
        deque.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionWhenCallingNextWithNoMoreItems() {
        deque.iterator().next();
    }

    @Test
    public void addsFirst() {
        deque.addFirst(5);
        deque.addFirst(8);

        assertEquals(2, deque.size());

        Iterator<Integer> it = deque.iterator();
        assertEquals(Integer.valueOf(8), it.next());
        assertEquals(Integer.valueOf(5), it.next());
    }

    @Test
    public void addsLast() {
        deque.addLast(5);
        deque.addLast(8);

        assertEquals(2, deque.size());

        Iterator<Integer> it = deque.iterator();
        assertEquals(Integer.valueOf(5), it.next());
        assertEquals(Integer.valueOf(8), it.next());
    }

}
