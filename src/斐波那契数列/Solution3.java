package 斐波那契数列;

/**
 * @Classname Solution3
 * @Description
 * @Date 2020/2/4 20:27
 * @Author SonnSei
 */
public class Solution3 {
    public int Fibonacci(int n) {
        if(n<=0)return 0;
        if(n==1||n==2)return 1;
        int a = 1,b = 1;
        int c=a+b;
        for (int i = 3; i <=n ; i++) {
            c = a+b;
            a = b;
            b =c;
        }
        return c;
    }
}
