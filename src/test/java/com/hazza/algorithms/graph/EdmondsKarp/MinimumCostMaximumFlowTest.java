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
    private int[][] edges1, edges2, edges3;

    @Before
    public void setUp() {
        edges1 = new int[9][4];
        edges1[0] = new int[]{0, 1, 16, 0};
        edges1[1] = new int[]{0, 2, 13, 0};
        edges1[2] = new int[]{2, 1, 4, 0};
        edges1[3] = new int[]{1, 3, 12, 0};
        edges1[4] = new int[]{3, 2, 9, 0};
        edges1[5] = new int[]{2, 4, 14, 0};
        edges1[6] = new int[]{4, 3, 7, 0};
        edges1[7] = new int[]{3, 5, 20, 0};
        edges1[8] = new int[]{4, 5, 4, 0};

        edges2 = new int[9][4];
        edges2[0] = new int[]{0, 1, 16, 2};
        edges2[1] = new int[]{0, 2, 13, 3};
        edges2[2] = new int[]{2, 1, 4, 4};
        edges2[3] = new int[]{1, 3, 12, 5};
        edges2[4] = new int[]{3, 2, 9, 2};
        edges2[5] = new int[]{2, 4, 14, 5};
        edges2[6] = new int[]{4, 3, 7, 8};
        edges2[7] = new int[]{3, 5, 20, 2};
        edges2[8] = new int[]{4, 5, 4, 1};

        edges3 = new int[7][4];
        edges3[0] = new int[]{0, 1, 10, 4};
        edges3[1] = new int[]{0, 2, 8, 1};
        edges3[2] = new int[]{1, 3, 2, 6};
        edges3[3] = new int[]{1, 4, 7, 1};
        edges3[4] = new int[]{2, 1, 5, 2};
        edges3[5] = new int[]{2, 3, 10, 3};
        edges3[6] = new int[]{3, 4, 4, 2};
    }

    @Test
    public void testZeroWeight() {
        int expectedFlow = 23;
        int expectedCost = 0;
        int[] ans = MinimumCostMaximumFlow.minCostMaxFlow(6, 9, edges1, 0, 5);


        assertEquals(expectedFlow, ans[0]);
        assertEquals(expectedCost, ans[1]);
    }

    @Test
    public void test1() {
        int expectedFlow = 23;
        int expectedCost = 270;
        int[] ans = MinimumCostMaximumFlow.minCostMaxFlow(6, 9, edges2, 0, 5);


        assertEquals(expectedFlow, ans[0]);
        assertEquals(expectedCost, ans[1]);
    }

    @Test
    public void test2() {
        int expectedFlow = 11;
        int expectedCost = 55;
        int[] ans = MinimumCostMaximumFlow.minCostMaxFlow(5, 7, edges3, 0, 4);


        assertEquals(expectedFlow, ans[0]);
        assertEquals(expectedCost, ans[1]);
    }

    
   
}
