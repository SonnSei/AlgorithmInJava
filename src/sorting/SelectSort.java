package sorting;

import static utils.ArrayUtils.swap;

/**
 * @Classname SelectSort
 * @Description TODO
 * @Date 2019/12/6 19:14
 * @Created by Jesse
 */
public class SelectSort {
    public static void sort(int[] array) {
        System.out.println("选择排序");
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min  = j;
                }
            }
            swap(array,i,min);
        }
    }
}
