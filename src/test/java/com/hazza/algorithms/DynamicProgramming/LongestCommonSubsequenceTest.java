package com.hazza.algorithms.DynamicProgramming;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by hazza on 8/4/17.
 */
public class LongestCommonSubsequenceTest {
    LongestCommonSubsequence test;
    String randomStr1;
    String randomStr2;

    private String createRandomString(int len) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char)(random.nextInt(26) + 65));
        }
        return sb.toString();
    }

    @Before
    public void setUp() {
        test = new LongestCommonSubsequence();
        randomStr1 = createRandomString(new Random().nextInt(200));
        randomStr2 = createRandomString(new Random().nextInt(200));
    }


    @Test
    public void testFixed() {
        String x = "ABCBDAB";
        String y = "BDCABA";

        assertEquals(4, test.getLCSlen(x.toCharArray(), y.toCharArray()));
    }

    @Test
    public void testRandom() {
        System.out.println(randomStr1);
        System.out.println(randomStr2);
        System.out.println(test.getLCSlen(randomStr1.toCharArray(), randomStr2.toCharArray()));
    }
}
