package queue;

import java.util.ArrayList;

/**
 * @Classname ArrayQueue
 * @Description 基于数组实现的顺序队列
 * @Date 2019/12/15 18:15
 * @Created by SunCheng
 */
public class ArrayQueue<E> {
    // ArrayList是自动扩容的，但是这里我们只是需要它已经实现好的元素操作后的位移
    // 所以这里我们模拟的是一个有固定容量的队列
    private ArrayList<E> data;
    private int capacity;

    public ArrayQueue(int capacity) {
        data = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize(){
        return data.size();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public void enqueue(E e) {
        if (data.size() >= capacity) {

        }
        data.add(e);
    }

    public E dequeue() {
        if (data.size() <= 0) {
            throw new IllegalArgumentException("队列为空");
        }
        return data.remove(0);
    }

    public E getFront(){
        if (data.size() <= 0) {
            throw new IllegalArgumentException("队列为空");
        }
        return data.get(0);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0 ; i < data.size() ; i ++){
            res.append(data.get(i));
            if(i != data.size() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
}
