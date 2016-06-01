import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DequeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addFirstNullThrowsException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("item cannot be null");

        new Deque<>().addFirst(null);
    }

    @Test
    public void addLastNullThrowsException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("item cannot be null");

        new Deque<>().addLast(null);
    }

    @Test
    public void removeFirstWhenEmptyThrowsException() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("cannot remove from empty deque");

        new Deque<>().removeFirst();
    }

    @Test
    public void removeLastWhenEmptyThrowsException() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("cannot remove from empty deque");

        new Deque<>().removeLast();
    }

    @Test
    public void removeFromIteratorThrowsException() {
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("operation not supported");

        new Deque<>().iterator().remove();
    }

    @Test
    public void nextOnExhaustedIteratorThrowsException() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("next item does not exist");

        new Deque<>().iterator().next();
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
