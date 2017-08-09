package com.hazza.algorithms.longestCommonSubsequence;

/**
 * Created by hazza on 7/27/17.
 * The problem of longest-common-subsequence(LCS)
 */
public class LongestCommonSubsequence {

    /**
     * A direct down-top method, return the length  of the LCS of two char sequences,
     * using DP, also print the LCS.
     *
     * @author HazzaCheng
     * @param x a char sequence
     * @param y a char sequence
     * @return the length of the LCS of x and y
     */
    int getLCSlen(char[] x, char[] y) {
        int lenX = x.length, lenY = y.length;
        int[][] c = new int[lenX + 1][lenY + 1];
        int[][] b = new int[lenX + 1][lenY + 1];
        for (int i = 0; i < lenX; i++) {
            for (int j = 0; j < lenY; j++) {
                c[i][j] = 0;
                b[i][j] = 0;
            }
        }

        for (int i = 1; i <= lenX; i++) {
            for (int j = 1; j <= lenY; j++) {
                if (x[i - 1] == y[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;    // 1 means pointing the top left
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2;    // 2 means pointing the top
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;    // 3 means pointing the left
                }
            }
        }
        printLCS(b, x, lenX, lenY);
        System.out.println();
        printLCS2(c, x, y, lenX, lenY);
        System.out.println();
        return c[lenX][lenY];
    }

    /**
     * print the LCS with extra space, recursively
     *
     * @param b the array of pointers to the optimal schemes
     * @param x a sequence
     * @param i row index
     * @param j column index
     */
    private void printLCS(int[][] b, char[] x, int i, int j) {
        if (i == 0 || j == 0) return;
        if (b[i][j] == 1) {
            printLCS(b, x, i - 1, j - 1);
            System.out.print(x[i - 1] + " ");
        }
        else if (b[i][j] == 2) printLCS(b, x, i - 1, j);
        else printLCS(b, x, i, j - 1);
    }

    /**
     * print the LCS without extra space, recursively
     *
     * @param c the array of
     * @param x a sequence
     * @param y a sequence
     * @param i row index
     * @param j column index
     */
    private void printLCS2(int[][] c, char[] x, char[] y, int i, int j) {
        if (i == 0 || j == 0) return;
        if (x[i - 1] == y[j - 1]) {
            printLCS2(c, x, y, i - 1, j - 1);
            System.out.print(x[i - 1] + " ");
        }
        else if (c[i - 1][j] >= c[i][j - 1]) printLCS2(c, x, y, i - 1, j);
        else printLCS2(c, x, y, i, j - 1);
    }



}
