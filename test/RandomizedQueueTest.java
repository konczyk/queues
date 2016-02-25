import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.NoSuchElementException;

@RunWith(JUnit4.class)
public class RandomizedQueueTest {

    @Test(expected = NullPointerException.class)
    public void throwExceptionWhenEnqueueNull() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenDequeueFromEmptyQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenSampleFromEmptyQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.sample();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throwExceptionWhenRemovingFromIterator() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwExceptionWhenCallingNextWithNoMoreItems() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.iterator().next();
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
