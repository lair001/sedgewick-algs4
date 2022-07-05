import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public Deque() {

    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item!");
        }
        Node<Item> newHead = new Node<>();
        newHead.item = item;
        newHead.next = this.head;
        if (isEmpty()) {
            this.tail = newHead;
        }
        else {
            this.head.prev = newHead;
        }
        this.head = newHead;
        this.size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item!");
        }
        Node<Item> newTail = new Node<>();
        newTail.item = item;
        newTail.prev = this.tail;
        if (this.isEmpty()) {
            this.head = newTail;
        }
        else {
            this.tail.next = newTail;
        }
        this.tail = newTail;
        this.size++;
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(
                    "Cannot remove an item from an empty deque!"
            );
        }
        Item result = this.head.item;
        this.head = this.head.next;
        if (this.isEmpty()) {
            this.tail = null;
        }
        else {
            this.head.prev = null;
        }
        this.size--;
        return result;
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(
                    "Cannot remove an item from an empty deque!"
            );
        }
        Item result = this.tail.item;
        this.tail = this.tail.prev;
        if (this.tail == null) {
            this.head = null;
        }
        else {
            this.tail.next = null;
        }
        this.size--;
        return result;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator<>(this);
    }

    private static class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> next;

        public DequeIterator(Deque<Item> deque) {
            this.next = deque.head;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException(
                        "This iterator is out of items!"
                );
            }
            Item result = next.item;
            this.next = this.next.next;
            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "Sedgewick does not believe in iterators removing items!"
            );
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        println(deque);

        for (int i = 0; i < 20; i++) {
            if (deque.size() % 2 == 0) {
                StdOut.println(String.format("addFirst %d", i));
                deque.addFirst(i);
            }
            else {
                StdOut.println(String.format("addLast %d", i));
                deque.addLast(i);
            }
            println(deque);
        }

        while (!deque.isEmpty()) {
            if (deque.size() % 2 == 0) {
                StdOut.println(String.format(
                        "removeFirst %d", deque.removeFirst())
                );
            }
            else {
                StdOut.println(String.format(
                        "removeLast %d", deque.removeLast())
                );
            }
            println(deque);
        }
    }

    private static <Item> void println(Deque<Item> deque) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (Item item : deque) {
            sb.append(item);
            sb.append(' ');
        }
        sb.append(']');
        StdOut.println(sb);
        StdOut.println(String.format("size: %d", deque.size()));
    }

}
