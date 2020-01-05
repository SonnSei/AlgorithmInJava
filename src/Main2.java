/**
 * @Classname Main2
 * @Description TODO
 * @Date 2020/1/5 11:11
 * @Author SonnSei
 */
public class Main2 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i-1]^arr[i];
        }
        int[] ret = new int[queries.length];
        if(ret.length==0)return ret;
        for (int i = 0; i < ret.length; i++) {
            int[] pair = queries[i];
            int add = dp[pair[1]]^dp[pair[0]]^arr[pair[0]];
            ret[i] = add;
        }
        return ret;
    }
}
