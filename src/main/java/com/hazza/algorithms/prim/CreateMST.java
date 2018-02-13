package com.hazza.algorithms.prim;

/**
 * Created with IntelliJ IDEA.
 * Description: Create a minimum spanning tree from the input using Prim's Algorithm.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-11
 * Time: 7:33 PM
 */
public class CreateMST {
    // adjacency matrix of weight
    private static int[][] matrix;
    // lowcost[i] => the shortest path weight for the vertex i to the vertices in MST
    private static int[] lowWeight;
    // determine if the vertice is in the MST
    private static boolean[] inTree;
    // record the parent node of vertex
    private static int[] path;

    /**
     * Util class, cannot be instantiated.
     */
    private CreateMST() {}

    /**
     * Initialize the arrays.
     * @param n The number of vertices.
     * @param edges The edges.
     */
    private static void init(int n, Edge[] edges) {
        lowWeight = new int[n + 1];
        inTree = new boolean[n + 1];
        path = new int[n + 1];
        matrix = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            lowWeight[i] = Integer.MAX_VALUE;   // the path is unreachable
            inTree[i] = false;
            path[i] = i;
            for (int j = 1; j <= n; j++) matrix[i][j] = Integer.MAX_VALUE;
            matrix[i][i] = 0;
        }

        // create the adjacency matrix of weight
        int len = edges.length;
        for (int i = 0; i < len; i++)
            matrix[edges[i].x][edges[i].y] = matrix[edges[i].y][edges[i].x] = edges[i].weight;
    }

    /**
     * Get the lowest weight to vertex k and set the path.
     * @param n The number of vertices.
     * @param k The vertex k.
     */
    private static void getLowestWeight(int n, int k) {
        for (int i = 1; i <= n ; i++) {
            if (lowWeight[i] > matrix[i][k]) {
                lowWeight[i] = matrix[i][k];
                path[i] = k;
            }
        }
    }

    /**
     * Create the MST.
     * @param m The number of vertices.
     * @param root The root node.
     * @param edges The edges.
     * @return The least cost of MST.
     */
    public static int prim(int m, int root, Edge[] edges) {
        init(m, edges);
        int price = 0;
        getLowestWeight(m, root);
        inTree[root] = true; // add the root node
        // add the rest nodes
        int edgesCount = 0; // the number of edges added to the MST
        while (edgesCount != m - 1) {
            int k = 0;
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= m; j++) {
                if (!inTree[j] && min > lowWeight[j]) {
                    min = lowWeight[j];
                    k = j;
                }
            }
            if (k == 0) break;
            ++edgesCount;
            price += min;
            inTree[k] = true;
            getLowestWeight(m, k);
        }
        // if edgesCount < m - 1, the undirected graph cannot be connected
        if (edgesCount < m - 1) throw new RuntimeException("These edges cannot create a MST!");

        return price;
    }

}
