import java.util.Iterator;
import java.util.Set;
import java.util.Collection;


public class CustomSet<T> implements Set<T>{
    private static final int INITIAL_CAPACITY = 15;
    private static final double GROWTH = 0.30;

    private T[] elements;
    private int size;


    /**
     * Конструктор порожній
     *
     */
    public CustomSet() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Конструктор для 1 об'єкту
     *
     * @param element
     */
    public CustomSet(T element) {
        this();
        if (element != null) {
            this.add(element);
        }
    }

    /**
     * Конструктор для колекції об'єктів
     *
     * @param collection
     */
    public CustomSet(Collection<? extends T> collection) {
        this();
        if (collection != null) {
            this.addAll(collection);
        }
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }

        if (this.contains(element)) {
            return false;
        }

        if (size == elements.length) {
            grow();
        }

        elements[size] = element;
        size++;
        return true;
    }

    private void grow() {
        int newCapacity = elements.length + (int) (elements.length  * GROWTH);
        if (newCapacity <= elements.length) {
            newCapacity = elements.length + 1;
        }

        T[] newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        this.elements = newElements;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        this.size = 0;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T element : c) {
            if (this.add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return elements[currentIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            return (E[]) java.util.Arrays.copyOf(elements, size, a.getClass());
        }

        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        int indexToRemove = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                indexToRemove = i;
                break;
            };
        }

        if (indexToRemove == -1) {
            return false;
        }

        int numMoved = size - indexToRemove - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, indexToRemove + 1, elements, indexToRemove, numMoved);
        }

        elements[--size] = null;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        boolean modified = false;
        for (Object element : c) {
            if (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        boolean modified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                this.remove(elements[i]);
                modified = true;
            }
        }
        return modified;
    }
}
