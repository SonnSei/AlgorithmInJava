package 搜索树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * @Classname BST
 * @Description
 * @Date 2020/1/19 10:27
 * @Author SonnSei
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    /**
     * 获取元素数量
     * @return BST包含的元素数量
     */
    public int getSize() {
        return size;
    }

    /**
     * 是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素
     * @param e 新元素值
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根节点的树添加元素，并返回新的根节点
     * @param node 添加元素的子树的根节点
     * @param e 新元素
     * @return
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.value) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.value) > 0)
            node.right = add(node.right, e);

        return node;
    }

    /**
     * 判断是否包含元素
     * @param e 需要判断是否存在于BST中的元素
     * @return 是否包含元素e
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 检查以node为根节点的子树中是否包含元素e
     * @param node 需要判断的子树的根节点
     * @param e 需要判断是否包含在以node为根节点的子树中的元素
     * @return 以node为根节点的子树是否包含元素e
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.value) < 0)
            return contains(node.left, e);
        if (e.compareTo(node.value) > 0)
            return contains(node.right, e);
        return true;
    }

    /**
     * 获取BST中的最小元素
     * @return BST中的最小元素，如果BST为空，则返回null
     */
    public E minimum() {
        if (size == 0) return null;
        return minimum(root).value;
    }

    /**
     * 获取以node为根节点的子树中的最小元素
     * @param node 需要搜索的子树的根节点
     * @return 最小元素所在的节点
     */
    private Node minimum(Node node) {
        if (node.left == null) return node;
        return minimum(node.left);
    }

    /**
     * 获取BST中最大元素，如果BST为空，则返回null
     * @return 获取BST中的最大元素
     */
    public E maximum() {
        if (size == 0) return null;
        return maximum(root).value;
    }

    /**
     * 获取以node为根节点的子树的最大元素所在的节点，如果
     * @param node 待搜索的子树的根节点
     * @return 以node为根节点的子树中最大元素所在的节点
     */
    private Node maximum(Node node) {
        if (node.right == null) return node;
        return minimum(node.right);
    }

    /**
     * 移除BST中的最小元素
     * @return 被移除的最小元素
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 移除以node为根节点的子树中的最小元素，并返回更新后的根节点
     * @param node 待处理子树的根节点
     * @return 更新后的子树的根节点
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 移除并返回BST中的最大元素
     * @return 被删除的最大元素
     */
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMin(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.value) < 0) {
            node.left = remove(node.left, e);
            return node;
        }
        if (e.compareTo(node.value) > 0) {
            node.right = remove(node.right, e);
            return node;
        }
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        Node successor = minimum(node.right);
        successor.right = removeMin(node.right);
        successor.left = node.left;
        node.left = node.right = null;
        return successor;
    }


    private class Node {
        E value;
        public Node left, right;

        public Node(E value) {
            this.value = value;
            this.left = null;
            this.right = right;
        }
    }


    public List<List<E>> levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        List<List<E>> ret = new ArrayList<>();
        List<E> tmp = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            tmp.clear();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                tmp.add(node.value);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ret.add(new ArrayList<>(tmp));
        }
        return ret;
    }

    public E floor(E e) {
        return floor(root, e);
    }

    private E floor(Node node, E e) {
        if (node == null) return null;
        if (node.value.equals(e)) return e;
        if (e.compareTo(node.value) < 0) return floor(node.left, e);
        E right = floor(node.right, e);
        return right == null ? node.value : right;
    }

    public E ceiling(E e){
        return ceiling(root, e);
    }

    private E ceiling(Node node, E e) {
        if(node == null)return null;
        if(node.value.equals(e))return e;
        if(e.compareTo(node.value)>0) return ceiling(node.right, e);
        E left = ceiling(node.left, e);
        return left==null?node.value:left;
    }

    /**
     * 选取排名第K位的元素
     * @param k 元素排名
     * @return 第K小的元素
     */
    public E select(int k) {
        return select(root,k).value;
    }

    private Node select(Node node, int k) {
        if(node == null)return null;
        int t = getSize(node.left);
        if(t>=k) return select(node.left, k);
        if(t==k-1)return node;
        return select(node.right, k - t - 1);
    }

    private int getSize(Node node) {
        int[] ret = new int[1];
        getSize(node, ret);
        return ret[0];
    }

    private void getSize(Node node, int[] ret) {
        if(node ==null)return;
        ret[0]++;
        getSize(node.left, ret);
        getSize(node.right, ret);
    }

    public List<E> inOrder() {
        List<E> ret = new ArrayList<>();
        inOrder(ret, root);
        return ret;
    }

    private void inOrder(List<E> ret, Node node) {
        if (node == null) return;
        inOrder(ret, node.left);
        ret.add(node.value);
        inOrder(ret, node.right);
    }

    /**
     * 返回元素在BST中的排名，从1开始，假定元素一定存在于BST中
     * @param e 待搜索元素
     * @return 元素排名
     */
    public int rank(E e) {
        return rank(root, e);
    }

    private int rank(Node node, E e) {
        if(node == null)return 0;
        if(e.compareTo(node.value)<0)return rank(node.left,e);
        if(e.compareTo(node.value)>0) return 1 + getSize(node.left) + rank(node.right, e);
        return getSize(node.left)+1;
    }

    /**
     * 范围查询，包含边界
     * @param low 左区间
     * @param high 右区间
     * @return 查询结果
     */
    public List<E> rangeSelect(E low, E high) {
        List<E> ret = new ArrayList<>();
        rangeSelect(root, ret, low, high);
        return ret;
    }

    private void rangeSelect(Node node, List<E> ret, E low, E high) {
        if(node == null)return ;
        int comlo = low.compareTo(node.value);
        int comhi = high.compareTo(node.value);
        if(comlo<0) rangeSelect(node.left, ret, low, high);
        if(comlo<=0 && comhi>=0) ret.add(node.value);
        if(comhi>0) rangeSelect(node.right, ret, low, high);
    }
}
