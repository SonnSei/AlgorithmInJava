package topk;

import sorting.QuickSort;

import static sorting.QuickSort.partition;
import static utils.ArrayUtils.swap;

/**
 * @Classname KthSmallestWithQuickSort
 * @Description 第K小的元素
 * @Date 2019/12/16 10:55
 * @Created by SunCheng
 */
public class KthSmallestWithQuickSort {
    public static int getKthSmallest(int[] nums, int k) {
        // 省略参数合法性检查

        int partition = partition(nums, 0, nums.length - 1);
        while (partition + 1 != k) {
            if (partition + 1 < k) {
                partition = partition(nums, partition + 1, nums.length - 1);
            } else {
                partition = partition(nums, 0, partition - 1);
            }
        }
        return nums[partition];
    }


}
