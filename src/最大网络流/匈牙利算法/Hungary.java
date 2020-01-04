package 最大网络流.匈牙利算法;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Classname Hungary
 * @Description TODO
 * @Date 2020/1/1 13:45
 * @Author SonnSei
 */
public class Hungary {

    boolean[] used;
    boolean[][] map;
    int[] matchX;
    int[] matchY;
    int[] V1;
    int[] V2;

    public int hungary() {
        int ret = 0;
        for (int i = 0; i < V1.length; i++) {
            Arrays.fill(used, false);
            if (find(V1[i]))ret++;
        }
        for (int i = 0; i < matchX.length; i++) {
            if(matchX[i]==-1)continue;
            System.out.println(i+"--"+matchX[i]);
        }
        return ret;
    }

    public Hungary(boolean[][] map,int[] v1,int[] v2) {
        int n = v1.length+V2.length;
        used = new boolean[n];
        this.map = map;
        matchX = new int[n];
        matchY = new int[n];
        Arrays.fill(matchX, -1);
        Arrays.fill(matchY, -1);
        V1 = v1;
        V2 = v2;
    }

    private boolean find(int x) {
        for (int i = 0; i< V2.length; i++) {
            int y = V2[i];
            if (map[x][y] && !used[y]) {
                used[y]=true;
                if (matchY[y] == -1 || find(matchY[y])) {
                    matchX[x]=y;
                    matchY[y]=x;
                    return true;
                }
            }
        }
        return false;
    }
}

/*
5 7
1 8
2 7
2 8
2 11
3 7
3 9
3 10
4 12
4 9
5 10
-1 -1
 */
