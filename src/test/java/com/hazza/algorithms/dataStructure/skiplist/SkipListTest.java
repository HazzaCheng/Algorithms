package com.hazza.algorithms.dataStructure.skiplist;

import com.hazza.algorithms.utils.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * @description:
 * @user: HazzaCheng
 * @contact: hazzacheng@gmail.com
 * @date: 18-8-5
 * Change log:
 */
public class SkipListTest {
    private SkipList<Integer> skipList1;
    private SkipList<String> skipList2;

    @Test
    public void testIntegerNonDuplicates() {
        skipList1 = new SkipList<>(false);
        int n = 30, limit = 100;
        Integer[] nums = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(limit);
        }

        skipList1.arrayToSkipList(nums);
        skipList1.print();

        Arrays.sort(nums);

        System.out.println(nums[n/2]);
        assertTrue(skipList1.isFound(nums[n / 2]));
        assertTrue(!skipList1.isFound(limit + 10));

        // bigger
        int target = random.nextInt(100) + limit;
        skipList1.insert(target);
        skipList1.insert(target);
        skipList1.insert(target);
        System.out.println("\nInsert: " + target);
        skipList1.print();
        assertTrue(skipList1.isFound(target));

        // smaller
        target = nums[0] - 1;
        skipList1.insert(target);
        System.out.println("\nInsert: " + target);
        skipList1.print();

        // middle
        target = nums[n / 2] - 1;
        skipList1.insert(target);
        System.out.println("\nInsert: " + target);
        skipList1.print();
        assertTrue(skipList1.isFound(target));

        target = nums[n / 2];
        skipList1.delete(target);
        System.out.println("\nDelete: " + target);
        skipList1.print();
        assertTrue(!skipList1.isFound(target));

        assertEquals(nums[n / 3], skipList1.get(nums[n / 3]));
    }

    @Test
    public void testIntegerDuplicates() {
        skipList1 = new SkipList<>(true);
        int n = 30, limit = 100;
        Integer[] nums = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(limit);
        }

        skipList1.arrayToSkipList(nums);
        skipList1.print();

        Arrays.sort(nums);

        System.out.println(nums[n/2]);
        assertTrue(skipList1.isFound(nums[n / 2]));
        assertTrue(!skipList1.isFound(limit + 10));

        // bigger
        int target = random.nextInt(100) + limit;
        skipList1.insert(target);
        skipList1.insert(target);
        skipList1.insert(target);
        System.out.println("\nInsert: " + target);
        skipList1.print();
        assertTrue(skipList1.isFound(target));

        // smaller
        target = nums[0] - 1;
        skipList1.insert(target);
        System.out.println("\nInsert: " + target);
        skipList1.print();

        // middle
        target = nums[n / 2] - 1;
        skipList1.insert(target);
        System.out.println("\nInsert: " + target);
        skipList1.print();
        assertTrue(skipList1.isFound(target));

        target = nums[0] - 1;
        skipList1.delete(target);
        System.out.println("\nDelete: " + target);
        skipList1.print();
        assertTrue(!skipList1.isFound(target));

        assertEquals(nums[n / 3], skipList1.get(nums[n / 3]));

    }
    
    @Test
    public void testString() {
        skipList2 = new SkipList<>(true);
        int n = 30, len = 3;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = StringUtils.getRandomString(3);
        }

        skipList2.arrayToSkipList(strs);
        skipList2.print();

        String target = strs[n / 2];
        skipList2.insert(target);
        skipList2.insert(target);
        System.out.println("\nInsert: " + target);
        skipList2.print();
        assertTrue(skipList2.isFound(target));

        target = strs[n / 2 + 1];
        skipList2.delete(target);
        System.out.println("\nDelete: " + target);
        skipList2.print();
        assertTrue(!skipList2.isFound(target));

        assertEquals(strs[n / 3], skipList2.get(strs[n / 3]));
    }
}
