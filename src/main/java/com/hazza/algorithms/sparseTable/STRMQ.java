package com.hazza.algorithms.sparseTable;

/**
 * Created with IntelliJ IDEA.
 * Description: Program for range minimum query using sparse table method.
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-09-11
 * Time: 9:28 AM
 */
public class STRMQ {
    private static int[][] lookup;

    /**
     * Construct the dp array.
     * @param arr the input array
     */
    static void preprocess(int[] arr) {
        int n = arr.length;
        int col = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        lookup = new int[n][col];
        for (int i = 0; i < n; i++) lookup[i][0] = arr[i];
        for (int j = 1; (1 << j) <= n; j++)
            for (int i = 0; i + (1 << j) - 1 < n; i++)
                lookup[i][j] = Math.min(lookup[i][j - 1], lookup[i + (1 << (j - 1))][j - 1]);
    }

    /**
     * Return the minimum value from start to end.
     * @param arr the input array
     * @param start the starting index
     * @param end the ending index
     * @return the minimum number
     */
    int query(int[] arr, int start, int end) {
        int j = (int) (Math.log(end - start + 1) / Math.log(2));
        return Math.min(lookup[start][j],
                lookup[end - (int) Math.pow(2, j)][j]);
    }

}
