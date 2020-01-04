package 最大网络流.增广路;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Classname ISAP
 * @Description SAP贴标签优化
 * @Date 2019/12/31 16:38
 * @Author SonnSei
 */
public class ISAP {
    static class Vertex {
        public Vertex(int first) {
            this.first = first;
        }

        int first;
    }

    static class Edge {
        int v;
        Integer next;
        int cap, flow;
    }

    static Vertex[] heads;
    static Edge[] edges;
    static int[] height;
    static int[] pre;
    static int[] count;// 每个高度的节点有多少个
    static int top;

    public static void main(String[] args) {
        // n个点，m条边
        int n, m;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        init(n, m);
        for (int i = 0; i < m; i++) {
            add(sc.nextInt()-1 , sc.nextInt()-1 , sc.nextInt());
        }
        printGraph(n);
        int ret = isap(0, n - 1, n);
        System.out.println(ret);

    }

    private static void printGraph(int n) {
        System.out.println("网络邻接表状态");
        for (int i = 0; i <n; i++) {
            System.out.print("v"+i+" ["+heads[i].first);
            for (int j = heads[i].first; j >= 0; j = edges[j].next) {
                System.out.print("]--[ "+edges[j].v+" "+edges[j].cap+" "+edges[j].flow+" "+edges[j].next);
            }
            System.out.print("]\n");
        }
    }

    private static int isap(int source, int sink, int n) {
        setHeight(sink, n);
        int ret = 0,u = source;
        int d=0;
        while (height[source] < n) {
            int i = heads[u].first;
            if(u == source)
                d = Integer.MAX_VALUE;
            for (; i >= 0; i = edges[i].next) {
                int v = edges[i].v;
                if (edges[i].cap > edges[i].flow && height[u] == height[v] + 1) {
                    u = v;
                    pre[v] = i;
                    d = Math.min(d, edges[i].cap - edges[i].flow);
                    if (u == sink) {
                        System.out.print("增广路径"+sink);
                        while (u != source) {
                            int j = pre[u];
                            edges[j].flow+=d;
                            edges[j^1].flow-=d;
                            u = edges[j^1].v;
                            System.out.print("--"+u);
                        }
                        System.out.println("增流 "+d);
                        ret+=d;
                        d = Integer.MAX_VALUE;
                    }
                    break;
                }

            }
            if (i == -1) {
                if (--count[height[u]] == 0) {
                    break;
                }
                int hmin = n-1;
                for (int j = heads[u].first; j >= 0; j = edges[j].next) {
                    if (edges[j].cap > edges[j].flow) {
                        hmin = Math.min(hmin, height[edges[j].v]);
                    }
                }
                height[u] = hmin+1;
                System.out.println("重贴标签后的高度");
                System.out.print("h[]=");
                for (int k = 0; k < n; k++) {
                    System.out.print(" "+height[k]);
                }
                System.out.print("\n");
                ++count[height[u]];
                if (u != source) {
                    u = edges[pre[u]^1].v;
                }
            }
        }
        return ret;
    }

    private static void setHeight(int sink, int n) {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(height, -1);
        Arrays.fill(count, 0);
        height[sink] = 0;
        queue.add(sink);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            ++count[height[v]];
            for(int j = heads[v].first;j>=0;j = edges[j].next){
                int u = edges[j].v;
                if (height[u] == -1) {
                    height[u] = height[v]+1;
                    queue.add(u);
                }
            }


        }
        System.out.println("初始化高度");
        for (int i = 0; i < n; i++) {
            System.out.print("  " + height[i]);
        }
        System.out.println();
    }

    private static void add(int v1, int v2, int flow) {
        addEdge(v1, v2, flow);
        addEdge(v2, v1, 0);
    }

    private static void addEdge(int v1, int v2, int flow) {
        edges[top].v = v2;
        edges[top].cap = flow;
        edges[top].flow = 0;
        edges[top].next = heads[v1].first;
        heads[v1].first = top++;
    }

    private static void init(int n, int m) {
        // 初始化邻接表头，设置next为-1
        heads = new Vertex[n];
        for (int i = 0; i < n; i++) {
            heads[i] = new Vertex(-1);
        }
        // 初始化边的下标为0
        top = 0;


        edges = new Edge[2 * m];
        for (int i = 0; i < 2 * m; i++) {
            edges[i] = new Edge();
        }

        height = new int[n+1];
        pre = new int[n+1];
        count = new int[n+1];
    }
}

/*
6 9
1 3 10
1 2 12
2 4 8
3 5 13
3 2 2
4 6 18
4 3 5
5 6 4
5 4 6
 */
