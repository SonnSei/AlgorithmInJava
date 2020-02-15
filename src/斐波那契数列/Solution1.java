package 斐波那契数列;

/**
 * @Classname Solution1
 * @Description
 * @Date 2020/2/4 20:18
 * @Author SonnSei
 */
public class Solution1 {
    public int Fibonacci(int n) {
        if(n<=0)return 0;
        if(n ==1||n==2)return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}
