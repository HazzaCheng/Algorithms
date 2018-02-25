package com.hazza.algorithms.Sort;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-23
 * Time: 10:49 AM
 */
public class MySortTest {


    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    private int[] getRandomNums(int n) {
        int[] nums = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++)
            nums[i] = random.nextInt(Integer.MAX_VALUE);

        return nums;
    }

    private boolean isSorted(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++)
            if (nums[i] < nums[i - 1]) return false;

        return true;
    }

    @Test
    public void testSort() {
        Map<String, Long> map = new HashMap<>();
        long time;
        int n = 100000;
        int[] nums = getRandomNums(n);
        int[] temp;

        System.out.println("Sort " + n + " numbers.");

        temp = nums.clone();
        time = System.currentTimeMillis();
        MySort.insertSort(temp);
        map.put("Insert sort", System.currentTimeMillis() - time);
        assertEquals(true, isSorted(temp));

        temp = nums.clone();
        time = System.currentTimeMillis();
        MySort.shellSort(temp);
        map.put("Shell sort", System.currentTimeMillis() - time);
        assertEquals(true, isSorted(temp));

        temp = nums.clone();
        time = System.currentTimeMillis();
        MySort.selectSort(temp);
        map.put("Select sort", System.currentTimeMillis() - time);
        assertEquals(true, isSorted(temp));

        temp = nums.clone();
        time = System.currentTimeMillis();
        MySort.bubbleSort(temp);
        map.put("Bubble sort", System.currentTimeMillis() - time);
        assertEquals(true, isSorted(temp));

        temp = nums.clone();
        time = System.currentTimeMillis();
        MySort.mergeSort(temp, 0, n - 1);
        map.put("Merge sort", System.currentTimeMillis() - time);
        assertEquals(true, isSorted(temp));

        temp = nums.clone();
        time = System.currentTimeMillis();
        MySort.quickSort(temp, 0, n - 1);
        map.put("Quick sort", System.currentTimeMillis() - time);
        assertEquals(true, isSorted(temp));

        temp = nums.clone();
        time = System.currentTimeMillis();
        MySort.heapSort(temp);
        map.put("Heap sort", System.currentTimeMillis() - time);
        assertEquals(true, isSorted(temp));

        Map<String, Long> res = sortByValue(map);

        for (Map.Entry<String, Long> entry: res.entrySet())
            System.out.println(entry.getKey() + " use " + entry.getValue() + " ms.");


    }
}
