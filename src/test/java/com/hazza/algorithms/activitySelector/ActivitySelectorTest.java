package com.hazza.algorithms.activitySelector;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-08-11
 * Time: 9:10 PM
 */
public class ActivitySelectorTest {
    ActivitySelector test;
    private int[] s = {0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
    private int[] f = {0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
    private int[] randomS;
    private int[] randomF;

    private int[] generateRandomF(int len, int range) {
        int[] res = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) res[i] = random.nextInt(range);
        Arrays.sort(res);
        return res;
    }

    private int[] generateRandomS(int len, int range, int[] f) {
        int[] res = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            int s = random.nextInt(range);
            while (s >= f[i]) s = random.nextInt(range);
            res[i] = s;
        }
        return res;
    }

    private String list2str(List<Integer> l) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(l);
        for (Integer i: l) if (i != 0) sb.append(i);
        return sb.toString();
    }

    @Before
    public void setUp() {
        test = new ActivitySelector();
        randomF = generateRandomF(20, 24);
        randomS = generateRandomS(20, 24, randomF);
        randomS[0] = 0;
        randomF[0] = 0;
    }

    @Test
    public void testFixed() {
        List<Integer> right = Arrays.asList(1, 4, 8, 11);
        assertEquals(list2str(right), list2str(test.selectActivity(s, f)));
    }

    @Test
    public void testRandom() {
        for (int i = 1; i < randomS.length; ++i)
            System.out.print((String.valueOf(i).length() == 1 ?
                    " " + i : i) + " ");
        System.out.println();
        for (int i = 1; i < randomS.length; ++i)
            System.out.print((String.valueOf(randomS[i]).length() == 1 ?
                    " " + randomS[i] : randomS[i]) + " ");
        System.out.println();
        for (int i = 1; i < randomF.length; ++i)
            System.out.print((String.valueOf(randomF[i]).length() == 1 ?
                    " " + randomF[i] : randomF[i]) + " ");
        System.out.println();

        List<Integer> l1 = test.selectActivity(randomS, randomF);
        List<Integer> l2 = test.selectActivityGreedy(randomS, randomF);
        for (Integer i: l1) System.out.print(i + " ");
        assertEquals(list2str(l1), list2str(l2));
    }
}
