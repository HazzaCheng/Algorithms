package com.hazza.algorithms.SPFA;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * Description: Find the shortest path from the source vertex using Bellman-Ford's Algorithm.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-14
 * Time: 9:05 PM
 */
public class FindShortestPath {
    // dist[i] => the shortest distance form source vertex to vertex i
    private static int[] dist;
    // path[i] => the predecessor vertex of the vertex i
    private static int[] path;
    // inPath[i] => determine vertex i whether in the path
    private static boolean[] inPath;
    // enqueueNum[i] => the nnumber of enqueue for vertex i
    private static int[] enqueNum;
    // adjacency matrix of weight
    private static int[][] matrix;
    // the unreachable distance
    private static int INF = Integer.MAX_VALUE - 1000; // prevent integer out of bounds

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
        enqueNum = new int[m + 1];
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
            dist[i] = INF;
            path[i] = source;
            enqueNum[i] = 0;
        }
        inPath[source] = true;
        dist[source] = 0;
    }

    /**
     * Find the shortest path from source vertex to any vertices and store the paths.
     * @param m The number of vertices.
     * @param source The source vertex.
     * @param edges The edges.
     */
    public static boolean bellmanFord(int m, int source, Edge[] edges) {
        init(m, source, edges);
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(source);
        ++enqueNum[source];
        while (!queue.isEmpty()) {
            int u = queue.poll();
            inPath[u] = false;
            for (int v = 1; v <= m; v++) {
                if (matrix[u][v] < INF) {
                    if (dist[u] + matrix[u][v] < dist[v]) {
                        dist[v] = dist[u] + matrix[u][v];
                        path[v] = u;
                        if (!inPath[v]) {
                            queue.add(v);
                            ++enqueNum[v];
                            if (enqueNum[v] >= m) return false;
                            inPath[v] = true;
                        }
                    }
                }
            }

        }

        return true;
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
