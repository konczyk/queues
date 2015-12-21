import java.util.Iterator;
import java.util.NoSuchElementException;

// A double-ended queue
public class Deque<Item> implements Iterable<Item> {

    public Deque() {
    }

    // check if deque is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // return number of items in the deque
    public int size() {
        return 0;
    }

    // add item at the front of the deque
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null");
    }

    // add item at the tail of the deque
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null");
    }

    // remove and return head element
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove from empty deque");
        return null;
    }

    // remove and return tail element
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove from empty deque");
        return null;
    }

    // return iterator over elements from head to tail
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
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
