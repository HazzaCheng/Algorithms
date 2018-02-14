package com.hazza.algorithms.DynamicProgramming;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-08-09
 * Time: 9:43 PM
 */
public class OptimalBinarySearchTreeTest {
    OptimalBinarySearchTree test;
    static final double[] p = new double[]{0, 0.15, 0.10, 0.05, 0.10, 0.20};
    static final double[] q = new double[]{0.05, 0.10, 0.05, 0.05, 0.05, 0.10};

    @Before
    public void setUp() {
        test = new OptimalBinarySearchTree();
    }

    @Test
    public void testGetOptimalSearchCost() {
        assertEquals(2.75, test.getOptimalBST(5, p, q));
    }
}
