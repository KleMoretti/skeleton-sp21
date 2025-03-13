package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    class Node {
        T item;
        Node pre;
        Node last;

        public Node() {
            this.item = null;
        }

        public Node(T item) {
            this.item = item;
        }
    }


    private Node sentinel;
    private Node last;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node();
        last = new Node();
        sentinel.last = last;
        last.pre = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node();
        last = new Node();
        Node node = new Node(item);
        sentinel.last = node;
        node.pre = sentinel;
        node.last = last;
        last.pre = node;
        size++;
    }

    @Override
    public void addFirst(T item) {
        Node node = new Node(item);
        node.last = sentinel.last;
        sentinel.last = node;
        node.pre = sentinel;
        node.last.pre = node;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node node = new Node(item);
        last.pre = node.pre;
        node.pre.last = node;
        node.last = last;
        last.pre = node;
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
        Node node = sentinel.last;
        while (node.last != last) {
            System.out.print(node.item + " ");
            node = node.last;
        }
        System.out.println();

    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.last.item;
        sentinel.last = sentinel.last.last;
        sentinel.last.pre = sentinel;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = last.pre.item;
        last.pre = last.pre.pre;
        last.pre.last = last;
        size--;
        return item;
    }

    @Override
    public T get(int index) {
        if (index > size || index <= 0) {
            return null;
        }
        Node node = sentinel;
        while (index > 0) {
            node = node.last;
            index--;
        }
        return node.item;
    }

    @Override
    public Iterable<T> iterable() {
        return null;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.last, index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.last, index - 1);
    }


    @Override
    public Iterator<T> iterator() {
        class LinkedListDequeIterator implements Iterator<T> {
            private Node current = sentinel.last;

            @Override
            public boolean hasNext() {
                return current != last;
            }

            @Override
            public T next() {
                T item = current.item;
                current = current.last;
                return item;
            }
        }
        return new LinkedListDequeIterator();
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }
        Node node1 = sentinel;
        Node node2 = other.sentinel;
        while (node1.last != last) {
            if (node1.item != node2.item) {
                return false;
            }
            node1 = node1.last;
            node2 = node2.last;
        }
        return true;
    }
}
