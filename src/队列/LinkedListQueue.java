package 队列;

import java.util.LinkedList;

/**
 * @Classname LinkedListQueue
 * @Description 链式队列
 * @Date 2019/12/15 18:16
 * @Created by SunCheng
 */
public class LinkedListQueue<E> {
    private LinkedList<E> data;

    public LinkedListQueue() {
        data = new LinkedList<>();
    }

    public void enqueue(E e) {
        data.addLast(e);
    }

    public E dequeue() {
        return data.removeFirst();
    }
}
