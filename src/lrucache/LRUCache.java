package lrucache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Classname LRUCache
 * @Description 直接用LinkedHashMap
 * @Date 2019/12/14 7:45
 * @Created by Jesse
 */
public class LRUCache {
    private LinkedHashMap<Integer,Integer> data;
    public LRUCache(int capacity) {
        // 注意这个loadFactor是一个float，所以需要加f
        data = new LinkedHashMap<Integer,Integer>(capacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size()>capacity;
            }
        };
    }

    public int get(int key) {
        return data.containsKey(key)?data.get(key):-1;
    }

    public void put(int key, int value) {
        data.put(key, value);
    }
}
