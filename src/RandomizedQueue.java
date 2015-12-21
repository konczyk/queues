import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    public RandomizedQueue() {
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    // add item to the queue
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null");
    }

    // remove and return a random item from the queue
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Dequeue from empty queue");

        return null;
    }

    // return a random item from the queue
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Sample from empty queue");

        return null;
    }

    // return an iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        public boolean hasNext() {
            return false;
        }

        public void remove() {
            throw new UnsupportedOperationException("Operation not supported");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Next item does not exist");

            return null;
        }

    }
}
