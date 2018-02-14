package com.hazza.algorithms.FastMatrixExponentiation;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-9
 * Time: 9:24 PM
 */
public class FibonacciSequenceTest {
    FibonacciSequence test;

    @Before
    public void setUp() {
        test = new FibonacciSequence();
    }

    @Test
    public void testMultiply() {
        int[][] a = {{1, 1}, {1, 0}};
        int[][] b = {{1, 1}, {1, 0}};

        int[][] res = test.matrixMultiply(a, b);
        assertEquals(res[0][0], 2);
        assertEquals(res[0][1], 1);
        assertEquals(res[1][0], 1);
        assertEquals(res[1][1], 1);
    }

    @Test
    public void testFibonacci() {
        int[][] a = {{1, 1}, {1, 0}};
        int[][] b = {{1, 0}, {0, 1}};
        int n = 11;
        int m = n + 1;

        int[][] res1 = test.fastMatrixFibonacci(n);
        int[][] res2 = test.fastMatrixFibonacci(m);
        assertEquals(res2[0][0], res1[0][0] + res1[1][0]);
        assertEquals(res2[1][0], res1[0][0]);

    }
}
