package com.hazza.algorithms.graph.EdmondsKarp;

import com.hazza.algorithms.utils.ds.FlowEdge;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description: Use Edmond-Karp Algorithm to solve maximum flow problem.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-6-10
 * Time: 7:27 PM
 */
public class MaximumFlow {
    private static final int INF = Integer.MAX_VALUE - 100000;

    /**
     * The edges in the flow networks.
     */
    private static FlowEdge[] edges;
    /**
     * The flow networks.
     */
    private static Map<Integer, List<Integer>> graph;


    /**
     *  Util class, cannot be instantiated.
     */
    private MaximumFlow() {}

    /**
     * The initialization.
     * @param n The number of vertices.
     * @param m The number of edges.
     * @param edgesArr The input edges.
     */
    private static void init(int n, int m, int[][] edgesArr) {
        edges = new FlowEdge[m * 2];
        graph = new HashMap<>(n);

        addEdges(edgesArr);
    }

    /**
     * Add edges to the graph.
     * @param edgesArr
     */
    private static void addEdges(int[][] edgesArr) {
        int len = edgesArr.length;

        int i = 0;
        for (int[] edge: edgesArr) {
            edges[i++] = new FlowEdge(edge[0], edge[1], 0, edge[2]);
            // The antiparallel edge.
            edges[i++] = new FlowEdge(edge[1], edge[0], 0, 0);
            List<Integer> temp;
            temp = graph.getOrDefault(edge[0], new ArrayList<>());
            temp.add(i - 2);
            graph.put(edge[0], temp);
            temp = graph.getOrDefault(edge[1], new ArrayList<>());
            temp.add(i - 1);
            graph.put(edge[1], temp);
        }
    }

    public static int edmondsKarp(int n, int m, int[][] edgesArr, int start, int end) {
        init(n, m, edgesArr);

        int ans = 0;

        while (true) {
            Queue<Integer> queue = new LinkedList<>();
            // the increment from the start
            int[] a = new int[n];
            // the paths.
            int[] p = new int[n];

            queue.offer(start);
            a[start] = INF;
            while (!queue.isEmpty()) {
                int x = queue.poll();
                List<Integer> connected = graph.getOrDefault(x, new ArrayList<>());
                int len = connected.size();
                for (int i = 0; i < len; i++) {
                    FlowEdge e = edges[connected.get(i)];
                    if (a[e.to] == 0 && e.flow < e.cap) {
                        p[e.to] = connected.get(i);
                        a[e.to] = Math.min(a[x], e.cap - e.flow);
                        queue.offer(e.to);
                    }
                }

                if (a[end] != 0) {
                    break;
                }
            }
            // find all augmenting paths
            if (a[end] == 0) {
                break;
            }
            // add the flow
            for (int u = end; u != start; u = edges[p[u]].from) {
                edges[p[u]].flow += a[end];
                edges[p[u] ^ 1].flow -= a[end];
            }
            ans += a[end];
        }

        return ans;
    }

}
