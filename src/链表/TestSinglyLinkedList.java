package 链表;

/**
 * @Classname TestSinglyLinkedList
 * @Description TODO
 * @Date 2019/12/14 10:33
 * @Created by SunCheng
 */
public class TestSinglyLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        for (int i = 0; i < 10; i++) {
            linkedList.addLast(i);
            System.out.println(linkedList+" size:"+linkedList.size());
        }
        for (int i = 0; i < 10; i++) {
            linkedList.update(i,999);
            System.out.println(linkedList+" size:"+linkedList.size());
        }

    }
}
