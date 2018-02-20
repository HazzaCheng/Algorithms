package com.hazza.algorithms.Dijkstra;

import com.hazza.algorithms.ds.Edge;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created with IntelliJ IDEA.
 * Description: Find the shortest path from the source vertex using Dijkstra's Algorithm.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-13
 * Time: 19:05 PM
 */
public class FindShortestPath {
    // dist[i] => the shortest distance form source vertex to vertex i
    private static int[] dist;
    // path[i] => the predecessor vertex of the vertex i
    private static int[] path;
    // inPath[i] => determine vertex i whether in the path
    private static boolean[] inPath;
    // the unreachable distance
    private static int INF = Integer.MAX_VALUE - 1000; // prevent integer out of bounds
    // adjacency matrix of weight
    private static int[][] matrix;

    /**
     * Util class, cannot be instantiated.
     */
    private FindShortestPath() {}

    /**
     * Initialized the arrays.
     * @param m The number of vertices.
     * @param source The source vertex.
     * @param edges The edges.
     */
    private static void init(int m, int source, Edge[] edges) {
        dist = new int[m + 1];
        path = new int[m + 1];
        inPath = new boolean[m + 1];
        matrix = new int[m + 1][m + 1];

        // create the adjacency matrix of weight
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) matrix[i][j] = INF;
            matrix[i][i] = 0;
        }
        int len = edges.length;
        for (int i = 0; i < len; i++)
            matrix[edges[i].x][edges[i].y] = edges[i].weight;

        // initialize the dist array for source
        for (int i = 1; i <= m; i++) {
            inPath[i] = false;
            dist[i] = matrix[source][i];
            path[i] = source;
        }
        inPath[source] = true;
    }

    /**
     * Get the lowest weight to vertex k and set the path.
     * @param n The number of vertices.
     * @param k The vertex k.
     */
    private static void getLowestWeight(int n, int k) {
        for (int i = 1; i <= n ; i++) {
           if (matrix[k][i] < INF) {
               if (dist[i] > dist[k] + matrix[k][i]) {
                   dist[i] = dist[k] + matrix[k][i];
                   path[i] = k;
               }
           }
        }
    }

    /**
     * Find the shortest path from source vertex to any vertices and store the paths.
     * @param m The number of vertices.
     * @param source The source vertex.
     * @param edges The edges.
     */
    public static void dijkstra(int m, int source, Edge[] edges) {
        init(m, source, edges);

        int edgesCount = 0;
        while (edgesCount != m - 1) {
            int k = 0, min = INF;
            for (int i = 1; i <= m; i++) {
                if (!inPath[i] && min > dist[i]) {
                    min = dist[i];
                    k = i;
                }
            }
            if (k == 0) break;
            inPath[k] = true;
            ++edgesCount;
            getLowestWeight(m, k);
        }

        // if edgesCount < m - 1, the undirected graph cannot be connected
        if (edgesCount < m - 1) throw new RuntimeException("These edges cannot get a path!");

    }

    /**
     * Get the distance from the source to the v.
     * @param v The vertex.
     * @return The distance.
     */
    public static int getDistanceFromSource(int v) {
        return dist[v];
    }

    /**
     * Print the path from the source to v.
     * @param source The source vertex.
     * @param v The vertex.
     */
    public static void printPath(int source, int v) {
        if (source == v) return;
        Deque<Integer> stack = new ArrayDeque<>();
        int root = v;
        stack.push(root);
        while (root != source) {
            root = path[root];
            stack.push(root);
        }
        stack.pop();
        System.out.print(source);
        while (!stack.isEmpty())
            System.out.print(" --> " + stack.pop());
        System.out.println(" Total distance: " + dist[v]);
    }
}
