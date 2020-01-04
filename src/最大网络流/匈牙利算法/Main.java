package 最大网络流.匈牙利算法;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Classname Main
 * @Description TODO
 * @Date 2020/1/1 5:00
 * @Author SonnSei
 */
public class Main {

    static boolean[] used;
    static boolean[][] map;
    static int[] matchX;
    static int[] matchY;
    static int m, n, e;
    static int ret;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        e = sc.nextByte();
        int max = 1005;
        map = new boolean[max][max];
        matchX = new int[max];
        matchY = new int[max];
        used = new boolean[max];
//        for (int i = 0; i < e; i++) {
//            int x = sc.nextInt();
//            int y = sc.nextInt();
//            if (x > n || x < 1 || y < 1 || y > m) continue;
//            map[x - 1][y - 1] = true;
//        }
        while (sc.hasNextInt()){
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (x > n || x < 1 || y < 1 || y > m) continue;
            map[x - 1][y - 1] = true;
        }

        ret = 0;
        maxMatch();
        if (ret == 0) {
            System.out.println("No Solution!");
        } else {
            for (int i = 0; i < n; i++) {
                if (matchY[i] != -1) {
                    System.out.println(i+1+" "+(matchY[i]+1));
                }
            }
        }
//        System.out.println(ret);
    }

    private static void maxMatch() {
        Arrays.fill(matchX, -1);
        Arrays.fill(matchY, -1);
        for (int i = 0; i < n; i++) {

            Arrays.fill(used, false);
            if(dfs(i)) ret++;
        }
    }

    private static boolean dfs(int x) {
        for (int y = 0; y < m; y++) {
            if (map[x][y] && !used[y]) {
                used[y] = true;
                if (matchX[y] == -1 || dfs(matchX[y])) {
                    matchX[y] = x;
                    matchY[x] = y;
                    return true;
                }
            }
        }
        return false;
    }
}
/*
8 8 7
1 1
1 2
2 2
2 3
3 1
3 2
4 2


5 10 11
1 7
1 8
2 6
2 9
2 10
3 7
3 8
4 7
4 8
5 10
-1 -1
 */