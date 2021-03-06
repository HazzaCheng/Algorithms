package com.hazza.algorithms.graph.SPFA;

import com.hazza.algorithms.utils.ds.Edge;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-14
 * Time: 9:33 PM
 */
public class FindShortestPathTest {

    @Test
    public void testExisted() {
        Edge[] edges = new Edge[6];
        edges[0] = new Edge(1, 2, 3);
        edges[1] = new Edge(1, 5, -2);
        edges[2] = new Edge(2, 3, 2);
        edges[3] = new Edge(2, 4, 5);
        edges[4] = new Edge(4, 3, 1);
        edges[5] = new Edge(4, 5, 4);

        assertEquals(true, FindShortestPath.spfa(5, 1, edges));
        assertEquals(0, FindShortestPath.getDistanceFromSource(1));
        assertEquals(3, FindShortestPath.getDistanceFromSource(2));
        assertEquals(5, FindShortestPath.getDistanceFromSource(3));
        assertEquals(8, FindShortestPath.getDistanceFromSource(4));
        assertEquals(-2, FindShortestPath.getDistanceFromSource(5));

        for (int i = 1; i <= 5; i++) FindShortestPath.printPath(1, i);
    }

    @Test
    public void testNonExisted() {
        Edge[] edges = new Edge[6];
        edges[0] = new Edge(1, 2, 3);
        edges[1] = new Edge(1, 5, -2);
        edges[2] = new Edge(2, 3, -2);
        edges[3] = new Edge(3, 2, 1);
        edges[4] = new Edge(4, 3, 1);
        edges[5] = new Edge(4, 5, 4);

        assertEquals(false, FindShortestPath.spfa(5, 1, edges));
    }

}
