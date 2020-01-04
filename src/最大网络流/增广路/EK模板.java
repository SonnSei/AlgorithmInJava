package 最大网络流.增广路;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Classname EK
 * @Description TODO
 * @Date 2019/12/31 9:34
 * @Author SonnSei
 */
class EK {
    private int[][] graphCur;
    private int[][] graphResidual;
    int[] pre;
    final int N;
    boolean[] visited;
    int source, sink;
    int maxFlow;

    public EK(int[][] graphResidual) {
        N = graphResidual.length;
        this.graphResidual = graphResidual;
        graphCur = new int[N][N];
        pre = new int[N];
        visited = new boolean[N];
        maxFlow = -1;
    }

    public int getMaxFlow(int source, int sink) {
        maxFlow = 0;
        this.source = source;
        this.sink = sink;
        while (bfs()) {
            int curIndex = sink;
            int preIndex;
            int limit = Integer.MAX_VALUE;
            while (curIndex != source) {
                preIndex = pre[curIndex];
                limit = Math.min(limit, graphResidual[preIndex][curIndex]);
                curIndex = preIndex;
            }
            maxFlow += limit;
            curIndex = sink;
            while (curIndex != source) {
                preIndex = pre[curIndex];
                graphResidual[preIndex][curIndex] -= limit;
                graphResidual[curIndex][preIndex] += limit;
                if (graphCur[curIndex][preIndex] > 0) graphCur[curIndex][preIndex] -= limit;
                else graphCur[preIndex][curIndex] += limit;
                curIndex = preIndex;
            }
        }
        return maxFlow;
    }

    public int[][] getGraphCur(){
        return graphCur;
    }

    private boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(pre, -1);
        Arrays.fill(visited, false);
        visited[source] = true;
        queue.add(source);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i = 0; i < N; i++) {
                if (!visited[i] && graphResidual[now][i] > 0) {
                    visited[i] = true;
                    pre[i] = now;
                    if (i == sink) return true;
                    queue.add(i);
                }
            }
        }
        return false;
    }
}
