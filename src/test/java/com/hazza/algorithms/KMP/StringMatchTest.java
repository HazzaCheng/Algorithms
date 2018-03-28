package com.hazza.algorithms.KMP;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author Hazzacheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-3-28
 * Time: 9:15 PM
 */
public class StringMatchTest {

    @Test
    public void test() {
        String s = "abacaabacabacabaabb";
        String t1 = "abacab";
        String t2 = "abadcab";

        assertEquals(true, StringMatch.kmp(s, t1));
        assertEquals(false, StringMatch.kmp(s, t2));
    }
}
