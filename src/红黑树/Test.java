package 红黑树;

import utils.FileOperation;
import 搜索树.AVLTree;
import 红黑树.java中的红黑树.RBTree;

import java.util.ArrayList;

/**
 * @Classname Test
 * @Description
 * @Date 2020/1/19 9:42
 * @Author SonnSei
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("src/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

//            RBTree<String, Integer> map = new RBTree<>();
            long start = System.currentTimeMillis();
            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            long end = System.currentTimeMillis();


            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
            System.out.println("put use times: "+(end-start)+" ms");
            start=System.currentTimeMillis();
            for (String word : words) {
                map.get(word);
            }
            end = System.currentTimeMillis();

            System.out.println("select use times:"+(end-start)+" ms");
        }

        System.out.println();
    }
}
