package 最大网络流.增广路;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname SAP
 * @Description 最短增广路算法/SAP/EK（Edmonds-karp）
 * @Date 2019/12/27 19:51
 * @Author SonnSei
 */
public class SAP {
    public static void main(String[] args) {
        int n = 6;
        int[][] graph = new int[n][n];
        graph[0][1] = 12;
        graph[0][3] = 10;
        graph[1][3] = 8;
        graph[2][1] = 2;
        graph[2][4] = 13;
        graph[3][2] = 5;
        graph[3][5] = 18;
        graph[4][3] = 6;
        graph[4][5] = 4;
        int ret = EK(graph, 0, 5);
        System.out.println(ret);
    }
    static int[] pre ;
    /**
     * 寻找增广路
     * @param start 起点
     * @param end 终点
     * @param graphRemain 图
     * @return
     */
    static boolean bfs(int[][] graphRemain,int start, int end) {
        int n = graphRemain.length;
        pre= new int[n];
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(pre, -1);
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i = 0; i <n; i++) {
                if (!visited[i] && graphRemain[now][i] > 0) {
                    visited[i] = true;
                    pre[i] = now;
                    // 找到一条增广路
                    if(i == end)return true;
                    queue.add(i);
                }
            }
        }
        return false;
    }

    /**
     * EK求最大流
     * @param graphRemain 残余流
     * @param start
     * @param end
     * @return
     */
    static int EK(int[][] graphRemain, int start, int end) {
        print(graphRemain);
        int n = graphRemain.length;
        int[][] graphCur = new int[n][n];// 残余流
        int maxFlow = 0;
        while (bfs(graphRemain, start, end)) {
            // 可以增广
            int curIndex = end;
            int preIndex=start;
            int limit = Integer.MAX_VALUE;
            while (curIndex != start) {
                preIndex = pre[curIndex];
                limit = Math.min(limit,graphRemain[preIndex][curIndex]);
                curIndex = preIndex;
            }
            maxFlow+=limit;
            curIndex = end;
            while (curIndex != start) {
                preIndex = pre[curIndex];
                graphRemain[preIndex][curIndex]-=limit;// 残余网络正向边减流
                graphRemain[curIndex][preIndex]+=limit;// 残余网络反向边增流
                if (graphCur[curIndex][preIndex] > 0) {
                    graphCur[curIndex][preIndex] -= limit;
                } else {
                    graphCur[preIndex][curIndex] += limit;
                }
                curIndex = preIndex;
            }
        }
        System.out.println("实时流：");
        print(graphCur);
        return maxFlow;
    }

    private static void print(int[][] graphRemain) {
        for (int[] row :
                graphRemain) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("************************************");
    }
}
