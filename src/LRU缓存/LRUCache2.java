package LRU缓存;

import java.util.ArrayList;

/**
 * @Classname LRUCache2
 * @Description 动态数组实现
 * @Date 2019/12/14 7:52
 * @Created by SoonSei
 */
public class LRUCache2 {

    class Node{
        Integer key;
        Integer value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    ArrayList<Node> data;
    private int capacity;

    public LRUCache2(int capacity) {
        data = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {

        int nodeIndex = getNodeIndex(key);
        if (nodeIndex > -1) {
            Node node = data.get(nodeIndex);
            data.remove(nodeIndex);
            data.add(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        int nodeIndex = getNodeIndex(key);
        if (nodeIndex == -1) {
            if (data.size() >= capacity) {
                data.remove(0);
            }
            data.add(new Node(key, value));
        } else {
            data.remove(nodeIndex);
            data.add(new Node(key,value));
        }
    }

    private int getNodeIndex(int key) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).key.equals(key)) {
                return i;
            }
        }
        return -1;
    }
}
