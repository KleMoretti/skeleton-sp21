package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        return getMax(this.comparator);
    }

    public T max(Comparator<T> c) {
        return getMax(c);
    }

    public T getMax(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T t = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T temp = this.get(i);
            if (c.compare(temp, t) > 0) {
                t = temp;
            }
        }
        return t;
    }
}
