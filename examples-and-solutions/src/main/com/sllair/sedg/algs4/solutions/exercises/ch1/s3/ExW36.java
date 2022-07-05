package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExW36 {
    public static class StackStequeDeque<Item> implements Iterable<Item> {

        private final Stack<Item> left = new Stack<>();
        private final Ex32.Steque<Item> right = new Ex32.Steque<>();

        private void rebalanceFromStequeToStack(Ex32.Steque<Item> full, Stack<Item> empty) {
            int targetFullSize = full.size() / 2;

            /*
             * We need to get the bottom half of the steque onto the stack
             * in reverse order.  But we also need to keep the top half of the
             * steque on the steque its original order.
             */

            /*
             *  We transfer the steque's intended final half so
             *  we can reach the stack's intended final half.
             *  This has the complication of reversing the order of
             *  the steque's intended final half.
             *
             */
            for (int i = 0; i < targetFullSize; i++) {
                empty.push(full.pop());
            }

            /*
             * We transfer the steque's intended final half to its
             * bottom by enqueueing. Unfortunately, this preserves
             * the reversed order.
             */
            while (!empty.isEmpty()) {
                full.enqueue(empty.pop());
            }

            /*
             * We transfer everything to the stack.  The stack's
             * intended final half is on the bottom in the correct order.
             * The steque's intended final half is on top in the
             * correct order.
             */
            while (!full.isEmpty()) {
                empty.push(full.pop());
            }

            /*
             * We enqueue the steque's final intended half from
             * the stack. This preserves the correct order.
             */
            for (int i = 0; i < targetFullSize; i++) {
                full.enqueue(empty.pop());
            }

        }

        private void rebalanceFromStackToSteque(Stack<Item> full, Ex32.Steque<Item> empty) {
            int targetFullSize = full.size() / 2;
            int targetEmptySize = full.size() - targetFullSize;

            /*
             * This time we need to get the bottom half of the stack onto the steque
             * in reverse order while preserving the order of the top half of the stack.
             */

            /*
             * Get the stack's intended final half on the steque in the reverse of the
             * intended final order by pushing
             */
            for (int i = 0; i < targetFullSize; i++) {
                empty.push(full.pop());
            }

            /*
             * Get the steque's intended final half on the bottom of steque but
             * in the reverse of the intended final order by enqueueing.
             */
            while (!full.isEmpty()) {
                empty.enqueue(full.pop());
            }

            /*
             * Get everything on the stack by pushing. The stack's intended half
             * is on the bottom in its intended order.  The steque's inteded half
             * is on top in its intended order.
             */
            while (!empty.isEmpty()) {
                full.push(empty.pop());
            }

            /*
             * Get the steque's intended half on the steque in the intending order
             * by enqueueing.
             */

            for (int i = 0; i < targetEmptySize; i++) {
                empty.enqueue(full.pop());
            }

        }

        public boolean isEmpty() {
            return this.left.isEmpty() && this.right.isEmpty();
        }

        public int size() {
            return this.left.size() + this.right.size();
        }

        public void pushLeft(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Cannot push a null item!");
            }
            this.left.push(item);
        }

        public void pushRight(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Cannot push a null item!");
            }
            this.right.push(item);
        }

        public Item popLeft() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty deque!"
                );
            }
            if (this.left.isEmpty()) {
                if (this.size() > 1) {
                    this.rebalanceFromStequeToStack(this.right, this.left);
                } else {
                    return this.right.pop();
                }
            }

            return this.left.pop();
        }

        public Item popRight() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty deque!"
                );
            }
            if (this.right.isEmpty()) {
                if (this.size() > 1) {
                    this.rebalanceFromStackToSteque(this.left, this.right);
                } else {
                    return this.left.pop();
                }
            }

            return this.right.pop();
        }

        public Item peekLeft() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot peek into an empty deque!"
                );
            }
            if (this.left.isEmpty()) {
                if (this.size() > 1) {
                    this.rebalanceFromStequeToStack(this.right, this.left);
                } else {
                    return this.right.peek();
                }
            }

            return this.left.peek();
        }

        public Item peekRight() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot peek into an empty deque!"
                );
            }
            if (this.right.isEmpty()) {
                if (this.size() > 1) {
                    this.rebalanceFromStackToSteque(this.left, this.right);
                } else {
                    return this.left.peek();
                }
            }

            return this.right.peek();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (Item item : this) {
                sb.append(item);
                sb.append(' ');
            }
            sb.append(']');

            return sb.toString();
        }

        public Iterator<Item> iterator() {
            return new StackStequeDequeIterator<>(this);
        }

        private static class StackStequeDequeIterator<Item> implements Iterator<Item> {

            private final Iterator<Item> leftIterator;
            private final Iterator<Item> reverseRightIterator;

            public StackStequeDequeIterator(StackStequeDeque<Item> deque) {
                this.leftIterator = deque.left.iterator();
                Stack<Item> tmp = new Stack<>();
                for (Item item : deque.right) {
                    tmp.push(item);
                }
                this.reverseRightIterator = tmp.iterator();
            }

            public boolean hasNext() {
                return leftIterator.hasNext() || reverseRightIterator.hasNext();
            }

            public Item next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                return this.leftIterator.hasNext() ? this.leftIterator.next() : this.reverseRightIterator.next();
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }
    }

    public static void main(String[] args) {
        StackStequeDeque<Integer> deque = new StackStequeDeque<>();
        StdOut.println(deque);

        for (int i = 0; i < 20; i++) {
            StdOut.println(String.format("pushRight %d", i));
            deque.pushRight(i);
            StdOut.println(deque);
        }

        StdOut.println(String.format("popLeft %d", deque.popLeft()));
        StdOut.println(deque);

        for (int i = 20; i < 40; i++) {
            StdOut.println(String.format("pushLeft %d", i));
            deque.pushLeft(i);
            StdOut.println(deque);
        }

        for (int i = 0; i < 11; i++) {
            StdOut.println(String.format("popRight %d", deque.popRight()));
            StdOut.println(deque);
        }

        while (!deque.isEmpty()) {
            StdOut.println(String.format("popLeft %d", deque.popLeft()));
            StdOut.println(deque);
        }

        StdOut.println(String.format("pushLeft %d", 0));
        deque.pushLeft(0);
        StdOut.println(deque);

        StdOut.println(String.format("popRight %d", deque.popRight()));
        StdOut.println(deque);

        StdOut.println(String.format("pushRight %d", 0));
        deque.pushRight(0);
        StdOut.println(deque);

        StdOut.println(String.format("popLeft %d", deque.popLeft()));
        StdOut.println(deque);

        for (int i = 0; i < 10; i++) {
            StdOut.println(String.format("pushLeft %d", i));
            deque.pushLeft(i);
            StdOut.println(deque);
        }

        while (!deque.isEmpty()) {
            StdOut.println(String.format("popRight %d", deque.popRight()));
            StdOut.println(deque);
        }
    }
}
