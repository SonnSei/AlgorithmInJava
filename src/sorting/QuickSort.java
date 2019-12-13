package sorting;

import java.util.Stack;

import static utils.ArrayUtils.swap;

/**
 * @Classname QuickSort
 * @Description TODO
 * @Date 2019/12/6 16:29
 * @Created by Jesse
 */
public class QuickSort {


    /**
     * 常见版本
     */
    public static void sort(int[] array) {
        System.out.println("普通版本快排");
        sort(array, 0, array.length - 1);
    }
    public static void sort(int[] array, int left, int right) {
        if (left < right) {
            int p1 = left;
            int p2 = right;
            int pivot = array[left];
            while (p1 < p2) {
                while (array[p2] >= pivot && p1 < p2)
                    p2--;
                while (array[p1] <= pivot && p1 < p2)
                    p1++;
                swap(array, p1, p2);
            }
            swap(array, left, p1);
            sort(array, left, p1 - 1);
            sort(array, p1 + 1, right);
        }
    }

    /**
     * 单边循环
     */
    public static void sort2(int[] array) {
        System.out.println("单边循环版本快排");
        sort2(array, 0, array.length - 1);
    }
    public static void sort2(int[] array, int left, int right) {
        if (left < right) {
            int pivot = array[left];
            int mid = help(array,left,right,pivot);
            sort(array, left, mid - 1);
            sort(array, mid + 1, right);
        }
    }
    public static int help(int[] nums,int left,int right,int pivot){
        int p1 = left;
        int mid = left;
        for (int i = left; i <=right ; i++) {
            if(nums[i]<=pivot){
                swap(nums,i,p1++);
            }
        }
        for (int i = right; i >=left ; i--) {
            if (nums[i] < pivot) {
                mid = i;
                break;
            }
        }
        swap(nums,left,mid);
        return mid;
    }


    /**
     * 非递归
     */
    public static void sort3(int[] array) {
        System.out.println("非递归版本快排");
        sort3(array, 0, array.length - 1);
    }
    public static void sort3(int[] array, int left, int right) {
        if(left>=right)return;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{left,right});
        while (!stack.isEmpty()) {
            int[] cur =stack.pop();
            int l = cur[0];
            int r = cur[1];
            //这里是单边循环里的help函数
            int mid = help(array,l,r,array[l]);
            if(l<mid-1)stack.push(new int[]{l,mid-1});
            if(mid<r-1)stack.push(new int[]{mid+1,r});
        }
    }

}
