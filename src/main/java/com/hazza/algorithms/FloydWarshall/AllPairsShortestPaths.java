package com.hazza.algorithms.FloydWarshall;

import com.hazza.algorithms.ds.Edge;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created with IntelliJ IDEA.
 * Description: Find all pairs shortest paths using Floyd-Warshall Algorithm.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-20
 * Time: 11:44 AM
 */
public class AllPairsShortestPaths {
    // the unreachable distance
    private static int INF = Integer.MAX_VALUE / 2- 1000; // prevent integer out of bounds
    // adjacency matrix of weight
    private static int[][] matrix;
    // matrix of path
    private static int[][] paths;

    /**
     *  Util class, cannot be instantiated.
     */
    private AllPairsShortestPaths() {}

    /**
     * Initialize the arrays.
     * @param n The number of vertices.
     * @param edges The edges.
     */
    private static void init(int n, Edge[] edges) {
        matrix = new int[n + 1][n + 1];
        paths = new int[n + 1][n + 1];

        // create the adjacency matrix of weight and the matrix of path
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = INF;
                paths[i][j] = INF;
            }
            matrix[i][i] = 0;
        }
        int len = edges.length;
        for (int i = 0; i < len; i++) {
            matrix[edges[i].x][edges[i].y] = edges[i].weight;
            paths[edges[i].x][edges[i].y] = edges[i].x;
        }
    }

    /**
     * Find all pairs shortest paths, using Floyd-Warshall Algorithm.
     * @param m The number of vertices.
     * @param edges The edges.
     * @return The shortest paths array.
     */
    public static int[][] floydWarshall(int m, Edge[] edges) {
        init(m, edges);

        for (int k = 1; k <= m; k++)
            for (int i = 1; i <= m; i++)
                for (int j = 1; j <= m; j++)
                    if (matrix[i][k] != INF
                            && matrix[k][j] != INF
                            && matrix[i][j] > matrix[i][k] + matrix[k][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        paths[i][j] = paths[k][j];
                    }

        return matrix;
    }

    /**
     * Return the matrix of paths.
     * @return The matrix of paths.
     */
    public static int[][] getExactPaths() {
        return paths;
    }

    /**
     * Print the path from vertex i to vertex j.
     * @param i The vertex.
     * @param j The vertex.
     */
    public static void printPath(int i, int j) {
        Deque<Integer> stack = new ArrayDeque<>();
        int temp = j;
        while (paths[i][temp] != INF && paths[i][temp] != i) {
            temp = paths[i][temp];
            stack.push(temp);
        }
        System.out.print(i + " --> ");
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " --> ");
        System.out.println(j + " weights: " + matrix[i][j]);
    }

    /**
     * Judge all the pairs paths whether connected.
     * @param m The number of vertices.
     * @param edges The edges.
     * @return The matrix of whether connected.
     */
    public static boolean[][] transitiveClosure(int m, Edge[] edges) {
        boolean[][] connected = new boolean[m + 1][m + 1];

        // initialize the connected matrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) connected[i][j] = false;
            connected[i][i] = true;
        }
        for (Edge edge: edges)
            connected[edge.x][edge.y] = true;

        for (int k = 1; k <= m; k++)
            for (int i = 1; i <= m; i++)
                for (int j = 1; j <= m; j++)
                    connected[i][j] = connected[i][j] || (connected[i][k] && connected[k][j]);

        return connected;
    }
}
