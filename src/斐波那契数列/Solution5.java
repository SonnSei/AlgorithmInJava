package 斐波那契数列;

/**
 * @Classname Solution4
 * @Description
 * @Date 2020/2/4 20:32
 * @Author SonnSei
 */
public class Solution5 {
    public int Fibonacci(int n) {
        return fib(n, 0, 1);
    }

    private int fib(int n, int a, int b) {
        return n<=0?a: fib(n - 1, b, a + b);
    }
}
