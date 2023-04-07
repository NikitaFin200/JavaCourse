public interface List<T> {
    public static final int capacity = 0;

    void ensureCapacity(int size);

    public void trimToSize(int size);
}
