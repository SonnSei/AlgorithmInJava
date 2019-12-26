package LRU缓存;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Classname LRUCache3
 * @Description 哈希表+链表 ！= 哈希链表，复杂度并没有降低，很慢
 * @Date 2019/12/14 8:27
 * @Created by Jesse
 */
public class LRUCache3 {
    private HashMap<Integer, Integer> map;
    private LinkedList<Integer> linkedList;
    private int capacity;

    public LRUCache3(int capacity) {
        map = new HashMap<>(capacity);
        linkedList = new LinkedList<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            linkedList.remove(new Integer(key));
            linkedList.add(key);
            return map.get(key);
        } else {
            return -1;
        }

    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            linkedList.remove(new Integer(key));
            linkedList.add(key);
            map.put(key, value);
        } else {
            if (map.size() >= capacity) {
                Integer remove = linkedList.remove(0);
                map.remove(remove);
            }
            map.put(key, value);
            linkedList.add(key);
        }
    }
}
