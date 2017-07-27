package com.hazza.alogrithms.matrixChainMultiplication;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by hazza on 7/24/17.
 */
public class MatrixChainMultiplicationTest {
    private MatrixChainMultiplication test = new MatrixChainMultiplication();
    private int[][] m3, m6, mRandom;

    private int[][] insertIntoArray(int... num) {
        int[][] res = new int[num.length / 2][2];
        for (int i = 0, count = 0; i < res.length; i++) {
            for (int j = 0; j < 2; j++) {
                res[i][j] = num[count++];
            }
        }

        return res;
    }

    private int[][] getRandomMatrix(int len) {
        Random random = new Random();
        int[][] matrix = new int[len][2];
        int temp = random.nextInt(500) +1;
        for (int i = 0; i < len; i++) {
            matrix[i][0] = temp;
            matrix[i][1] = random.nextInt(500) + 1;
            temp = matrix[i][1];
        }
        return matrix;
    }

    @Before
    public void setUp() {
        test = new MatrixChainMultiplication();
        m3 = insertIntoArray(10, 100, 100, 5, 5, 50);
        m6 = insertIntoArray(30, 35, 35, 15, 15, 5, 5, 10, 10, 20, 20, 25);
        mRandom = getRandomMatrix(1000);
    }

    @Test
    public void testThree() {
        assertEquals(7500, test.matrixChainOrderVal(m3));
    }

    @Test
    public void testSix() {
        int[][] res = test.matrixChainOrderArr(m6);
        assertEquals(7125, res[1][4]);
    }

    @Test(timeout = 10000)
    public void testRandom() {
        long begin = System.currentTimeMillis();
        int res1 = test.matrixChainOrderVal(mRandom);
        System.out.println("Time of DP: " + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        int res2 = test.memoizedMatrixChain(mRandom);
        System.out.println("Time of Memoized: " + (System.currentTimeMillis() - begin));

        assertEquals(res1, res2);
    }
}
