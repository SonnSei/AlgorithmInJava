package lrucache;

import java.util.HashMap;

/**
 * @Classname LRUCache4
 * @Description 正经哈希链表
 * @Date 2019/12/14 8:40
 * @Created by Jesse
 */
public class LRUCache4 {
    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    private HashMap<Integer,Node> map;
    private int capacity;

    public LRUCache4(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        refreshNode(node);
        return node.value;
    }


    private void refreshNode(Node node) {
        if (node == tail) {
            return;
        }
        removeNode(node);
        addNode(node);
    }

    private void addNode(Node node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        }
    }

    private void removeNode(Node node) {
        if (node == head && node == tail) {
            head = null;
            tail = null;
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else if (node == head) {
            head = head.next;
            head.prev = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            if (map.size() >= capacity) {
                int removedKey = head.key;
                removeNode(head);
                map.remove(removedKey);
            }
            node = new Node(key, value);
            addNode(node);
            map.put(key, node);
        } else {
            node.value = value;
            refreshNode(node);
        }
    }
}
