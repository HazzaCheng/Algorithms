package com.hazza.longestCommonSubsequence;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by hazza on 8/4/17.
 */
public class LongestCommonSubsequenceTest {
    LongestCommonSubsequence test;

    @Before
    public void setUp() {
        test = new LongestCommonSubsequence();
    }

    @Test
    public void testFixed() {
        String x = "BDCABA";
        String y = "ABCBDAB";

        assertEquals(4, test.getLCSlen(x.toCharArray(), y.toCharArray()));
    }
}
