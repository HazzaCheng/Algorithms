package com.hazza.algorithms.graph.EdmondsKarp;

import com.hazza.algorithms.utils.ds.CostFlowEdge;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description: Use Edmonds-Karp Algorithm and Bellman-Ford Algorithm to slove Minimum Cost Maximum Flow problem.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-6-12
 * Time: 4:29 OM
 */
public class MinimumCostMaximumFlow {
    private static final int INF = Integer.MAX_VALUE - 100000;

    /**
     * The number of vertices.
     */
    private static int n;
    /**
     * The number of edges.
     */
    private static int m;
    /**
     * The maximum flow.
     */
    private static int maxFlow;
    /**
     * The minimum cost.
     */
    private static int minCost;
    /**
     * The edges in the flow networks.
     */
    private static CostFlowEdge[] edges;
    /**
     * The flow networks.
     */
    private static Map<Integer, List<Integer>> graph;
    /**
     * The increment from the start.
     */
    private static int[] a;
    /**
     * The paths.
     */
    private static int[] p;
    /**
     * The cost.
     */
    private static int[] d;

    /**
     *  Util class, cannot be instantiated.
     */
    private MinimumCostMaximumFlow() {}

    /**
     * The initialization.
     * @param vertexNum The number of vertices.
     * @param edgeNum The number of edges.
     * @param edgesArr The input edges.
     */
    private static void init(int vertexNum, int edgeNum, int[][] edgesArr) {
        n = vertexNum;
        m = edgeNum;

        edges = new CostFlowEdge[m * 2];
        graph = new HashMap<>(n);

        a = new int[n];
        p = new int[n];
        d = new int[n];

        maxFlow = 0;
        minCost = 0;

        addEdges(edgesArr);
    }

    /**
     * Add edges to the graph.
     * @param edgesArr The input edges.
     */
    private static void addEdges(int[][] edgesArr) {
        int i = 0;
        for (int[] edge: edgesArr) {
            edges[i++] = new CostFlowEdge(edge[0], edge[1], 0, edge[2], edge[3]);
            // The antiparallel edge.
            edges[i++] = new CostFlowEdge(edge[1], edge[0], 0, 0, -edge[3]);
            List<Integer> temp;
            temp = graph.getOrDefault(edge[0], new ArrayList<>());
            temp.add(i - 2);
            graph.put(edge[0], temp);
            temp = graph.getOrDefault(edge[1], new ArrayList<>());
            temp.add(i - 1);
            graph.put(edge[1], temp);
        }
    }

    /**
     * Use Bellman-Ford Algorithm to find the augmenting path.
     * @param start The source node.
     * @param end The sink node.
     * @return Whether finding the augmenting path or not.
     */
    private static boolean bellmanFord(int start, int end) {

        Queue<Integer> queue = new LinkedList<>();
        boolean[] inQueue = new boolean[n];

        for (int i = 0; i < n; i++) {
            d[i] = INF;
            a[i] = 0;
            p[i] = i;
        }

        queue.offer(start);
        a[start] = INF;
        d[start] = 0;
        inQueue[start] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;

            List<Integer> connected = graph.getOrDefault(u, new ArrayList<>());
            int len = connected.size();

            for (int i = 0; i < len; i++) {
                CostFlowEdge e = edges[connected.get(i)];
                if (e.flow < e.cap && d[e.to] > d[u] + e.cost) {
                    p[e.to] = connected.get(i);
                    a[e.to] = Math.min(a[u], e.cap - e.flow);
                    d[e.to] = d[u] + e.cost;
                    if (!inQueue[e.to]) {
                        queue.offer(e.to);
                        inQueue[e.to] = true;
                    }
                }
            }
        }
        // find all augmenting paths
        if (d[end] == INF) {
            return false;
        }
        // add the flow
        for (int u = end; u != start; u = edges[p[u]].from) {
            edges[p[u]].flow += a[end];
            edges[p[u] ^ 1].flow -= a[end];
        }

        maxFlow += a[end];
        minCost += a[end] * d[end];

        return true;
    }

    /**
     * Use Edmonds-Karp Algorithm to find the maximum flow and minimum cost,
     * the network shouldn't contain the circle with negative weight.
     * @param n The number of vertices.
     * @param m The number of edges.
     * @param edgesArr The input edges.
     * @param start The source node.
     * @param end The sink node.
     * @return
     */
    public static int[] minCostMaxFlow(int n, int m, int[][] edgesArr, int start, int end) {
        init(n, m, edgesArr);

        while (bellmanFord(start, end)){}

        return new int[]{maxFlow, minCost};
    }
}
