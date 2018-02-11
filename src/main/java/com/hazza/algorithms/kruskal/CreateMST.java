package com.hazza.algorithms.kruskal;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description: Create a minimum spanning tree from the input using Kruskal's Algorithm.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-10
 * Time: 9:04 PM
 */
public class CreateMST {
    private static int[] node;  // represent the tree
    private static int[] rank;  // the frequency of the node

    /**
     * Util class, cannot be instantiated.
     */
    private CreateMST() {}

    /**
     * Initialize the arrays.
     * @param m The number of nodes.
     */
    private static void init(int m) {
        node = new int[m + 1];
        rank = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            node[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Find the root of the tree that contains the node.
     * @param x The node.
     * @return The root.
     */
    private static int findInTree(int x) {
        int root = x;
        while (x != node[x]) x = node[x];
        while (x != root) {
            int temp = node[x];
            node[x] = root;
            x = temp;
        }

        return root;
    }

    /**
     * Combine two trees due to its rank.
     * @param x The root of a tree.
     * @param y The root of a tree.
     */
    private static void uniteTrees(int x, int y) {
        if (rank[x] < rank[y]) {
            node[x] = y;
            ++rank[y];
        } else {
            node[y] = x;
            ++rank[x];
        }
    }

    /**
     * Create the MST.
     * @param m The number of nodes.
     * @param edges Given edges.
     * @return The least cost of MST.
     */
    public static int kruskal(int m, Edge[] edges) {
        init(m);
        Arrays.sort(edges);
        int n = edges.length, edgesCount = 0, price = 0;

        for (int i = 0; i < n && edgesCount != m - 1; i++) {
            int xRoot = findInTree(edges[i].x);
            int yRoot = findInTree(edges[i].y);
            if (xRoot != yRoot) {
                uniteTrees(xRoot, yRoot);
                price += edges[i].weight;
                ++edgesCount;
            }
        }
        // if edgesCount < m - 1, the undirected graph cannot be connected
        if (edgesCount < m - 1) throw new RuntimeException("These edges cannot create a MST!");

        return price;
    }
}
