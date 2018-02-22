package com.hazza.algorithms.Johnson;

import com.hazza.algorithms.ds.Edge;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description: Find all pairs shortest paths using Johnson Algorithm.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-22
 * Time: 2:35 PM
 */

public class AllPairsShortestPaths {
    // the unreachable distance
    private static int INF = Integer.MAX_VALUE / 2- 1000; // prevent integer out of bounds
    // matrix of weight
    private static int[][] matrix;

    /**
     *  Util class, cannot be instantiated.
     */
    private AllPairsShortestPaths() {}

    /**
     * Initialize the arrays.
     * @param n The number of vertices.
     */
    private static void init(int n) {
        matrix = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                matrix[i][j] = INF;
    }

    /**
     * Add a vertex to the graph and connect the vertex to each vertices in old graph.
     * @param m The number of vertices.
     * @param edges The edges representing old graph.
     * @return The edges representing new graph.
     */
    private static Edge[] addVertex(int m, Edge[] edges) {
        int len = edges.length;
        Edge[] newEdegs = Arrays.copyOf(edges, len + m);
        // add a vertex and connect it with other vertices
        for (int i = 0; i < m; i++)
            newEdegs[len + i] = new Edge(m + 1, i, 0);

        return newEdegs;
    }


    /**
     * Find all pairs shortest paths, using Johnson's Algorithm.
     * @param m The number of vertices.
     * @param edges The edges.
     * @return The shortest paths array.
     */
    public static int[][] johnson(int m, Edge[] edges) {
        init(m);
        Edge[] newEdges = addVertex(m, edges);
        int[] h = new int[m + 1];
        // using Bellman-Ford to get h
        if (com.hazza.algorithms.BellmanFord.FindShortestPath.bellmanFord(m + 1, m + 1, newEdges)) {
            for (int i = 1; i <= m; i++)
                h[i] = com.hazza.algorithms.BellmanFord.FindShortestPath.getDistanceFromSource(i);
        } else throw new RuntimeException("The input graph contains a negative circle!");

        // reset the weight of edges
        for (Edge edge: edges)
            edge.setWeight(edge.weight + h[edge.x] - h[edge.y]);

        // use Dijkstra to get distances
        for (int i = 1; i <= m; i++) {
            com.hazza.algorithms.Dijkstra.FindShortestPath.dijkstra(m, i, edges);
            for (int j = 1; j <= m; j++)
                matrix[i][j] = h[j] - h[i]
                        + com.hazza.algorithms.Dijkstra.FindShortestPath.getDistanceFromSource(j);

        }

        return matrix;
    }


}
