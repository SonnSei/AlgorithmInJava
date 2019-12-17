package heap;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Classname Heap
 * @Description 大顶堆
 * @Date 2019/12/17 10:45
 * @Created by SunCheng
 */
public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> data;

    public MaxHeap(int capacity){
        data = new ArrayList<>(capacity);
    }

    public MaxHeap(){
        data = new ArrayList<>();
    }

    public MaxHeap(E[] arr){
        data = new ArrayList<>();
        for (E e :
                arr) {
            data.add(e);
        }
        for(int i = parent(arr.length - 1) ; i >= 0 ; i --)
            siftDown(i);
    }

    public int size(){
        return data.size();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回父节点的索引
    // 如果是从0开始，则返回（index-1）/2
    // 如果是从1开始，则返回index/2
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回左子节点的索引
    // 如果是从0开始， 返回index*2+1
    // 如果是从1开始，返回index*2
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回右子节点的索引
    // 如果是从0开始， 返回index*2+2
    // 如果是从1开始，返回index*2+1
    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        data.add(e);
        siftUp(data.size() - 1);
    }

    // 上浮操作
    private void siftUp(int k){
        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0 ){
            Collections.swap(data,k,parent(k));
            k = parent(k);
        }
    }

    // 看堆中的最大元素
    public E findMax(){
        if(data.size() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax(){
        E ret = findMax();
        Collections.swap(data, 0, data.size() - 1);
        data.remove(data.size() - 1);
        siftDown(0);
        return ret;
    }

    private void siftDown(int k){
        while(leftChild(k) < data.size()){
            int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
            if( j + 1 < data.size() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0 )
                j ++;
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if(data.get(k).compareTo(data.get(j)) >= 0 )
                break;
            Collections.swap(data,k,j);
            k = j;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e){
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}
