package com.hazza.algorithms.graph.Johnson;

import com.hazza.algorithms.utils.ds.Edge;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-22
 * Time: 2:05 PM
 */
public class AllPairsShortestPathsTest {
    private Edge[] edges;

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

    }


    @Test
    public void testRight() {
        int[][] right = com.hazza.algorithms.graph.FloydWarshall.AllPairsShortestPaths.floydWarshall(5, edges);
        int[][] expeceted = AllPairsShortestPaths.johnson(5, edges);
        assertEquals(-4, expeceted[2][3]);
        assertEquals(4, expeceted[3][2]);

        for (int i = 1; i < expeceted.length; i++)
            for (int j = 1; j < expeceted.length; j++) {
                if (expeceted[i][j] != right[i][j])
                System.out.println(i + " --> " + j + " " + expeceted[i][j] + " " + right[i][j]);
                assertEquals(expeceted[i][j], right[i][j]);
            }
    }


}
