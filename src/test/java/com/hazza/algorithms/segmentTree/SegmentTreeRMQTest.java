package com.hazza.algorithms.segmentTree;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-09-10
 * Time: 4:52 PM
 */
public class SegmentTreeRMQTest {
    SegmentTreeRMQ test;

    @Before
    public void setUp() {
        test = new SegmentTreeRMQ();
    }

    @Test
    public void testTree() {
        int arr[] = {1, 3, 2, 7, 9, 11};
        int n = arr.length;
        test.constructST(arr);
        assertEquals(1, test.RMQ(n,0, arr.length - 1));
        assertEquals(2, test.RMQ(n,1, arr.length - 1));
        assertEquals(2, test.RMQ(n,2, 4));
    }
}
