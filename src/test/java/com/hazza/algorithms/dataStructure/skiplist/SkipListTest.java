package com.hazza.algorithms.dataStructure.skiplist;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;

/**
 * @description:
 * @user: HazzaCheng
 * @contact: hazzacheng@gmail.com
 * @date: 18-8-5
 * Change log:
 */
public class SkipListTest {
    private SkipList<Integer> skipList;

    @Test
    public void testIntegerNonDuplicates() {
        skipList = new SkipList<>(false);
        int n = 30, limit = 100;
        Integer[] nums = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(limit);
        }

        skipList.arrayToSkipList(nums);
        skipList.print();

        Arrays.sort(nums);

        System.out.println(nums[n/2]);
        assertTrue(skipList.isFound(nums[n / 2]));
        assertTrue(!skipList.isFound(limit + 10));

        // bigger
        int target = random.nextInt(100) + limit;
        skipList.insert(target);
        skipList.insert(target);
        skipList.insert(target);
        System.out.println("\nInsert: " + target);
        skipList.print();
        assertTrue(skipList.isFound(target));

        // smaller
        target = nums[0] - 1;
        skipList.insert(target);
        System.out.println("\nInsert: " + target);
        skipList.print();

        // middle
        target = nums[n / 2] - 1;
        skipList.insert(target);
        System.out.println("\nInsert: " + target);
        skipList.print();
        assertTrue(skipList.isFound(target));

        target = nums[n / 2];
        skipList.delete(target);
        System.out.println("\nDelete: " + target);
        skipList.print();
        assertTrue(!skipList.isFound(target));

    }

    @Test
    public void testIntegerDuplicates() {
        skipList = new SkipList<>(true);
        int n = 30, limit = 100;
        Integer[] nums = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(limit);
        }

        skipList.arrayToSkipList(nums);
        skipList.print();

        Arrays.sort(nums);

        System.out.println(nums[n/2]);
        assertTrue(skipList.isFound(nums[n / 2]));
        assertTrue(!skipList.isFound(limit + 10));

        // bigger
        int target = random.nextInt(100) + limit;
        skipList.insert(target);
        skipList.insert(target);
        skipList.insert(target);
        System.out.println("\nInsert: " + target);
        skipList.print();
        assertTrue(skipList.isFound(target));

        // smaller
        target = nums[0] - 1;
        skipList.insert(target);
        System.out.println("\nInsert: " + target);
        skipList.print();

        // middle
        target = nums[n / 2] - 1;
        skipList.insert(target);
        System.out.println("\nInsert: " + target);
        skipList.print();
        assertTrue(skipList.isFound(target));

        target = nums[0] - 1;
        skipList.delete(target);
        System.out.println("\nDelete: " + target);
        skipList.print();
        assertTrue(!skipList.isFound(target));

    }
}
