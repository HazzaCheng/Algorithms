package com.hazza.longestCommonSubsequence;

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
    public int getLCSlen(char[] x, char[] y) {
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
                } else if (c[i - 1][j] > c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 2;    // 2 means pointing the left
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 3;    // 3 means pointing the top
                }
            }
        }

        return c[lenX][lenY];
    }


}
