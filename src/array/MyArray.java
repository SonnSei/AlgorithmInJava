package array;

/**
 * @Classname MyArray
 * @Description 动态数组
 * @Date 2019/12/15 17:38
 * @Created by SunCheng
 */
public class MyArray<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] data;
    private int size;


    public MyArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public MyArray() {
        this(DEFAULT_CAPACITY);
    }

    public int getCapacity(){
        return data.length;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(int index, E e) {
        CheckIndexForAdd(index);
        if (size == data.length) {
            // todo
            resize(size << 1);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;

        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public E get(int index) {
        checkIndexForGet(index);
        return data[index];
    }

    public void set(int index, E e) {
        checkIndexForGet(index);
        data[index] = e;
    }

    public boolean contains(E e) {
        for (E item :
                data) {
            if (item.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) {
        checkIndexForGet(index);
        E ret = data[index];
        for (int i = index; i <size ; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;//loitering objects != memory leak
        if (size == (data.length >> 1)) {
            resize(data.length >> 1);
        }
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    private void checkIndexForGet(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("invalid index");
        }
    }

    private void CheckIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
