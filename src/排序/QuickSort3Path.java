package 排序;

import java.util.Arrays;

/**
 * @Classname QuickSort3Path
 * @Description TODO
 * @Date 2020/1/5 6:52
 * @Author SonnSei
 */
public class QuickSort3Path {

    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if(left>=right)return;
        int pivot = nums[left];
        int[] border = getBorder(nums, left, right, pivot);
        quickSort(nums,left,border[0]-1);
        quickSort(nums,border[1]+1,right);
    }

    private static int[] getBorder(int[] nums, int left, int right, int pivot) {
        int l = left,r = right;
        while(l<=right && nums[l]<pivot)l++;
        while(r>=left && nums[r]>pivot)r--;
        int cur = l;
        // 要包含r，因为l走过的一定是排号的，但是r不一定
        // 比如排序[4,1,2,5,2]
        // l一直走到5这个位置的时候才和2交换，但是交换之后，l==r了，但是此时数组为[1,2,4,2,5]所以还需要对2进行一次处理
        while (cur <= r) {
            if(nums[cur]<pivot)
                swap(nums,cur++,l++);
            else if(nums[cur]>pivot)
                swap(nums,cur,r--);
            else
                cur++;
        }
        return new int[]{l, r};
    }
    public static void swap(int[] nums,int a,int b){
        if(a == b)return;
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
