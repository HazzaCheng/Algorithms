package com.hazza.algorithms.matrix.FastMatrixExponentiation;

import com.hazza.algorithms.utils.ds.Edge;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-17
 * Time: 2:05 PM
 */
public class AllPairsShortestPathsTest {

    @Test
    public void test() {
        Edge[] edges = new Edge[9];
        edges[0] = new Edge(1, 2, 3);
        edges[1] = new Edge(1, 3, 8);
        edges[2] = new Edge(1, 5, -4);
        edges[3] = new Edge(2, 4, 1);
        edges[4] = new Edge(2, 5, 7);
        edges[5] = new Edge(3, 2, 4);
        edges[6] = new Edge(4, 1, 2);
        edges[7] = new Edge(5, 4, 6);
        edges[8] = new Edge(4, 3, -5);

        int[][] expeceted1 = AllPairsShortestPaths.showAllPairsShortestPaths(5, edges);
        int[][] expeceted2 = AllPairsShortestPaths.showAllPairsShortestPaths_fast(5, edges);
        assertEquals(-4, expeceted1[2][3]);
        assertEquals(4, expeceted1[3][2]);
        assertEquals(-4, expeceted2[2][3]);
        assertEquals(4, expeceted2[3][2]);

        for (int i = 1; i < expeceted1.length; i++)
            for (int j = 1; j < expeceted1.length; j++)
                assertEquals(expeceted1[i][j], expeceted2[i][j]);

    }
}
