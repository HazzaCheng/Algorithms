package com.hazza.algorithms.activitySelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: In the activity-selection problem, we wish to select a maximum-size subset
 * of mutually compatible activities.
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-08-11
 * Time: 8:45 PM
 */
public class ActivitySelector {
    // We assume that the end time of activities are sorted,
    // other wise, sort it in O(nlgn), also the start time of activities
    // should be matched with the sorted finish time of activities.

    // soulution 1
    /**
     * Use a recursive method to solve it.
     *
     * @param s start of time
     * @param f end of time
     * @return a maximum-size subset of activities
     */
    List<Integer> selectActivity(int[] s, int[] f) {
        List<Integer> activities = new ArrayList<>();
        selectActivityRec(s, f, 0, f.length - 1, activities);

        return activities;
    }

    /**
     * Use greedy algorithms, recursively.
     *
     * @param s start of time
     * @param f end of time
     * @param k start index
     * @param n end index
     * @param activities a maximum-size subset of activities
     */
    void selectActivityRec(int[] s, int[] f, int k, int n, List<Integer> activities) {
        int m = k + 1;
        if (m > n) return;
        while (m <= n && s[m] < f[k]) ++m;
        if (m <= n) {
            activities.add(m);
            selectActivityRec(s, f, m, n, activities);
        }
    }

    // solution 2
    /**
     * Use greedy algorithms, iteratively.
     *
     * @param s start of time
     * @param f end of time
     * @return a maximum-size subset of activities
     */
    List<Integer> selectActivityGreedy(int[] s, int[] f) {
        int len = s.length;
        List<Integer> activities = new ArrayList<>();
        activities.add(1);
        for (int i = 2, k = 1; i < len; i++) {
            if (s[i] >= f[k]) {
                activities.add(i);
                k = i;
            }
        }

        return activities;
    }
}
