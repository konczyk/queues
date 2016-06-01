import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue
 */
public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first;
    private Node last;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // class representing deque element
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }

        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
            last.prev = oldLast;
        }

        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot remove from empty deque");
        }

        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }

        size--;

        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("cannot remove from empty deque");
        }

        Item item = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }

        size--;

        return item;
    }

    // return iterator over elements from head to tail
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("operation not supported");
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("next item does not exist");
            }

            Item item = current.item;
            current = current.next;

            return item;
        }
    }
}
