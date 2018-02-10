package com.hazza.algorithms.fastMatrixExponentiation;

import cucumber.api.java.it.Ma;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-9
 * Time: 下午7:48
 */
public class SetDicesTest {
    SetDices test;
    boolean[][] mutex;

    @Before
    public void setUp() {
        test = new SetDices();
        mutex = new boolean[7][7];
        mutex[1][2] = mutex[2][1] = true;
    }

    @Test
    public void testFixed() {
        int n = 3;
        assertEquals(test.countOrders_dp(n, mutex), test.countOrders_matrix(n, mutex));
        n = 30;
        assertEquals(test.countOrders_dp(n, mutex), test.countOrders_matrix(n, mutex));
        n = 300;
        assertEquals(test.countOrders_dp(n, mutex), test.countOrders_matrix(n, mutex));
        n = 3000;
        assertEquals(test.countOrders_dp(n, mutex), test.countOrders_matrix(n, mutex));
        n = 30000;
        assertEquals(test.countOrders_dp(n, mutex), test.countOrders_matrix(n, mutex));
    }

    @Test
    public void testRandom() {
        Random random = new Random();
        long time;
        for (int i = 0; i < 10; i++) {
            int n = random.nextInt((int) Math.pow(10, 9));
            time = System.currentTimeMillis();
            long res1 = test.countOrders_dp(n, mutex);
            long time1 = System.currentTimeMillis() - time;
            time = System.currentTimeMillis();
            long res2 = test.countOrders_dp(n, mutex);
            long time2 = System.currentTimeMillis() - time;
            assertEquals(res1, res2);
            System.out.println("n: " + n + "  dp: " + time1 +"ms  matrix: " + time2 + "ms");
        }
    }

    @Test
    public void testLargest() {
        long time;

        int n = (int) Math.pow(10, 9 );
        time = System.currentTimeMillis();
        long res1 = test.countOrders_dp(n, mutex);
        long time1 = System.currentTimeMillis() - time;
        time = System.currentTimeMillis();
        long res2 = test.countOrders_dp(n, mutex);
        long time2 = System.currentTimeMillis() - time;
        assertEquals(res1, res2);
        System.out.println("n: " + n + "  dp: " + time1 +"ms  matrix: " + time2 + "ms");

    }
}
