package sorting;

import java.util.Arrays;

/**
 * @Classname RadixSort
 * @Description TODO
 * @Date 2019/12/16 9:25
 * @Created by SunCheng
 */
public class RadixSort {
    public static void sort(int[] nums) {
        System.out.println("基数排序");
        // 假设输入的数字长度都相同
        // 如果不等长，可以加一步补0对齐的操作
        int length = String.valueOf(nums[0]).length();
        for (int i = length-1; i >=0; i--) {
            // 根据第i位进行排序
            countSort(nums, i);
        }

        System.out.println(Arrays.toString(nums));
    }

    private static void countSort(int[] nums, int index) {
        int min = 9,max = 0;
        for (int num :
                nums) {
            int val = getNumByIndex(num, index);
            min = Math.min(val,min);
            max = Math.max(max,val);
        }

        int[] countArray = new int[max - min + 1];
        for (int num:
                nums) {
            int val = getNumByIndex(num, index);
            countArray[val-min]++;
        }

        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        int[] temp = new int[nums.length];
        for (int i = nums.length-1; i >=0 ; i--) {
            int val = getNumByIndex(nums[i], index)-min;
            int position = countArray[val];
            temp[position-1] = nums[i];
            countArray[val]--;
        }

        System.arraycopy(temp,0,nums,0,nums.length);
    }

    private static int getNumByIndex(int num, int index) {
        int len = String.valueOf(num).length();
        num = num/(int)Math.pow(10, len-index-1);
        return num%10;
    }
}
