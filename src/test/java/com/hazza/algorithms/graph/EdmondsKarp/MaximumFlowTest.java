package com.hazza.algorithms.graph.EdmondsKarp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-6-10
 * Time: 8:18 PM
 */
public class MaximumFlowTest {
    private int[][] edges;

    @Before
    public void setUp() {
        edges = new int[9][3];
        edges[0] = new int[]{0, 1, 16};
        edges[1] = new int[]{0, 2, 13};
        edges[2] = new int[]{2, 1, 4};
        edges[3] = new int[]{1, 3, 12};
        edges[4] = new int[]{3, 2, 9};
        edges[5] = new int[]{2, 4, 14};
        edges[6] = new int[]{4, 3, 7};
        edges[7] = new int[]{3, 5, 20};
        edges[8] = new int[]{4, 5, 4};
    }

    @Test
    public void test() {
        int expected = 23;
        int ans = MaximumFlow.edmondsKarp(6, 9, edges, 0, 5);

        assertEquals(expected, ans);
    }
}
