import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A randomized queue
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
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

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }

        // enlarge array if necessary
        if (size == items.length) {
            resize(2 * items.length);
        }

        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Dequeue from empty queue");
        }

        int randomItemIndex = rand.nextInt(size);
        Item item = items[randomItemIndex];

        // swap item with the last one and remove
        items[randomItemIndex] = items[size-1];
        items[size-1] = null;
        size--;

        // shrink array if necessary
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }

        return item;
    }

    private void resize(int newCapacity) {
        Item[] tmp = (Item[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            tmp[i] = items[i];
        }

        items = tmp;
    }

    // return a random item from the queue
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Sample from empty queue");
        }

        return items[rand.nextInt(size)];
    }

    // return an iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private Item[] getShuffledItems() {
        Item[] shuffled = (Item[]) new Object[size];

        for (int i = 0; i < size; i++) {
            shuffled[i] = items[i];
        }

        // shuffle contents, uniformly at random
        for (int i = 0; i < size; i++) {
            int idx = i + rand.nextInt(size - i);
            Item temp = shuffled[i];
            shuffled[i] = shuffled[idx];
            shuffled[idx] = temp;
        }

        return shuffled;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] iterItems;
        private int iterSize;
        private int current;

        public RandomizedQueueIterator() {
            iterItems = getShuffledItems();
            iterSize = size;
            current = 0;
        }

        public boolean hasNext() {
            return current < iterSize;
        }

        public void remove() {
            throw new UnsupportedOperationException("Operation not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next item does not exist");
            }

            return iterItems[current++];
        }

    }
}
