package com.hazza.algorithms.Sort;

/**
 * Created with IntelliJ IDEA.
 * Description: Different sort methods.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-23
 * Time: 10:48
 */
public class MySort {

    /**
     * Swap two numbers in array.
     * @param nums The input array.
     * @param i The index.
     * @param j The index.
     */
    private static void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[j] ^ nums[i];
            nums[i] = nums[i] ^ nums[j];
        }
    }


    /**
     * Direct insert sort.
     * @param nums The input array.
     */
    public static void insertSort(int[] nums) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int temp = nums[i], j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] > temp) nums[j + 1] = nums[j];
                else break;
            }
            nums[j + 1] = temp;
        }
    }

    /**
     * Shell sort.
     * @param nums The input array.
     */
    public static void shellSort(int[] nums) {
        int n = nums.length;
        int gap = n;

        do {
            gap = gap / 3 + 1;
            for (int i = gap; i < n; i++) {
                if (nums[i] < nums[i - gap]) {
                    int temp = nums[i], j = i - gap;
                    do {
                        nums[j + gap] = nums[j];
                        j -= gap;
                    } while (j >= 0 && temp < nums[j]);
                    nums[j + gap] = temp;
                }
            }
        } while (gap > 1);
    }

    /**
     * Direct select sort.
     * @param nums The input array.
     */
    public static void selectSort(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) swap(nums, i, j);
            }
        }
    }

    /**
     * Bubble sort.
     * @param nums The input array.
     */
    public static void bubbleSort(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) swap(nums, j, j+1);
            }
        }
    }

    /**
     * Merge two sorted groups.
     * @param nums The input array.
     * @param mid The middle index.
     * @param left The left index.
     * @param right The right index.
     */
    private static void merge(int[] nums, int mid, int left, int right) {
        int[] temp = new int[right - left + 1];
        int l = 0, r = right - left;

        for (int i = left; i <= mid; i++) temp[i - left] = nums[i];
        for (int i = right; i > mid; i--) temp[i - left] = nums[mid + 1 + right - i];
        for (int i = left; i <= right; i++) {
            if (temp[l] < temp[r]) nums[i] = temp[l++];
            else nums[i] = temp[r--];
        }
    }

    /**
     * Merge sort.
     * @param nums The input array.
     * @param left The left index.
     * @param right The right index.
     */
    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int mid = (right - left) / 2 + left;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, mid, left, right);
    }

    /**
     * Divide the array into two groups depending on the nums[left].
     * @param nums The input array.
     * @param left The left index.
     * @param right The right index.
     * @return The split index.
     */
    public static int quickPartition(int[] nums, int left, int right) {
        int spiltIndex = left;

        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < nums[left]) {
                ++spiltIndex;
                if (spiltIndex != i) swap(nums, spiltIndex, i);
            }
        }
        swap(nums, left, spiltIndex);

        return spiltIndex;
    }

    /**
     * Quick sort.
     * @param nums The input array.
     * @param left The left index.
     * @param right The right index.
     */
    public static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int spiltIndex = quickPartition(nums, left, right);
            quickSort(nums, left, spiltIndex - 1);
            quickSort(nums, spiltIndex + 1, right);
        }
    }

}
