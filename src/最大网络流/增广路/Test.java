package 最大网络流.增广路;

import java.util.Scanner;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2019/12/31 9:32
 * @Author SonnSei
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] graphResidual = new int[n][n];
        for (int i = 0; i < m; i++) {
            graphResidual[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        }
        EK ek = new EK(graphResidual);
        System.out.println(ek.getMaxFlow(0,n-1));
    }




    /*
6 7
0 1 5
0 2 6
1 4 8
1 3 7
2 3 10
4 5 4
3 5 5
     */
}
