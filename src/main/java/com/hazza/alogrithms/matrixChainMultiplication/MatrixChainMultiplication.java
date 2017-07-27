package com.hazza.alogrithms.matrixChainMultiplication;

/**
 * Created by hazza on 7/24/17.
 * The problem of Matrix-chain Multiplication
 */
public class MatrixChainMultiplication {

    //a direct down-top method, just return the best value
    int matrixChainOrderVal(int[][] matrixs) {
        int n = matrixs.length;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

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

    //a direct down-top method, return the whole array
    int[][] matrixChainOrderArr(int[][] matrixs) {
        int n = matrixs.length;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

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

    // print the optimal parentheses scheme
    private String printOptimalParens(int[][] s, int i, int j) {
        if (i == j) return "A[" + i + "]";
        else return "(" + printOptimalParens(s, i, s[i][j])
                + printOptimalParens(s, s[i][j] + 1, j) + ")";
    }
}
