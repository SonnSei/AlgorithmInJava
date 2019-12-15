package linkedlist;

/**
 * @Classname SinglyLinkedList
 * @Description 单链表
 * @Date 2019/12/14 10:11
 * @Created by SunCheng
 */
public class SinglyLinkedList<E> {
    private class Node<E>{
        E element;
        Node next;

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node(E element) {
            this(element, null);
        }
    }

    private Node head,tail;
    private Node dummyHead;
    private int size;

    public SinglyLinkedList() {
        size = 0;
        dummyHead = new Node(0,head);
    }


    //***************************************
    //********       增加元素       **********
    //***************************************

    public void add(int index, E e) {
        checkIndexIncludeSize(index);
        Node cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node next = cur.next;
        cur.next = new Node(e, next);

        if (head == null) {
            head = dummyHead.next;
            tail = dummyHead.next;
        }

        if (index == 0) {
            head = dummyHead.next;
        }
        if (index == size) {
            tail = cur.next;
        }
        size ++;
    }

    public void add(E e) {
        addLast(e);
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e) {
        add(size, e);
    }


    //***************************************
    //********       删除元素       **********
    //***************************************


    public E remove(int index) {
        checkIndexExcludeSize(index);
        Node cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node removeNode = cur.next;
        cur.next = removeNode.next;
        removeNode.next = null;

        if (index == 0) {
            head = dummyHead.next;
        }
        if (index == size - 1) {
            tail = cur;
        }

        size--;
        return (E) removeNode.element;
    }

    public E removeFirst() {
        checkIndexExcludeSize(0);
        return remove(0);
    }

    public E removeLast(){
        checkIndexExcludeSize(size - 1);
        return remove(size-1);
    }

    public void removeElement(E e) {
        int index = getIndex(e);
        if (index > -1) {
            remove(index);
        }
    }





    //***************************************
    //********       修改元素       **********
    //***************************************

    public void update(int index, E e) {
        Node node = getNode(index);
        node.element = e;
    }



    //***************************************
    //********       查找元素       **********
    //***************************************


    public E get(int index) {
        checkIndexExcludeSize(index);
        return (E) getNode(index).element;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }


    //***************************************
    //********       边界检查       **********
    //***************************************

    private void checkIndexExcludeSize(int index) {
        if(index<0 || index>=size)
            throw new IllegalStateException("invalid index");
    }

    private void checkIndexIncludeSize(int index) {
        if(index<0 || index>size)
            throw new IllegalStateException("invalid index");
    }

    //***************************************
    //********       辅助函数       **********
    //***************************************

    @Override
    public String toString() {
        Node cur = dummyHead.next;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.element.toString()+" ");
            cur = cur.next;
        }
        return sb.toString().trim();
    }

    public int size(){
        return size;
    }

    private int getIndex(E e) {
        Node cur = head;
        for (int i = 0; i < size; i++) {
            if (cur.element.equals(e)) {
                return i;
            }
            cur = cur.next;
        }
        return -1;
    }

    private Node getNode(int index) {
        checkIndexExcludeSize(index);
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }
}
