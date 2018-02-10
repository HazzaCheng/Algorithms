package com.hazza.algorithms.dynamicProgramming;

/**
 * Created with IntelliJ IDEA.
 * Description: Solve Maximum Problem by divide-and-conquer and DP
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 17-11-28
 * Time: 8:17 pm
 */
public class MaximumSubarray {
    // solution 1

    /**
     * Find the maximum sub array.
     * @param A The input array.
     * @param low The start index of the sub array.
     * @param high The end index of the sub array.
     * @return An int array includes the start index of the maximum sub array,
     *         the end index of the maximum sub array and the sum of maximum sub array.
     */
    int[] findMaximumSubarray(int[] A, int low, int high) {
        if (low == high) return new int[]{low, high, A[low]};

        int mid = (low + high) / 2;
        int[] left = findMaximumSubarray(A, low, mid);
        int[] right = findMaximumSubarray(A, mid + 1, high);
        int[] cross = findMaxCrossingSubarray(A, low, mid, high);

        if (left[2] >= right[2] && left[2] >= cross[2]) return left;
        else if (right[2] >= left[2] && right[2] >= cross[2]) return right;
        else return cross;
    }

    /**
     * Find the maximum crossing sub array.
     * @param A The input array.
     * @param low The start index of the sub array.
     * @param mid The mid index of the sub array.
     * @param high The end index of the sub array.
     * @return An int array includes the start index of the maximum sub array,
     *         the end index of the maximum sub array and the sum of maximum sub array.
     */
    int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE, maxLeft = mid;
        for (int i = mid, sum = 0; i >= low ; --i) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = Integer.MIN_VALUE, maxRight = mid + 1;
        for (int i = mid + 1, sum = 0; i <= high; ++i) {
            sum += A[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new int[]{maxLeft, maxRight, leftSum + rightSum};
    }

    // solution 2

    /**
     * Find the maximum sub array from the input array A.
     * @param A the input array
     * @return An int array includes the start index of the maximum sub array,
     *         the end index of the maximum sub array and the sum of maximum sub array.
     */
    int[] findMaximumSubarrayDP(int[] A) {
        int len = A.length;
        int cur_left = 0, maxLeft = 0, maxRight = 0, temp = A[0], sumMax = A[0];

        for (int i = 1; i < len; ++i) {
            temp += A[i];
            if (temp < 0) {
                temp = 0;
                cur_left = i + 1;
            }
            if (temp > sumMax) {
                sumMax = temp;
                maxLeft = cur_left;
                maxRight = i;
            }
        }

        return new int[]{maxLeft, maxRight, sumMax};
    }
}
