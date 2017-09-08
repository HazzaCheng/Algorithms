package com.hazza.algorithms.sumOfRange;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-09-08
 * Time: 9:42 PM
 */
public class SegmentTreeTest {
    SegmentTree test;

    @Test
    public void testTree() {
        int arr[] = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        test = new SegmentTree(arr, n);

        System.out.println("Sum of values in given range = " + test.getSum(n, 1, 3));
        assertEquals(15, test.getSum(n, 1, 3));

        test.updateValue(arr, 1, 10);
        System.out.println("Updated sum of values in given range = " + test.getSum(n, 1, 3));
        assertEquals(22, test.getSum(n, 1, 3));
    }
}
