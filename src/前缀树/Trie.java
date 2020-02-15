package 前缀树;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Classname Trie
 * @Description TODO
 * @Date 2020/1/8 7:51
 * @Author SonnSei
 */
public class Trie {
    private class Node{
        public boolean isWord;
        public Map<Character,Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c)==null)return  false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c= prefix.charAt(i);
            if(cur.next.get(c)==null)return false;
            cur = cur.next.get(c);
        }
        return true;
    }

    public boolean remove(String word) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        for (int i = 0; i < word.length(); i++) {
            if(!stack.peek().next.containsKey(word.charAt(i)))return false;
            stack.push(stack.peek().next.get(word.charAt(i)));
        }
        if(!stack.peek().isWord)return false;

        stack.peek().isWord=false;
        size--;
        if(stack.peek().next.size()>0)return true;
        else stack.pop();
        for (int i = word.length() - 1; i >= 0; i--) {
            stack.peek().next.remove(word.charAt(i));
            if(stack.peek().isWord || stack.peek().next.size()>0)return true;
            stack.pop();
        }
        return true;
    }
}
