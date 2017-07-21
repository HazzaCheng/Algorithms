package com.hazza.algorithms.rodCutting;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by hazza on 7/21/17.
 */
public class RodCuttingTest {
    private RodCutting test;
    private int[] p;

    private int[] generateNums(int len) {
        int[] p10 = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int[] p = new int[len + 1];
        Random random = new Random();
        int last = 30;
        for (int i = 0; i < 11; i++) p[i] = p10[i];
        for (int i = 11; i <= len; i++) {
            p[i] = random.nextInt(10) + last;
            last = p[i];
        }
        return p;
    }

    @Before
    public void setUp() {
        test = new RodCutting();
        p = generateNums(100000);
    }

    @Test
    public void testShortDirect() {
        int profit = test.cutRod(p, 5);
        assertEquals(13, profit);
    }

    @Ignore
    @Test(timeout = 5000)
    public void testLongDirect() {
        int profit = test.cutRod(p, 40);
        System.out.println(profit);
    }

    @Test
    public void testShortMemory() {
        int profit = test.memorizedCutRod(p, 5);
        assertEquals(13, profit);
    }

    @Test(timeout = 5000)
    public void testLongMemory() {
        int profit = test.memorizedCutRod(p, 4000);
        System.out.println(profit);
    }

    @Test(expected = StackOverflowError.class)
    public void testOverFlow() {
        int profit = test.memorizedCutRod(p, 40000);
        System.out.println(profit);
    }

    @Test
    public void testAccuracy() {
        int len = 4000;
        int p1 = test.bottem2upCutRod(p, 4000);
        int p2 = test.memorizedCutRod(p, 4000);
        assertEquals(p1, p2);
    }

    @Test
    public void testBottom2Up() {
        int profit = test.bottem2upCutRod(p, 40000);
        System.out.println(profit);
    }

    @Test
    public void testBottom2UpPrint() {
        int len = 7;
        int p1 = test.bottem2upCutRod(p, len);
        int p2 = test.printCutRod(p, len);
        assertEquals(p1, p2);
    }
}
