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

        int[] nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(9000)+1000;
        }
        System.out.println(Arrays.toString(nums));
        ShellSort.sort(nums);
        System.out.println(Arrays.toString(nums));
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
    }
}
