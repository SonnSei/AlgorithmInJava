package 斐波那契数列;

import java.util.Arrays;

/**
 * @Classname Solution2
 * @Description
 * @Date 2020/2/4 20:22
 * @Author SonnSei
 */
public class Solution2 {
    public static void main(String[] args) {
        System.out.println(Fibonacci(0));
    }
    public static int Fibonacci(int n) {
        if(n<=0)return 0;
        if(n==1||n==2)return 1;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0]=0;dp[1]=1;dp[2]=1;
        return getFromTop(n,dp);

    }

    private static int getFromTop(int n, int[] dp) {
        if(dp[n]!=-1) return dp[n];
        return dp[n] = getFromTop(n - 1, dp) + getFromTop(n - 2, dp);
    }
}
