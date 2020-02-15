package 红黑树.java中的红黑树;

import java.util.TreeMap;

/**
 * @Classname RBTree
 * @Description
 * @Date 2020/2/15 13:06
 * @Author SonnSei
 */
public class RBTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean contains(K key) {
        return getNode(key) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node==null?null:node.value;
    }

    public void set(K key, V value) {
        Node<K, V> node = getNode(key);
        if(node!=null)node.setValue(value);
    }

    public void add(K key, V value) {
        Node t = root;
        if (t == null) {
            root = new Node<K, V>(key, value, null);
            size = 1;
            return;
        }
        int cmp;
        Node<K, V> parent;
        do {
            parent = t;
            cmp = key.compareTo(parent.getKey());
            if (cmp < 0) {
                t = t.left;
            } else if (cmp > 0) {
                t = t.right;
            } else {
                t.setValue(value);
                return;
            }
        } while (t != null);

        Node<K, V> e = new Node<>(key, value, parent);
        if (cmp < 0)
            parent.left = e;
        else
            parent.right = e;
        fixAfterInsertion(e);
        size++;
    }

    private void fixAfterInsertion(Node<K, V> node) {
        node.color = RED;
        while (node != null && node != root && node.parent.color == RED) {
            Node<K,V> y;
            if (parentOf(node) == leftOf(parentOf(parentOf(node)))) {
                y = rightOf(parentOf(parentOf(node)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = parentOf(parentOf(node));
                } else {
                    if (node == rightOf(parentOf(node))) {
                        node = parentOf(node);
                        rotateLeft(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rotateRight(parentOf(parentOf(node)));
                }
            } else {
                y = leftOf(parentOf(parentOf(node)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = parentOf(parentOf(node));
                } else {
                    if (node == leftOf(parentOf(node))) {
                        node = parentOf(node);
                        rotateRight(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rotateLeft(parentOf(parentOf(node)));
                }
            }
        }
        root.color=BLACK;
    }

    public void remove(K key) {
        Node<K, V> node = getNode(key);
        if(node!=null)deleteNode(node);
    }

    private void deleteNode(Node<K, V> del) {
        size--;
        if(del.left!=null && del.right!=null){
            Node<K,V> right = del.right;
            while(right.left!=null)right=right.left;
            del.key = right.getKey();
            del.setValue(right.getValue());
            del = right;
        }

        Node<K,V> replacement = del.left!=null?del.left:del.right;
        if (replacement != null) {
            replacement.parent = del.parent;
            if (del.parent == null) {
                root = replacement;
            } else if (del == del.parent.left) {
                del.parent.left = replacement;
            } else {
                del.parent.right = replacement;
            }
            del.left=del.right=del.parent=null;
            if (del.color == BLACK) {
                rebalanceAfterDelete(replacement);
            }
        } else if (del.parent == null) {
            root = null;
        } else {
            // 待删除节点无子树
            if (del.color == BLACK) {
                rebalanceAfterDelete(del);
            }
            if (del.parent != null) {
                if (del.parent.left == del) {
                    del.parent.left = null;
                } else {
                    del.parent.right=null;
                }
                del.parent=null;
            }
        }
    }

    private void rebalanceAfterDelete(Node<K, V> x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                Node<K, V> sib = rightOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                if (colorOf(leftOf(sib)) == BLACK &&
                        colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }

                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else {
                Node<K, V> sib = leftOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib),BLACK);
                        setColor(sib,RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }

                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x),BLACK);
                    setColor(leftOf(sib),BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }
        setColor(x, BLACK);

    }

    private boolean colorOf(Node<K, V> p) {
        return p == null ? BLACK : p.color;
    }

    private Node<K, V> parentOf(Node<K, V> p) {
        return p == null ? null : p.parent;
    }

    private Node<K, V> leftOf(Node<K, V> p) {
        return p == null ? null : p.left;
    }

    private Node<K, V> rightOf(Node<K, V> p) {
        return p == null ? null : p.right;
    }

    private void rotateLeft(Node<K, V> node) {
        if (node != null) {
            Node right = node.right;
            node.right = right.left;
            if (right.left != null) {
                right.left.parent = node;
            }
            right.parent = node.parent;
            if (node.parent == null) {
                root = right;
            } else if (node.parent.left == node) {
                node.parent.left = right;
            } else {
                node.parent.right = right;
            }
            node.parent = right;
            right.left = node;
        }
    }


    private void rotateRight(Node<K, V> node) {
        if (node != null) {
            Node left = node.left;
            node.left = left.right;
            if (left.right != null) {
                left.right.parent = node;
            }
            left.parent = node.parent;
            if (node.parent == null) {
                root = left;
            } else if (node == node.parent.left) {
                node.parent.left = left;
            } else {
                node.parent.right = left;
            }
            left.right = node;
            node.parent = left;
        }
    }

    private void setColor(Node<K, V> p, boolean color) {
        if (p != null) p.color = color;
    }

    private Node<K, V> getNode(K key) {
        if (key == null)
            throw new NullPointerException();
        Node<K, V> p = root;
        while (p != null) {
            int cmp = key.compareTo(p.getKey());
            if (cmp < 0) p = p.left;
            else if (cmp > 0) p = p.right;
            else
                return p;
        }
        return null;
    }


    private static final boolean RED = false;
    private static final boolean BLACK = true;


    class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        boolean color = BLACK;


        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
