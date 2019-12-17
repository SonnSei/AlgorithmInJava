package skiplist;

import java.util.Arrays;

/**
 * @Classname TestSkipList
 * @Description TODO
 * @Date 2019/12/16 16:19
 * @Created by SunCheng
 */
public class TestSkipList {
    public static void main(String[] args) {
        for (int ii = 0; ii < 20; ii++) {
            String[] methods = {"add", "add", "add", "add", "add", "add", "add", "add", "add", "erase", "search", "add",
                    "erase", "erase", "erase", "add", "search", "search", "search", "erase", "search", "add", "add", "add",
                    "erase", "search", "add", "search", "erase", "search", "search", "erase", "erase", "add", "erase",
                    "search", "erase", "erase", "search", "add", "add", "erase", "erase", "erase", "add", "erase", "add",
                    "erase", "erase", "add", "add", "add", "search", "search", "add", "erase", "search", "add", "add",
                    "search", "add", "search", "erase", "erase", "search", "search", "erase", "search", "add", "erase",
                    "search", "erase", "search", "erase", "erase", "search", "search", "add", "add", "add", "add", "search",
                    "search", "search", "search", "search", "search", "search", "search", "search"};
            int[] nums = {16, 5, 14, 13, 0, 3, 12, 9, 12, 3, 6, 7, 0, 1, 10, 5, 12, 7, 16, 7, 0, 9, 16, 3, 2, 17, 2, 17, 0, 9, 14, 1, 6, 1, 16, 9, 10, 9, 2, 3, 16, 15, 12, 7, 4, 3, 2, 1, 14, 13, 12, 3, 6, 17, 2, 3, 14, 11, 0, 13, 2, 1, 10, 17, 0, 5, 8, 9, 8, 11, 10, 11, 10, 9, 8, 15, 14, 1, 6, 17, 16, 13, 4, 5, 4, 17, 16, 7, 14, 1};
            Object[] booleans = {null,null,null,null,null,null,null,null,null,true,false,null,true,false,false,null,true,true,true,true,false,null,null,null,false,false,null,false,false,true,true,false,false,null,true,true,false,true,true,null,null,false,true,false,null,true,null,true,true,null,null,null,false,false,null,true,false,null,null,true,null,false,false,false,true,true,false,true,null,true,false,false,false,true,true,false,false,null,null,null,null,true,true,true,true,true,true,false,false,true};
            Object[] rets = new Object[booleans.length];
            SkipList skipList = new SkipList();

            for (int i = 0; i < methods.length; i++) {
                switch (methods[i]) {
                    case "add":
                        skipList.insert(nums[i]);
                        rets[i] = null;
                        break;
                    case "erase":
                        rets[i] = skipList.delete(nums[i]);
                        break;
                    case "search":
                        rets[i] = skipList.search(nums[i]);
                        break;
                }
            }
            for (int i = 0; i < rets.length; i++) {
                if (!(rets[i] + "").equals(booleans[i] + "")) {
                    System.out.println("fail");
                    System.out.println(i);
                    System.out.println(methods[i]);
                    System.out.println(nums[i]);
                    System.out.println("should be :"+booleans[i]);
                    System.out.println("real be :"+rets[i]);
                    System.out.println("*****************************");
                    break;
                }
            }
        }
    }
}
