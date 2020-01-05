package 排序;

import java.util.Arrays;
import java.util.Random;

import static utils.ArrayUtils.swap;

/**
 * @Classname QuickTest
 * @Description TODO
 * @Date 2020/1/5 14:21
 * @Author SonnSei
 */
public class QuickTest {

    /**
     * 常见版本
     */
    public static void sort(int[] array) {
        System.out.println("普通版本快排");
        sort(array, 0, array.length - 1);
    }
    private static void sort(int[] nums,int l,int r){
        if(l>=r)return;
        int mid = partition(nums,l,r);
        if(mid == -1) return;
        sort(nums,l,mid-1);
        sort(nums,mid+1,r);

    }

    /**
     * 单边
     */
    private static int partition(int[] nums,int l,int r){
        int swapCount = 0;
        int pivot = nums[l];
        int left = l;
        for(int i = left;i<=r;i++){
            if(nums[i]<=pivot){
                swapCount++;
                swap(nums, left++, i);
            }

        }
        if(swapCount==1)
            return -1;
        int mid = l;
        for(int i = r;i>=l;i--){
            if(nums[i]<pivot){
                mid = i;
                break;
            }
        }
        swap(nums, mid, l);
        return mid;
    }
}
