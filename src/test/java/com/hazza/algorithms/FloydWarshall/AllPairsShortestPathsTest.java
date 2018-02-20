package com.hazza.algorithms.FloydWarshall;

import com.hazza.algorithms.ds.Edge;
import org.junit.Before;
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
    private Edge[] edges, edges1;

    @Before
    public void setUp() {
        edges = new Edge[9];
        edges[0] = new Edge(1, 2, 3);
        edges[1] = new Edge(1, 3, 8);
        edges[2] = new Edge(1, 5, -4);
        edges[3] = new Edge(2, 4, 1);
        edges[4] = new Edge(2, 5, 7);
        edges[5] = new Edge(3, 2, 4);
        edges[6] = new Edge(4, 1, 2);
        edges[7] = new Edge(5, 4, 6);
        edges[8] = new Edge(4, 3, -5);

        edges1 = new Edge[5];
        edges1[0] = new Edge(2, 3, 1);
        edges1[1] = new Edge(2, 4, 1);
        edges1[2] = new Edge(3, 2, 1);
        edges1[3] = new Edge(4, 1, 1);
        edges1[4] = new Edge(4, 3, 1);
    }


    @Test
    public void testRight() {
        int[][] expeceted1 = AllPairsShortestPaths.getAllPairsShortestPaths(5, edges);
        int[][] expeceted2 = com.hazza.algorithms.FastMatrixExponentiation.AllPairsShortestPaths.showAllPairsShortestPaths_fast(5, edges);
        assertEquals(-4, expeceted1[2][3]);
        assertEquals(4, expeceted1[3][2]);

        for (int i = 1; i < expeceted1.length; i++)
            for (int j = 1; j < expeceted1.length; j++)
                assertEquals(expeceted1[i][j], expeceted2[i][j]);
    }

    @Test
    public void testPrintPath() {
        int[][] expeceted = AllPairsShortestPaths.getAllPairsShortestPaths(5, edges);
        int[][] paths = AllPairsShortestPaths.getExactPaths();
        assertEquals(4, paths[2][1]);
        assertEquals(2, paths[3][4]);
        for (int i = 1; i <= 5; i++)
            for (int j = 1; j <= 5; j++)
                AllPairsShortestPaths.printPath(i, j);
    }

    @Test
    public void testTransitiveClosure() {
        int m = 4;
        boolean[][] connected = AllPairsShortestPaths.transitiveClosure(m, edges1);
        int[][] expected = AllPairsShortestPaths.getAllPairsShortestPaths(m, edges1);

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= m; j++)
                assertEquals(expected[i][j] < m, connected[i][j]);

    }
}
