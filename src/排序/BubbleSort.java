package 排序;

import static utils.ArrayUtils.swap;

/**
 * @Classname BubbleSort
 * @Description TODO
 * @Date 2019/12/6 17:50
 * @Created by Jesse
 */
public class BubbleSort {
    public static void sort(int[] nums) {
        System.out.println("普通冒泡排序");
        for (int i = 0; i < nums.length ; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void sort2(int[] nums) {
        System.out.println("冒泡排序+sorted 标记位+有序区间边界");
        int border = nums.length-1;
        int lastChangeIndex = 0;
        boolean sorted = false;
        for (int i = 0; i < nums.length; i++) {
            sorted = true;
            for (int j = 0; j < border; j++) {
                if (nums[j] > nums[j + 1]) {
                    sorted = false;
                    swap(nums,j,j+1);
                    // 保持一致性，j代表的是最后一次交换的左边位置，
                    // 如果lastChangeIndex=j+1，就破坏了一致性
                    lastChangeIndex = j;
                }
            }
            border = lastChangeIndex;
            if(sorted)return;
        }
    }

    public static void sort3(int[] nums) {
        System.out.println("鸡尾酒排序");

        boolean sorted = false;
        for (int i = 0; i < nums.length / 2; i++) {
            sorted = true;
            for (int j = i; j <nums.length-1-i ; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    sorted = false;
                }
            }
            if(sorted)return;
            sorted = true;
            for (int j = nums.length-1-i; j >i ; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                    sorted = false;
                }
            }
            if(sorted)return;
        }
    }
}
