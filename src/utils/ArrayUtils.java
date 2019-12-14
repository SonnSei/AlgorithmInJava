package utils;

/**
 * @Classname ArrayUtils
 * @Description TODO
 * @Date 2019/12/14 8:16
 * @Created by Jesse
 */
public class ArrayUtils {
    public static void swap(int[] nums,int a,int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
