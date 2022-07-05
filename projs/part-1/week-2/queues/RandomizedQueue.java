import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int n;
    private int first;
    private int last;

    public RandomizedQueue() {
        this.q = (Item[]) new Object[2];
        this.n = 0;
        this.first = 0;
        this.last = 0;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public int size() {
        return this.n;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot enqueue a null item!");
        }
        if (this.q.length == this.n) {
            this.resize(2 * this.q.length);
        }
        this.q[this.last++] = item;
        if (this.last == this.q.length) {
            this.last = 0;
        }
        this.n++;
    }

    private static int getRandomIndex(
            int localN, int localFirst, int localQlength) {
        return (StdRandom.uniform(localN) + localFirst) % localQlength;
    }

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(
                    "Cannot dequeue an item from an empty queue!"
            );
        }
        int randomIndex = getRandomIndex(this.n, this.first, this.q.length);
        Item result = this.q[randomIndex];
        this.q[randomIndex] = this.q[first];
        this.q[this.first++] = null;
        this.n--;
        if (this.first == this.q.length) {
            this.first = 0;
        }
        if (this.n > 0 && this.n <= this.q.length / 4) {
            this.resize(this.q.length / 2);
        }
        return result;
    }

    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(
                    "Cannot sample an item from an empty queue!"
            );
        }
        return this.q[getRandomIndex(this.n, this.first, this.q.length)];
    }

    private Item[] copy(int newSize) {
        Item[] newQ = (Item[]) new Object[newSize];
        for (int i = 0; i < this.n; i++) {
            newQ[i] = this.q[(this.first + i) % this.q.length];
        }

        return newQ;
    }

    private void resize(int newSize) {
        this.q = copy(newSize);
        this.first = 0;
        this.last = this.n;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>(this);
    }

    private static class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private Item[] q;
        private int n;
        private int first;

        public RandomizedQueueIterator(RandomizedQueue<Item> queue) {
            this.q = queue.copy(queue.n);
            this.n = queue.n;
            this.first = 0;
        }

        public boolean hasNext() {
            return this.n > 0;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException(
                        "This iterator is out of items!"
                );
            }
            int randomIndex = getRandomIndex(n, first, this.q.length);
            Item result = q[randomIndex];
            q[randomIndex] = q[this.first];
            q[this.first++] = result;
            this.n--;

            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "Sedgewick does not believe in iterators removing items!"
            );
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        println(queue);

        for (int i = 0; i < 20; i++) {
            StdOut.println(String.format("enqueue %d", i));
            queue.enqueue(i);
            println(queue);
        }

        StdOut.println(String.format("sample %d", queue.sample()));

        while (!queue.isEmpty()) {
            StdOut.println(String.format(
                    "dequeue %d", queue.dequeue())
            );
            println(queue);
        }
    }

    private static <Item> void println(RandomizedQueue<Item> queue) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (Item item : queue) {
            sb.append(item);
            sb.append(' ');
        }
        sb.append(']');
        StdOut.println(sb);
        StdOut.println(String.format("size: %d", queue.size()));
    }
}
