package com.hazza.algorithms.dp;

/**
 * Created by hazza on 7/24/17.
 * The problem of Matrix-chain Multiplication solved by DP.
 */

public class MatrixChainMultiplication {

    // solution 1
    /**
     * A direct down-top method, just return the best value,
     * also print the optimal parentheses scheme,
     * using DP.
     *
     * @author HazzaCheng
     * @param matrixs the input matrix
     * @return the optimal value, also the smallest value
     */
    int matrixChainOrderVal(int[][] matrixs) {
        int n = matrixs.length;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];  //the array of split points

        for (int i = 0; i < n; i++) m[i][i] = 0;
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n -l; i++) {
                int j = i + l;
                int q;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    q = m[i][k] + m[k+1][j] + matrixs[i][0] * matrixs[k][1] * matrixs[j][1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        System.out.println(printOptimalParens(s, 0, n -1));
        return m[0][n - 1];
    }

    /**
     * A direct down-top method, return the whole array,
     * also print the optimal parentheses scheme.
     *
     * @author HazzaCheng
     * @param matrixs the input matrix
     * @return the whole result array
     */
    int[][] matrixChainOrderArr(int[][] matrixs) {
        int n = matrixs.length;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];  //the array of split points

        for (int i = 0; i < n; i++) m[i][i] = 0;
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n -l; i++) {
                int j = i + l;
                int q;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    q = m[i][k] + m[k+1][j] + matrixs[i][0] * matrixs[k][1] * matrixs[j][1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        System.out.println(printOptimalParens(s, 0, n -1));
        return m;
    }

    /**
     * Print the optimal parentheses scheme, recursively.
     *
     * @author HazzaCheng
     * @param s the array of split points
     * @param i the row index
     * @param j the column index
     * @return the optimal parentheses scheme in string
     */
    private String printOptimalParens(int[][] s, int i, int j) {
        if (i == j) return "A[" + i + "]";
        else return "(" + printOptimalParens(s, i, s[i][j])
                + printOptimalParens(s, s[i][j] + 1, j) + ")";
    }

    // solution 2
    /**
     * A direct top-down method using a recursive method with a memorized array.
     *
     * @author HazzaCheng
     * @param matrixs the input martrix
     * @return the optimal value, also the smallest value
     */
    int memoizedMatrixChain(int[][] matrixs) {
        int n = matrixs.length;
        int[][] m = new int[n][n];  //the memorized array
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                m[i][j] = Integer.MAX_VALUE;

        return lookupChain(matrixs, m, 0, n - 1);
    }

    /**
     * Solve subproblems in martrix chain multiplication , recursively.
     *
     * @param matrixs the input martirx
     * @param m the memorized array which store the optimal value temporarily
     * @param i row index
     * @param j column index
     * @return the optimal value when row = i and column = j
     */
    int lookupChain(int[][] matrixs, int[][] m, int i, int j) {
        if (m[i][j] < Integer.MAX_VALUE) return m[i][j];
        if (i == j) m[i][j] = 0;
        else {
            for (int k = i; k < j; k++) {
                int q = lookupChain(matrixs, m, i, k) +
                        lookupChain(matrixs, m, k + 1, j) +
                        matrixs[i][0] * matrixs[k][1] * matrixs[j][1];
                if (q < m[i][j]) m[i][j] = q;
            }
        }

        return m[i][j];
    }
}
