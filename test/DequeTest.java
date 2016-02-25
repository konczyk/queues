import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;
import java.util.NoSuchElementException;

@RunWith(JUnit4.class)
public class DequeTest {

    @Test(expected = NullPointerException.class)
    public void throwExceptionWhenAddingNullAtFront() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void throwExceptionWhenAddingNullAtTail() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenRemovingFromEmptyDequeHead() {
        Deque<Integer> deque = new Deque<>();
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenRemovingFromEmptyDequeTail() {
        Deque<Integer> deque = new Deque<>();
        deque.removeLast();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throwExceptionWhenRemovingFromIterator() {
        Deque<Integer> deque = new Deque<>();
        deque.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenCallingNextWithNoMoreItems() {
        Deque<Integer> deque = new Deque<>();
        deque.iterator().next();
    }

    @Test
    public void addFirst() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(5);
        deque.addFirst(8);
        Iterator<Integer> it = deque.iterator();

        assertThat(deque.size(), is(2));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(5));
    }

    @Test
    public void addLast() {
        Deque<Integer> deque = new Deque<>();

        deque.addLast(5);
        deque.addLast(8);
        Iterator<Integer> it = deque.iterator();

        assertThat(deque.size(), is(2));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(8));
    }

    @Test
    public void removeFirst() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(5);
        deque.addFirst(8);

        assertThat(deque.removeFirst(), is(8));
        assertThat(deque.size(), is(1));
        assertThat(deque.iterator().next(), is(5));
    }

    @Test
    public void removeLast() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(5);
        deque.addFirst(8);

        assertThat(deque.removeLast(), is(5));
        assertThat(deque.size(), is(1));
        assertThat(deque.iterator().next(), is(8));
    }

    @Test
    public void clearAndAdd() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(5);
        deque.removeFirst();
        deque.addFirst(8);

        assertThat(deque.size(), is(1));
        assertThat(deque.iterator().next(), is(8));
    }

    @Test
    public void handleIndependentIterators() {
        Deque<Integer> deque = new Deque<>();

        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        Iterator<Integer> it1 = deque.iterator();
        Iterator<Integer> it2 = deque.iterator();

        assertThat(it1.next(), is(1));
        assertThat(it1.next(), is(2));
        assertThat(it2.next(), is(1));
        assertThat(it1.next(), is(3));
    }

}
