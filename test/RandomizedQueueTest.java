import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RandomizedQueueTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void enqueueNullThrowsException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("item cannot be null");

        new RandomizedQueue<>().enqueue(null);
    }

    @Test
    public void dequeueFromEmptyQueueThrowsException() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("dequeue from empty queue");

        new RandomizedQueue<>().dequeue();
    }

    @Test
    public void sampleFromEmptyQueueThrowsException() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("sample from empty queue");

        new RandomizedQueue<>().sample();
    }

    @Test
    public void removeFromIteratorThrowsException() {
        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("operation not supported");

        new RandomizedQueue<>().iterator().remove();
    }

    @Test
    public void nextOnExhaustedIteratorThrowsException() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("next item does not exist");

        new RandomizedQueue<>().iterator().next();
    }

    @Test
    public void enqueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(5);

        assertThat(queue.size(), is(1));
        assertThat(queue.iterator().next(), is(5));
    }

    @Test
    public void dequeue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(5);

        assertThat(queue.dequeue(), is(5));
        assertThat(queue.size(), is(0));
    }

    @Test
    public void sample() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        int s1 = 0, s2 = 0;
        for (int i = 0; i < 100; ++i) {
            int sample = queue.sample();
            if (sample == 1) {
                s1++;
            } else if (sample == 2) {
                s2++;
            }
        }

        assertThat(queue.size(), is(2));
        assertThat(s1, greaterThan(0));
        assertThat(s2, greaterThan(0));
    }

}
