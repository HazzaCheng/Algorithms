package com.hazza.algorithms.rodCutting;

/**
 * Created by hazza on 7/21/17.
 * The problem of Rod Cutting using dynamic programming
 */
public class RodCutting {
    //a direct top-down recursive method
    int cutRod(int[] p, int len) {
        if (len <= 0) return 0;
        else {
            int profit = 0;
            for (int i = 1; i <= len; i++)
                profit = Math.max(profit, p[i] + cutRod(p, len - i));
            return profit;
        }
    }

    //a direct top-down recursive method with a memorized array
    int memorizedCutRod(int[] p, int len) {
        int[] r = new int[len + 1];
        for (int i = 0; i <= len; i++) r[i] = 0;

        return memorizedCutRodAux(p, len, r);
    }

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

    //a direct down-top method with a memoized array, not a recursive
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

    //same as above, but recode the first cutted rod
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
