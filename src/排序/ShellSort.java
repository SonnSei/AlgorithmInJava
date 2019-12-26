package 排序;

/**
 * @Classname ShellSort
 * @Description TODO
 * @Date 2019/12/16 14:00
 * @Created by SunCheng
 */
public class ShellSort {
    public static void sort(int[] nums) {
        for (int gap = nums.length/2; gap >0 ; gap/=2) {
            for (int i = gap; i <nums.length ; i++) {
                int key = nums[i];
                int k = i-gap;
                while (k >= gap && nums[k] > key) {
                    nums[k + gap] = nums[k];
                    k-=gap;
                }
                nums[k] = key;
            }
        }
    }

    public static void sort2(int[] nums) {
        int[] seq = {5, 3, 1};
        for (int i = 0; i < seq.length; i++) {
            int gap = seq[i];
            for (int j = gap; j <nums.length ; j++) {
                int key = nums[j];
                int k = j-gap;
                while (k >= gap && nums[k] > key) {
                    nums[k+gap] = nums[k];
                    k-=gap;
                }
                nums[k] = key;
            }
        }
    }
}
