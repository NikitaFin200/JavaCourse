public class MyArrayList<T> implements List<T> {
    public static final int capacity = 10;
    private Object[] array;
    private int size;

    public MyArrayList(int capacity) {
        array = new Object[capacity];
        size = 0;
    }


}
