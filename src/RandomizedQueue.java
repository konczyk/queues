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

    // the items array will contain only Item instances
    @SuppressWarnings("unchecked")
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
            throw new NullPointerException("item cannot be null");
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
            throw new NoSuchElementException("dequeue from empty queue");
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

    // the tmp array will contain only Item instances copied from the items
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Item[] tmp = (Item[]) new Object[newCapacity];
        System.arraycopy(items, 0, tmp, 0, size);
        items = tmp;
    }

    // return a random item from the queue
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("sample from empty queue");
        }

        return items[rand.nextInt(size)];
    }

    // return an iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void shuffle(Item[] input) {
        // shuffle contents, uniformly at random
        for (int i = 0; i < size; i++) {
            int idx = i + rand.nextInt(size - i);
            Item temp = input[i];
            input[i] = input[idx];
            input[idx] = temp;
        }
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] iterItems;
        private final int iterSize;
        private int current;

        public RandomizedQueueIterator() {
            iterItems = items.clone();
            shuffle(iterItems);
            iterSize = size;
            current = 0;
        }

        public boolean hasNext() {
            return current < iterSize;
        }

        public void remove() {
            throw new UnsupportedOperationException("operation not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("next item does not exist");
            }

            return iterItems[current++];
        }

    }
}
