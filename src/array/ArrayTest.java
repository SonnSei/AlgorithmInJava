package array;

/**
 * @Classname ArrayTest
 * @Description TODO
 * @Date 2019/12/15 18:04
 * @Created by SunCheng
 */
public class ArrayTest {
    public static void main(String[] args) {
        MyArray<Integer> array = new MyArray<>();
        for (int i = 0; i <= 100; i++) {
            array.addLast(i);
            if(i%10==0)
                System.out.println(array);
        }
    }
}
