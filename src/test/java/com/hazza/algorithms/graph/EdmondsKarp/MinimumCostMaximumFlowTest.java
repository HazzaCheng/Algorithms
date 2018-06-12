package com.hazza.algorithms.graph.EdmondsKarp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-6-12
 * Time: 7:42 PM
 */
public class MinimumCostMaximumFlowTest {
    private int[][] edges;

    @Before
    public void setUp() {
        edges = new int[7][4];
        edges[0] = new int[]{0, 1, 10, 4};
        edges[1] = new int[]{0, 2, 8, 1};
        edges[2] = new int[]{1, 3, 2, 6};
        edges[3] = new int[]{1, 4, 7, 1};
        edges[4] = new int[]{2, 1, 5, 2};
        edges[5] = new int[]{2, 3, 10, 3};
        edges[6] = new int[]{3, 4, 4, 2};
    }

    @Test
    public void test() {
        int expectedFlow = 11;
        int expectedCost = 55;
        int[] ans = MinimumCostMaximumFlow.minCostMaxFlow(5, 7, edges, 0, 4);


        assertEquals(expectedFlow, ans[0]);
        assertEquals(expectedCost, ans[1]);
    }
}
