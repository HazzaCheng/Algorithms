package com.hazza.algorithms.FastMatrixExponentiation;

/**
 * Created with IntelliJ IDEA.
 * Description: Find all pairs shortest paths using DP and Fast Matrix Exponentiation.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-17
 * Time: 11:44 AM
 */
public class AllPairsShortestPaths {
    // the unreachable distance
    private static int INF = Integer.MAX_VALUE / 2- 1000; // prevent integer out of bounds
    // adjacency matrix of weight
    private static int[][] matrix;

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

        // create the adjacency matrix of weight
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) matrix[i][j] = INF;
            matrix[i][i] = 0;
        }
        int len = edges.length;
        for (int i = 0; i < len; i++)
            matrix[edges[i].x][edges[i].y] = edges[i].weight;
    }

    /**
     * Extend one edge to the (m-1) matrix and construct (m) matrix.
     * @param matrix The (m-1) matrix.
     * @param weights The matrix of weights.
     * @return The (m) matrix.
     */
    private static int[][] extendShortestPaths(int[][] matrix, int[][] weights) {
        if (weights == null) return matrix;
        int rowLen = matrix.length, colLen = weights[0].length;
        int midLen = weights.length;
        if (rowLen != colLen && midLen != matrix[0].length)
            throw new RuntimeException("The number of rows and columns must be the same: " +
                "A(" + matrix.length + "," + matrix[0].length + ") B("+ weights.length + "," + weights[0].length + ")");

        int[][] temp = new int[midLen][midLen];
        for (int i = 1; i < midLen; i++) {
            for (int j = 1; j < midLen; j++) {
                temp[i][j] = INF;
                for (int k = 1; k < midLen; k++)
                    temp[i][j] = Math.min(temp[i][j], matrix[i][k] + weights[k][j]);
            }
        }

        return temp;
    }

    /**
     * Find all pairs shortest paths, using DP.
     * @param m The number of vertices.
     * @param edges The edges.
     * @return The shortest paths array.
     */
    public static int[][] showAllPairsShortestPaths(int m, Edge[] edges) {
        init(m, edges);
        int[][] weights = matrix;

        for (int i = 2; i < m; i++)
            matrix = extendShortestPaths(matrix, weights);

        return matrix;
    }

    /**
     * Find all pairs shortest paths, using DP and Fast Matrix Exponentiation.
     * @param m The number of vertices.
     * @param edges The edges.
     * @return The shortest paths array.
     */
    public static int[][] showAllPairsShortestPaths_fast(int m, Edge[] edges) {
        init(m, edges);
        int[][] temp = null; // unit matrix

        int i = m - 1;
        while (i != 0) {
            if ((i & 1) == 1) temp = extendShortestPaths(matrix, temp);
            matrix = extendShortestPaths(matrix, matrix);
            i >>= 1;
        }

//        int i = 1;
//        while (i < m - 1) {
//            matrix = extendShortestPaths(matrix, matrix);
//            i <<= 1;
//        }
//        temp = matrix;

        return temp;
    }

}
