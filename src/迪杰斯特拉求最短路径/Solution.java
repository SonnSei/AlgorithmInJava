package 迪杰斯特拉求最短路径;

/**
 * @Classname Solution
 * @Description
 * @Date 2020/2/12 11:04
 * @Author SonnSei
 */
public class Solution {
    static final int N = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] map = new int[][]{
                {N, 2, 5, N, N},
                {N, N, 2, 6, N},
                {N, N, N, 7, 1},
                {N, N, 2, N, 5},
                {N, N, N, N, N},
        };

        dijkstra(map, 2, 4);
    }

    public static void dijkstra(int[][] map, int start, int end) {
        int n = map.length;
        int[] dist = new int[n];
        int[] p = new int[n];
        boolean[] flag = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = map[start][i];
            p[i] = start;
        }
        p[start] = start;
        dist[start] = 0;
        flag[start] = true;

        for (int i = 0; i < n; i++) {
            int temp = N, t = start;
            //找到P-V中距离最短的
            for (int j = 0; j < n; j++)
                if (!flag[j] && dist[j] < temp) {
                    t = j;
                    temp = dist[j];
                }

            if (t == start)
                break;
            flag[t] = true;
            for (int k = 0; k < n; k++) {
                if (!flag[k] && map[t][k] < N)
                    if (dist[k] > dist[t] + map[t][k]) {
                        dist[k] = dist[t] + map[t][k];
                        p[k] = t;
                    }
            }
        }

        System.out.println(start + "到" + end + "的最短路径为" + dist[end]);
        String path = "end";
        int index = end;
        while (p[index] != index) {
            path = index + "-->" + path;
            index = p[index];
        }
        path = start + "-->" + path;
        System.out.println(path);
    }
}

