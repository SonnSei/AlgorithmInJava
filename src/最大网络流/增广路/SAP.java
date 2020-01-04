package 最大网络流.增广路;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Classname SAP
 * @Description 最短增广路算法/SAP/EK（Edmonds-karp）
 * @Date 2019/12/27 19:51
 * @Author SonnSei
 */
public class SAP {
    public static void main(String[] args) {
        int n, m;
        int source,sink;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        source = sc.nextInt();
        sink = sc.nextInt();
        int[][] graphResidual = new int[n][n];
        for (int i = 0; i < m; i++) {
            graphResidual[sc.nextInt()-1][sc.nextInt()-1] = sc.nextInt();
        }
        int[] pre = new int[graphResidual .length];
        int ret = EK(graphResidual ,pre, source, sink);
        System.out.println(ret);
    }
    /**
     * 寻找增广路
     * @param source 源点
     * @param sink 汇点
     * @param graphResidual  图
     * @return
     */
    static boolean hasAugmentingPah(int[][] graphResidual ,int[] pre,int source, int sink) {
        int n = graphResidual .length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(pre, -1);
        visited[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i = 0; i <n; i++) {
                if (!visited[i] && graphResidual [now][i] > 0) {
                    visited[i] = true;
                    pre[i] = now;
                    // 找到一条增广路
                    if(i == sink)return true;
                    queue.add(i);
                }
            }
        }
        return false;
    }

    /**
     * EK求最大流
     * @param graphResidual  残余流
     * @param source
     * @param sink
     * @return
     */
    static int EK(int[][] graphResidual , int[] pre,int source, int sink) {
        int n = graphResidual .length;
        int[][] graphCur = new int[n][n];// 残余流
        int maxFlow = 0;
        while (hasAugmentingPah(graphResidual ,pre, source, sink)) {
            // 可以增广
            int curIndex = sink;
            int preIndex;
            int limit = Integer.MAX_VALUE;
            while (curIndex != source) {
                preIndex = pre[curIndex];
                limit = Math.min(limit,graphResidual [preIndex][curIndex]);
                curIndex = preIndex;
            }
            maxFlow+=limit;
            curIndex = sink;
            while (curIndex != source) {
                preIndex = pre[curIndex];
                graphResidual [preIndex][curIndex]-=limit;// 残余网络正向边减流
                graphResidual [curIndex][preIndex]+=limit;// 残余网络反向边增流
                // 为什么要先判断反向？ 因为我们没有让正向+反向流量一直为0，当存在反向时，要先反向减流量
                // 当正向为0的时候，不代表反向没有流量
                if (graphCur[curIndex][preIndex] > 0) {
                    // 与增广路反向的边减流
                    graphCur[curIndex][preIndex] -= limit;
                } else {
                    // 与增广路同向的边增流
                    graphCur[preIndex][curIndex] += limit;
                }

                curIndex = preIndex;
            }
        }
        System.out.println("实时流：");
        print(graphCur);
        return maxFlow;
    }

    private static void print(int[][] graphResidual ) {
        for (int[] row :
                graphResidual ) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("************************************");
    }
}
