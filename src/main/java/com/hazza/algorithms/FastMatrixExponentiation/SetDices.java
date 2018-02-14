package com.hazza.algorithms.FastMatrixExponentiation;

/**
 * Created with IntelliJ IDEA.
 * Description: Use DP and Fast Matrix Exponentiation to solve the problem of setting dices.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-8
 * Time: 5:14 PM
 */
public class SetDices {
    public static final int MOD = 1000000007;

    // solution 1

    /**
     * Use DP to solve the problem of setting dices.
     * @param level The number of dices
     * @param mutex The conflict combinations, false means non-conflict and true means conflict.
     * @return Total number of arrangements of dices orders.
     */
    long countOrders_dp(int level, boolean[][] mutex) {
        int[] opposite = {-1, 4, 5, 6, 1, 2, 3};    // the opposite side of dice
        long[][] dp = new long[2][6];
        int e = 0;

        for (int i = 0; i < 6; i++) dp[e][i] = 1;
        for (int i = 2; i <= level; i++) {
            e = 1 - e;
            for (int j = 0; j < 6; j++) {
                dp[e][j] = 0;
                for (int k = 0; k < 6; k++) {
                    if (!mutex[opposite[j + 1]][k + 1])
                        dp[e][j] = (dp[e][j] + dp[1 - e][k]) % MOD;
                }
            }
        }

        long sum = dp[e][0];
        for (int i = 1; i < 6; i++) sum = (sum + dp[e][i]) % MOD;
        sum = (sum * (long) Math.pow(4, level)) % MOD;

        return sum;
    }

    // solution 2

    /**
     * Use Fast Matrix Exponentiation to solve the problem of setting dics.
     * @param level The number of dices
     * @param mutex The conflict combinations, false means non-conflict and true means conflict.
     * @return Total number of arrangements of dices orders.
     */
    long countOrders_matrix(int level, boolean[][] mutex) {
        int[] opposite = {-1, 4, 5, 6, 1, 2, 3};    // the opposite side of dice
        long[][] firstLevel = {{1}, {1}, {1}, {1}, {1}, {1}};  // initialize to the first level
        long[][] unitMatrix = new long[6][6];
        unitMatrix[0][0] = unitMatrix[1][1] = unitMatrix[2][2] = unitMatrix[3][3] = unitMatrix[4][4] = unitMatrix[5][5] = 1;
        long[][] matrix = new long[6][6];
        // initialize to 1
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                matrix[i][j] = 1;
        // set the mutex position to 0
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                if (mutex[i + 1][j + 1])
                    matrix[opposite[i + 1]][j + 1] = 0;
        // fast matrix exponentiation
        int m = level - 1;
        while (m != 0) {
            if ((m & 1) == 1) unitMatrix = matrixMultiply(matrix, unitMatrix);
            matrix = matrixMultiply(matrix, matrix);
            m >>= 1;
        }
        long[][] res = matrixMultiply(unitMatrix, firstLevel);
        long sum = 0;
        for (int i = 0; i < 6; i++) sum = (sum + res[i][0]) % MOD;
        sum = (sum * (long) Math.pow(4, level)) % MOD;

        return sum;
    }

    /**
     * Multiply two matrices.
     * @param a Matrix a.
     * @param b Matrix b.
     * @return The product of two matrices.
     */
    long[][] matrixMultiply(long[][] a, long[][] b) {
        int rowLen = a.length, colLen = b[0].length;
        int midLen = b.length;
        if (midLen != a[0].length) throw new RuntimeException("Two matrix cannot be multiplied: " +
                "A(" + a.length + "," + a[0].length + ") B("+ b.length + "," + b[0].length + ")");
        long[][] res = new long[rowLen][colLen];

        for (int i = 0; i < rowLen; i++)
            for (int j = 0; j < colLen; j++)
                for (int k = 0; k < midLen; k++)
                    res[i][j] = (res[i][j] + b[k][j] * a[i][k]) % MOD;

        return res;
    }
}
