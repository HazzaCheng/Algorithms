package com.hazza.algorithms.dp;

/**
 * Created by hazza on 7/21/17.
 * The problem of Rod Cutting solved by dynamic programming
 */
public class RodCutting {

    // solution 1
    /**
     * A direct top-down recursive method.
     *
     * @author HazzaCheng
     * @param p the array of price
     * @param len the length of rod
     * @return the max of profit in all cutting schemes
     */
    int cutRod(int[] p, int len) {
        if (len <= 0) return 0;
        else {
            int profit = 0;
            for (int i = 1; i <= len; i++)
                profit = Math.max(profit, p[i] + cutRod(p, len - i));
            return profit;
        }
    }

    // solution 2
    /**
     * A direct top-down using a recursive method with a memorized array.
     *
     * @author HazzaCheng
     * @param p the array of price
     * @param len the length of rod
     * @return the max of profit in all cutting schemes
     */
    int memorizedCutRod(int[] p, int len) {
        int[] r = new int[len + 1];
        for (int i = 0; i <= len; i++) r[i] = 0;

        return memorizedCutRodAux(p, len, r);
    }

    /**
     * Calculate the max of profit when the length of rod in different values.
     *
     * @author HazzaCheng
     * @param p the array of price
     * @param len the length of rod
     * @param r the memorized array which store the optimal value temporarily
     * @return the max of profit when the length of rod is len
     */
    int memorizedCutRodAux(int[] p, int len, int[] r) {
        if (len == 0) return 0;
        if (r[len] > 0) return r[len];
        else {
            int profit = 0;
            for (int i = 1; i <= len; i++)
                profit = Math.max(profit, p[i] + memorizedCutRodAux(p, len - i, r));
            r[len] = profit;
            return profit;
        }
    }

    // solution 3
    /**
     * A direct down-top method with a memoized array, not a recursive, using DP.
     *
     * @author HazzaCheng
     * @param p the array of price
     * @param len the length of rod
     * @return the max of profit in all cutting schemes
     */
    int bottem2upCutRod(int[] p, int len) {
        int[] r = new int[len + 1];
        r[0] = 0;
        for (int j = 1; j <= len; j++) {
            int profit = 0;
            for (int i = 1; i <= j; i++)
                profit = Math.max(profit, p[i] + r[j - i]);
            r[j] = profit;
        }

        return r[len];
    }

    // solution 4
    /**
     * A direct down-top method with a memoized array, not a recursive, using DP,
     * also record the first cutted rod.
     *
     * @author HazzaCheng
     * @param p the array of price
     * @param len the length of rod
     * @param s the array of the first cutted rod
     * @return the max of profit in all cutting schemes
     */
    int bottem2upCutRod(int[] p, int len, int[] s) {
        int[] r = new int[len + 1];
        r[0] = 0;
        for (int j = 1; j <= len; j++) {
            int profit = 0;
            for (int i = 1; i <= j; i++) {
                if (profit < p[i] + r[j - i]) {
                    profit = Math.max(profit, p[i] + r[j - i]);
                    s[j] = i;
                }
            }
            r[j] = profit;
        }

        return r[len];
    }

    /**
     * Print the the optimal scheme of cutting rod.
     *
     * @author HazzaCheng
     * @param p the array of price
     * @param len the length of rod
     * @return the max of profit in all cutting schemes
     */
    int printCutRod(int[] p, int len) {
        int[] s = new int[len + 1];
        int profit = bottem2upCutRod(p, len, s);
        while (len > 0) {
            System.out.println(s[len]);
            len -= s[len];
        }
        return profit;
    }
}
