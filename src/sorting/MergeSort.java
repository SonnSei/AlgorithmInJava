package sorting;

/**
 * @Classname MergeSort
 * @Description TODO
 * @Date 2019/12/6 15:43
 * @Created by Jesse
 */
public class MergeSort {
    public static void sort(int[] nums) {
        System.out.println("归并排序");
        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int l, int r) {
        if(l>=r)return;
        int mid = (l + r) >>> 1;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    private static void merge(int[] nums, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int p1 = l,p2 = mid+1;
        int k = 0;
        while (p1 <= mid && p2 <= r) {
            if (nums[p1] <= nums[p2]) {
                temp[k++] = nums[p1++];
            } else {
                temp[k++] = nums[p2++];
            } 
        }
        while(p1<=mid) temp[k++] = nums[p1++];
        while(p2<=r) temp[k++] = nums[p2++];
        for (int i = 0; i < temp.length; i++) {
            nums[i+l] = temp[i];
        }
    }
}
