package com.hazza.algorithms.Prim;

import com.hazza.algorithms.ds.Edge;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-10
 * Time: 9:39 PM
 */
public class CreateMSTTest {

    @Test
    public void testExisted() {
        Edge[] edges = new Edge[3];
        edges[0] = new Edge(1, 2, 1);
        edges[1] = new Edge(1, 3, 2);
        edges[2] = new Edge(2, 3, 4);

        assertEquals(3, CreateMST.prim(3, 1, edges));
    }

    @Test (expected = RuntimeException.class)
    public void nonExisted() {
        Edge[] edges = new Edge[1];
        edges[0] = new Edge(2, 3, 2);

        CreateMST.prim(3, 1, edges);
    }
}
