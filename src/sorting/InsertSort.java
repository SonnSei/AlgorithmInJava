package sorting;

/**
 * @Classname InsertSort
 * @Description 插入排序
 * @Date 2019/12/6 13:53
 * @Created by Jesse
 */
public class InsertSort{
    public static void sort(Comparable[] array) {
        for (int i = 1; i <array.length ; i++) {
            int j = i-1;
            Comparable key = array[i];
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
    }

    public static void sort(int[] array) {
        System.out.println("插入排序");
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i-1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
    }
}
