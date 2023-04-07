import java.lang.reflect.Array;
import java.util.*;

public class MyArrayList<T> implements List<T> {
    public static final int capacity = 10;
    private T[] array;
    private int size;

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be more than 0! Your capacity:" + capacity);
        }

        array = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void ensureCapacity(int capacity) {
        if (capacity > array.length) {
            int newCapacity = array.length * 2;

            if (newCapacity < capacity) {
                newCapacity = capacity;
            }

            array = Arrays.copyOf(array, newCapacity);
        }
    }

    @Override
    public void trimToSize(int size) {
        if (size < array.length) {
            T[] newArray = (T[]) new Object[size];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    List x = new List() {
        @Override
        public void ensureCapacity(int size) {

        }

        @Override
        public void trimToSize(int size) {

        }

    };

}
