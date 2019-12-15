package queue;


/**
 * @Classname LoopQueue
 * @Description 循环队列
 * @Date 2019/12/15 18:19
 * @Created by SunCheng
 */
public class LoopQueue<E> {
    private E[] data;
    private int front,tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    // 循环队列中，我们特意浪费一个空间来使逻辑清晰
    public int getCapacity() {
        return data.length-1;
    }

    // 循环队列的判空条件：首尾指针相同
    public boolean isEmpty(){
        return front == tail;
    }

    public int getSize(){
        return size;
    }

    public void enqueue(E e) {
        // 注意循环队列满的情况
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() << 1);
        }

        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }

        E ret = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size--;
        if (size == getCapacity() /4 && getCapacity()/2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public E gerFront(){
        if (isEmpty()) {
            throw new IllegalArgumentException("队列为空");
        }
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity+1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data  = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
