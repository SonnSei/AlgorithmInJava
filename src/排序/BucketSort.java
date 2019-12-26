package 排序;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @Classname BucketSort
 * @Description TODO
 * @Date 2019/12/6 18:37
 * @Created by Jesse
 */
public class BucketSort {
    public static void sort(double[] nums) {
        System.out.println("桶排序");
        //1. 得到最大值最小值，并计算出差值
        double max = nums[0];
        double min = nums[1];
        for (double item :
                nums) {
            max = Math.max(max, item);
            min = Math.min(min, item);
        }
        double d= max-min;

        //2. 初始化桶
        int bucketNum = nums.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }

        //3. 遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < nums.length; i++) {
            // !!!!!为什么要除以 桶的数量-1 ？ 因为下标是从0开开始的
            int num = (int) ((nums[i]-min) * (bucketNum-1)/d);
            bucketList.get(num).add(nums[i]);
        }

        //4. 对每个桶内进行排序
        // 在元素分布相对均匀的情况下，所有桶的运算量之和是n
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));
        }

        //5. 输出全部元素
        double[] sortedArray = new double[nums.length];
        int index = 0;
        for (LinkedList<Double> list :
                bucketList) {
            for (double item :
                    list) {
                sortedArray[index++] = item;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sortedArray[i];
        }
    }
}
