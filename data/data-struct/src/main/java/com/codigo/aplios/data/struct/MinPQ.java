package com.codigo.aplios.data.struct;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The {@code MinPQ} class represents a priority queue of generic keys. It
 * supports the usual <em>insert</em> and <em>delete-the-minimum</em>
 * operations, along with methods for peeking at the minimum key, testing if the
 * priority queue is empty, and iterating through the keys.
 * <p>
 * This implementation uses a binary heap. The <em>insert</em> and
 * <em>delete-the-minimum</em> operations take logarithmic amortized time. The
 * <em>min</em>, <em>size</em>, and <em>is-empty</em> operations take constant
 * time. Construction takes time proportional to the specified capacity or the
 * number of items used to initialize the data structure.
 * <p>
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 * @param <E>
 *            the generic type of key on this priority queue
 */
/**
 */
// Opis:
// http://wazniak.mimuw.edu.pl/index.php?title=Algorytmy_i_struktury_danych/Kolejki_priorytetowe#Drzewa_i_kolejki_dwumianowe
public class MinPQ<E>
        implements Iterable<E> {

    public static void main(final String[] args) {

        final MinPQ<String> data = new MinPQ<>(String.class,
                10);
        data.insert("12312");
        data.insert("123123");
        data.insert("ABC");
        data.insert("0123");
        data.insert("XYZ");
        data.resize(10);

    }

    private E[] pq;			// store items at indices 1 to n

    private int n;			// number of items on priority queue

    private Comparator<E> comparator;	// optional comparator

    private Class<E> type;		// typ parametru szablonu klasy

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity
     *                     the initial capacity of this priority queue
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public MinPQ(final Class<E> type, final int initCapacity) {

        this.type = type;

        try {
            this.pq = MinPQ.getInstance(this.type, initCapacity + 1);
        } catch (final IllegalArgumentException e) {
            e.printStackTrace();
        }
        this.n = 0;
    }

    /**
     * Initializes an empty priority queue.
     *
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public MinPQ(final Class<E> type) {

        this(type,
                1);
    }

    private static <E> E[] getInstance(final Class<E> type, final int size) {

        return (E[])Array.newInstance(type, size);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity, using
     * the given comparator.
     *
     * @param initCapacity
     *                     the initial capacity of this priority queue
     * @param comparator
     *                     the order to use when comparing keys
     */
    public MinPQ(final int initCapacity, final Comparator<E> comparator) {

        this.comparator = comparator;
        this.pq = MinPQ.getInstance(this.type, initCapacity + 1);
        this.n = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator
     *                   the order to use when comparing keys
     */
    public MinPQ(final Comparator<E> comparator) {

        this(1,
                comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * <p>
     * Takes time proportional to the number of keys, using sink-based heap
     * construction.
     *
     * @param keys
     *             the array of keys
     */
    public MinPQ(final E[] keys) {

        this.n = keys.length;
        this.pq = MinPQ.getInstance(this.type, keys.length + 1);

        for (int i = 0; i < this.n; i++)
            this.pq[i + 1] = keys[i];

        for (int k = this.n / 2; k >= 1; k--)
            this.sink(k);

        assert this.isMinHeap();
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {

        return this.n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {

        return this.n;
    }

    /**
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException
     * if this priority queue is empty
     */
    public E min() {

        if (this.isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        return this.pq[1];
    }

    // helper function to double the size of the heap array
    private void resize(final int capacity) {

        assert capacity > this.pq.length;
        // tablica rezerwowana jest w pamięci stack
        final E[] array = MinPQ.getInstance(this.type, capacity);

        System.arraycopy(this.pq, 0, array, 0, this.pq.length);

        // kasowanie referencji do obiektów w pamięci heap
        Arrays.fill(array, null);
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param x
     *          the key to add to this priority queue
     */
    public void insert(final E x) {

        // double size of array if necessary
        if (this.n == this.pq.length - 1)
            this.resize(2 * this.pq.length);

        // add x, and percolate it up to maintain heap invariant
        this.pq[++this.n] = x;
        this.swim(this.n);
        assert this.isMinHeap();
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException
     * if this priority queue is empty
     */
    public E deleteMin() {

        if (this.isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        this.exchange(1, this.n);
        final E min = this.pq[this.n--];
        this.sink(1);
        this.pq[this.n + 1] = null; // avoid loitering and help with garbage collection
        if (this.n > 0 && this.n == (this.pq.length - 1) / 4)
            this.resize(this.pq.length / 2);
        assert this.isMinHeap();
        return min;
    }

    /**
     * *************************************************************************
     * Helper functions to restore the heap invariant.
     **************************************************************************
     */
    private void swim(int k) {

        while (k > 1 && this.greater(k / 2, k)) {
            this.exchange(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {

        while (2 * k <= this.n) {
            int j = 2 * k;
            if (j < this.n && this.greater(j, j + 1))
                j++;
            if (!this.greater(k, j))
                break;
            this.exchange(k, j);
            k = j;
        }
    }

    /**
     * *************************************************************************
     * Helper functions for compares and swaps.
     **************************************************************************
     */
    private boolean greater(final int i, final int j) {

        if (this.comparator == null)
            return ((Comparable<E>)this.pq[i]).compareTo(this.pq[j]) > 0;
        else
            return this.comparator.compare(this.pq[i], this.pq[j]) > 0;
    }

    private void exchange(final int i, final int j) {

        final E swap = this.pq[i];
        this.pq[i] = this.pq[j];
        this.pq[j] = swap;
    }

    // is pq[1..N] a min heap?
    private boolean isMinHeap() {

        return this.isMinHeap(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeap(final int k) {

        if (k > this.n)
            return true;

        final int left = 2 * k;
        final int right = 2 * k + 1;

        if (left <= this.n && this.greater(k, left))
            return false;

        if (right <= this.n && this.greater(k, right))
            return false;

        return this.isMinHeap(left) && this.isMinHeap(right);
    }

    /**
     * Returns an iterator that iterates over the keys on this priority queue in
     * ascending order.
     * <p>
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    @Override
    public Iterator<E> iterator() {

        return new HeapIterator(this.type);
    }

    private class HeapIterator
            implements Iterator<E> {

        // create a new pq
        private MinPQ<E> copy;

        private final Class<E> type;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator(final Class<E> type) {

            this.type = type;

            if (Objects.isNull(MinPQ.this.comparator))
                this.copy = new MinPQ<>(this.type,
                        MinPQ.this.size());
            else
                this.copy = new MinPQ<>(MinPQ.this.size(),
                        MinPQ.this.comparator);

            for (int i = 1; i <= MinPQ.this.n; i++)
                this.copy.insert(MinPQ.this.pq[i]);

        }

        /*
         * (non-Javadoc)
         *
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {

            return !this.copy.isEmpty();
        }

        /*
         * (non-Javadoc)
         *
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() {

            throw new UnsupportedOperationException();
        }

        /*
         * (non-Javadoc)
         *
         * @see java.util.Iterator#next()
         */
        @Override
        public E next() {

            if (!this.hasNext())
                throw new NoSuchElementException();

            return this.copy.deleteMin();
        }

    }
}
