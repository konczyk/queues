import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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

}
