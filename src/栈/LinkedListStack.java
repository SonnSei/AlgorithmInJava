package 栈;

import java.util.LinkedList;

/**
 * @Classname LinkedListStack
 * @Description TODO
 * @Date 2019/12/14 12:42
 * @Created by SunCheng
 */
public class LinkedListStack<E> {
    LinkedList<E> data;

    public LinkedListStack() {
        data = new LinkedList<>();
    }

    public int size(){
        return data.size();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public void push(E e) {
        data.addLast(e);
    }

    public E pop() {
        return data.removeLast();
    }

    public E peek(){
        return data.get(data.size() - 1);
    }
}
