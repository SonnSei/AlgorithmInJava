package 斐波那契数列;

/**
 * @Classname Solution4
 * @Description
 * @Date 2020/2/4 20:40
 * @Author SonnSei
 */
public class Solution4 {
    public int Fibonacci(int n) {
        if(n<=0)return 0;
        if(n==1||n==2)return 1;
        int[] dp = new int[n + 1];
        dp[1]=1;
        dp[2]=1;
        for (int i = 3; i <=n; i++) {
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
