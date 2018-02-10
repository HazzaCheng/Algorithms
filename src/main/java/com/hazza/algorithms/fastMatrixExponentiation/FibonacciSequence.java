package com.hazza.algorithms.fastMatrixExponentiation;

/**
 * Created with IntelliJ IDEA.
 * Description: Use Fast Matrix Exponentiation to solve Fibonacci Sequence.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-9
 * Time: 9:05
 */
public class FibonacciSequence {

    /**
     * Calculate the n^th fibonacci sequence.
     * @param n The n^th fibonacci sequence
     * @return The value n^th fibonacci sequence.
     */
    int[][] fastMatrixFibonacci(int n) {
        int[][] a = {{1, 1}, {1, 0}};
        int[][] a0 = {{1, 0}, {0, 1}};
        int[][] b = {{1}, {1}};
        while (n != 0) {
            if ((n & 1) == 1) a0 = matrixMultiply(a, a0);
            a = matrixMultiply(a, a);
            n >>= 1;
        }

        return matrixMultiply(a0, b);
    }

    /**
     * Multiply two matrices.
     * @param a Matrix a.
     * @param b Matrix b.
     * @return The product of two matrices.
     */
    int[][] matrixMultiply(int[][] a, int[][] b) {
        int rowLen = a.length, colLen = b[0].length;
        int midLen = b.length;
        if (midLen != a[0].length) throw new RuntimeException("Two matrix cannot be multiplied: " +
                "A(" + a.length + "," + a[0].length + ") B("+ b.length + "," + b[0].length + ")");
        int[][] res = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++)
            for (int j = 0; j < colLen; j++)
                for (int k = 0; k < midLen; k++)
                    res[i][j] += b[k][j] * a[i][k];

        return res;
    }
}
