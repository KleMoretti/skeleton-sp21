package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (T[]) new Object[8];
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;

    }

    private void resize(int i) {
        T[] a = (T[]) new Object[i];
        int start = (nextFirst + 1) % items.length;
        for (int j = 0; j < size; j++) {
            a[j] = items[start];
            start = (start + 1) % items.length;
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int start = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            System.out.print(items[start] + " ");
            start = (start + 1) % items.length;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int starrt = (nextFirst + 1) % items.length;
        T item = items[starrt];
        items[starrt] = null;
        nextFirst = starrt;
        size--;
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = (nextLast - 1 + items.length) % items.length;
        T item = items[last];
        items[last] = null;
        nextLast = last;
        size--;
        if (size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (index > items.length) {
            return null;
        }
        int start = (nextFirst + 1) % items.length;
        return items[(start + index) % items.length];
    }

    @Override
    public Iterable<T> iterable() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        class ArrayDequeIterator implements Iterator<T> {
            private int current = (nextFirst + 1) % items.length;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                T item = items[current];
                current = (current + 1) % items.length;
                count++;
                return item;
            }
        }
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayDeque<?> that = (ArrayDeque<?>) o;
        if (this.size != that.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(that.get(i))) {
                return false;
            }
        }
        return true;
    }
}

