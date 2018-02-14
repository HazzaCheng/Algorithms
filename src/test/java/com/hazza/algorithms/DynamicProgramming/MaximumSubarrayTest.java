package com.hazza.algorithms.DynamicProgramming;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 17-11-28
 * Time: 8:45 PM
 */
public class MaximumSubarrayTest {
    MaximumSubarray test;

    @Before
    public void setup() {
        test = new MaximumSubarray();
    }

    @Test
    public void testDivideAndConquer() {
        int[] A = new int[]{13, -3, -25, 20, -3,
                -16, -23, 18, 20, -7,
                12, -5, -22, 15, -4, 7};

        int[] expected = new int[]{7, 10, 43};
        int[] res = test.findMaximumSubarray(A, 0, A.length - 1);

        assertEquals(Arrays.toString(res), Arrays.toString(expected));
    }

    @Test
    public void testDP() {
        int[] A = new int[]{13, -3, -25, 20, -3,
                -16, -23, 18, 20, -7,
                12, -5, -22, 15, -4, 7};

        int[] expected = new int[]{7, 10, 43};
        int[] res = test.findMaximumSubarrayDP(A);

        assertEquals(Arrays.toString(res), Arrays.toString(expected));
    }

    @Test
    public void testRandom() {
        int len = (int)(Math.random() * 100);
        int[] A = new int[len];
        for (int i = 0; i < len; i++)
            A[i] = -100 + (int)(Math.random() * 200);

        int[] res1 = test.findMaximumSubarray(A, 0, len - 1);
        int[] res2 = test.findMaximumSubarrayDP(A);
        System.out.println("res1:");
        System.out.println("left: index-" + res1[0] + " value-" + A[res1[0]]);
        System.out.println("right: index-" + res1[1] + " value-" + A[res1[1]]);
        System.out.println("max sum-" + res1[2]);
        System.out.println();
        System.out.println("res2:");
        System.out.println("left: index-" + res2[0] + " value-" + A[res2[0]]);
        System.out.println("right: index-" + res2[1] + " value-" + A[res2[1]]);
        System.out.println("max sum-" + res2[2]);

        assertEquals(Arrays.toString(res1), Arrays.toString(res2));
    }
}
