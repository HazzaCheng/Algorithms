package com.hazza.algorithms.sparseTable;

import com.hazza.algorithms.segmentTree.SegmentTreeRMQ;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-09-11
 * Time: 12:49 PM
 */
public class STRMQTest {
    STRMQ test;

    private int[] creatRandomNums(int len) {
        int[] res = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) res[i] = random.nextInt(100) + 1;
        return res;
    }

    @Before
    public void setUp() {
        test = new STRMQ();
    }

    @Test
    public void testFixed() {
        int arr[] = {1, 3, 2, 7, 9, 11};
        STRMQ.preprocess(arr);
        assertEquals(1, test.query(arr, 0, arr.length - 1));
        assertEquals(2, test.query(arr, 1, 3));
        assertEquals(2, test.query(arr, 2, 5));
        assertEquals(7, test.query(arr, 3, 5));
    }

    @Test
    public void testRandom() {
        int[] arr = creatRandomNums(100);
        STRMQ.preprocess(arr);
        SegmentTreeRMQ treeRMQ = new SegmentTreeRMQ();
        treeRMQ.constructST(arr);

        System.out.println(treeRMQ.RMQ(arr.length, 1, 28) + " " + test.query(arr, 1, 28));
        assertEquals(treeRMQ.RMQ(arr.length, 1, 28), test.query(arr, 1, 28));

        System.out.println(treeRMQ.RMQ(arr.length, 10, 78) + " " + test.query(arr, 10, 78));
        assertEquals(treeRMQ.RMQ(arr.length, 10, 78), test.query(arr, 10, 78));

        System.out.println(treeRMQ.RMQ(arr.length, 3, 98) + " " + test.query(arr, 3, 98));
        assertEquals(treeRMQ.RMQ(arr.length, 3, 98), test.query(arr, 3, 98));

        System.out.println(treeRMQ.RMQ(arr.length, 33, 88) + " " + test.query(arr, 33, 88));
        assertEquals(treeRMQ.RMQ(arr.length, 33, 88), test.query(arr, 33, 88));
    }
}
