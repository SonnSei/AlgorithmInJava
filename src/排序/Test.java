package 排序;

import java.util.Arrays;
import java.util.Random;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2019/12/6 14:06
 * @Created by Jesse
 */
public class Test {
    public static void main(String[] args) {

        Random random = new Random();

         final int SIZE = 10000;
        int[] nums = new int[SIZE];
        int[] nums2 = new int[SIZE];
        for (int i = 0; i < nums.length; i++) {
//            int cur = random.nextInt(100);;
//            int cur = random.nextInt();
            int cur = i;
            nums[i] = cur;
            nums2[i] = cur;
        }
        nums = new int[]{5,1,1,2,0,0};
        long start= System.currentTimeMillis();
//        System.out.println(Arrays.toString(nums));
//        QuickSort3Path.sort(nums);
        QuickTest.sort(nums);
//        System.out.println(Arrays.toString(nums));
        long end= System.currentTimeMillis();
        System.out.println("随机pivot快排用时："+(end-start)+" ms");

        QuickSort.sort(nums2);
        long end2 = System.currentTimeMillis();
        System.out.println("普通路快排用时："+(end2-end)+" ms");
        boolean success = true;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > nums[i + 1]) {
                System.out.println("fail");
                success = false;
                break;
            }
        }
        if (success) {
            System.out.println("success");
        }

        for (int i = 0; i < nums2.length-1; i++) {
            if (nums2[i] > nums2[i + 1]) {
                System.out.println("3路fail");
                break;
            }
        }
    }
}
