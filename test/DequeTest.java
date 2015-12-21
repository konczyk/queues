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

    @Test
    public void removesFirst() {
        deque.addFirst(5);
        deque.addFirst(8);

        assertEquals(Integer.valueOf(8), deque.removeFirst());
        assertEquals(1, deque.size());

        Iterator<Integer> it = deque.iterator();
        assertEquals(Integer.valueOf(5), it.next());
    }

    @Test
    public void removesLast() {
        deque.addFirst(5);
        deque.addFirst(8);

        assertEquals(Integer.valueOf(5), deque.removeLast());
        assertEquals(1, deque.size());

        Iterator<Integer> it = deque.iterator();
        assertEquals(Integer.valueOf(8), it.next());
    }

    @Test
    public void clearsAndAdds() {
        // add items
        deque.addFirst(5);
        deque.addLast(10);
        assertEquals(2, deque.size());

        // remove items
        assertEquals(Integer.valueOf(5), deque.removeFirst());
        assertEquals(Integer.valueOf(10), deque.removeLast());
        assertEquals(0, deque.size());

        // add item
        deque.addFirst(5);
        assertEquals(1, deque.size());
        Iterator<Integer> it = deque.iterator();
        assertEquals(Integer.valueOf(5), it.next());
    }

    @Test
    public void handlesIndependentIterators() {
        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);

        Iterator<Integer> it1 = deque.iterator();
        Iterator<Integer> it2 = deque.iterator();

        // first iterator starts
        assertEquals(Integer.valueOf(30), it1.next());
        assertEquals(Integer.valueOf(20), it1.next());

        // second interator starts
        assertEquals(Integer.valueOf(30), it2.next());

        // firts iterator continues
        assertEquals(Integer.valueOf(10), it1.next());
    }

}
