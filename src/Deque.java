import java.util.Iterator;
import java.util.NoSuchElementException;

// A double-ended queue
public class Deque<Item> implements Iterable<Item> {
    // size of deque
    private int size;
    // front of deque
    private Node first;
    // end of deque
    private Node last;

    // class representing deque element
    private class Node {
        private Item item;
        private Node next;
    }

    // check if deque is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // return number of items in the deque
    public int size() {
        return size;
    }

    // add item at the front of the deque
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null");

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty())
            last = first;

        size++;
    }

    // add item at the tail of the deque
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null");

        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;

        size++;
    }

    // remove and return item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove from empty deque");
        return null;
    }

    // remove and return item from the end
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
        Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Operation not supported");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Next item does not exist");
            Item item = current.item;
            current = current.next;

            return item;
        }
    }
}
