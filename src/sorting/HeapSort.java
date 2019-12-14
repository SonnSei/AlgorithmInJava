package sorting;


import static utils.ArrayUtils.swap;

/**
 * @Classname HeapSort
 * @Description TODO
 * @Date 2019/12/6 19:07
 * @Created by Jesse
 */
public class HeapSort {
    public static void sort(int[] array) {
        System.out.println("堆排序");
        heapify(array, (array.length - 2) / 2, array.length);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, i, 0);
            siftDown(array, 0, i);
        }
    }

    public static void siftDown(int[] array, int i, int len) {
        while (leftChild(i) < len) {
            int child = leftChild(i);
            if (child + 1 < len && array[child + 1] > array[child])
                child++;

            if (array[i] >= array[child])
                break;

            swap(array, i, child);
            i = child;
        }
    }

    public static int leftChild(int k) {
        return 2 * k + 1;
    }

    public static void heapify(int[] array, int begin, int len) {
        for (int i = begin; i >= 0; i--)
            siftDown(array, i, len);
    }

}
