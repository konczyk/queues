import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // array of items
    private Item[] items;
    // number of items
    private int size;

    private final Random rand = new Random();

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // add item to the queue
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null");

        // enlarge array if necessary
        if (size == items.length)
            resize(2 * items.length);

        // add item
        items[size++] = item;
    }

    // remove and return a random item from the queue
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Dequeue from empty queue");

        // get random item from the queue
        int idx = rand.nextInt(size);
        Item item = items[idx];

        // swap item with the last one and remove
        items[idx] = items[size-1];
        items[size-1] = null;
        size--;

        // shrink array if necessary
        if (size > 0 && size == items.length / 4)
            resize(items.length/2);

        return item;
    }

    private void resize(int capacity) {
        // temp array with new capacity
        Item[] tmp = (Item[]) new Object[capacity];
        // copy items
        for (int i = 0; i < size; i++)
            tmp[i] = items[i];
        items = tmp;
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
