package com.hazza.algorithms.optimalBinarySearchTree;

/**
 * Created with IntelliJ IDEA.
 *
 * Description: The problem of calculate the cost of searching BST
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-08-08
 * Time: 9:02 PM
 */
public class OptimalBinarySearchTree {

    /**
     * Get the minimum search cost in BST and restore roots of BST,
     * and print the tree in the end.
     *
     * @param n the amount of keys
     * @param p the search cost of keys
     * @param q the search cost of dummy keys
     * @return the minimum search cost in BST
     */
    double getOptimalBST(int n, double[] p, double[] q) {
        double[][] e = new double[n + 2][n + 1];
        double[][] w = new double[n + 2][n + 1];
        int[][] root = new int[n + 1][n + 1];

        for (int i = 1; i <= n + 1; i++) {
            e[i][i - 1] = q[i - 1];
            w[i][i - 1] = q[i - 1];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
                for (int r = i; r <= j; r++) {
                    double temp = e[i][r - 1] + e[r + 1][j] + w[i][j];
                    if (e[i][j] > temp) {
                        e[i][j] = temp;
                        root[i][j] = r;
                    }
                }
            }
        }
        printRoots(root, 1, n, 1);

        return e[1][n];
    }

    /**
     * Print the root, the root's left node, the root's right node
     * and the the depth of root in tree, recursively.
     *
     * @param root the array which store roots form i to j
     * @param i row index
     * @param j column index
     * @param level depth
     * @return the root
     */
    private int printRoots(int[][] root, int i, int j, int level) {
        if (i > j) return 0;
        int r = root[i][j];
        int left = printRoots(root, i, r - 1, level + 1);
        int right = printRoots(root, r + 1, j, level + 1);
        System.out.println("level: " + level +
                " root: k" + r + " " +
                "left: " + (left == 0 ? "None" : "k" + left) + " " +
                "right: " + (right == 0 ? "None" : "k" + right));

        return r;
    }
}
