package 栈;

import java.util.ArrayList;

/**
 * @Classname ArrayListStack
 * @Description TODO
 * @Date 2019/12/14 12:39
 * @Created by SunCheng
 */
public class ArrayListStack<E>{
    ArrayList<E> data;

    public ArrayListStack() {
        data = new ArrayList<>();
    }

    public int size() {
        return data.size();
    }

    public void push(E e) {
        data.add(e);
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("the stack is empty");
        }
        return data.remove(data.size()-1);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public E peek(){
        return data.get(data.size() - 1);
    }
}
