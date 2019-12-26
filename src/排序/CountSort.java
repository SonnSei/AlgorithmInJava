package 排序;

/**
 * @Classname CountSort
 * @Description TODO
 * @Date 2019/12/6 18:14
 * @Created by SunCheng
 */
public class CountSort {
    public static void sort(int[] nums) {
        System.out.println("计数排序");

        //1. 得到数列的最大值和最小值，计算差值d
        int max = nums[0];
        int min = nums[0];
        for (int item :
                nums) {
            max = max > item ? max : item;
            min = min < item ? min : item;
        }
        int d = max-min;
        //2. 创建统级数组，并统计对应元素的个数
        int[] countArray = new int[d + 1];
        for (int item :
                nums) {
            countArray[item-min]++;
        }

        //3. 统计数组变形，后面的元素等于前面的元素之和
        for (int i = 1; i < countArray.length; i++) {
            countArray[i]+=countArray[i-1];
        }
        //4. 倒序遍历数组，从统计数组找到正确位置，输出结果到数组
        // !!!!!!!倒序遍历是为了保证有序性，正序的话就无法保证了
        int[] sortedArray = new int[nums.length];
        for (int i = nums.length-1; i >=0 ; i--) {
            sortedArray[countArray[nums[i]-min]-1] = nums[i];
            countArray[nums[i]-min]--;
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = sortedArray[i];
        }
    }
}
