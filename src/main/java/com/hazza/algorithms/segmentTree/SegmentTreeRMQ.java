package com.hazza.algorithms.segmentTree;

/**
 * Created with IntelliJ IDEA.
 * Description: Program for range minimum query solved by segment tree.
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-09-10
 * Time: 4:20 PM
 */

public class SegmentTreeRMQ
{
    private int st[]; //array to store segment tree

    /**
     * A utility function to get minimum of two numbers
     * @param x an integer
     * @param y an integer
     * @return the smaller integer
     */
    private int minVal(int x, int y) {
        return (x < y) ? x : y;
    }

    /**
     * A utility function to get the middle index from corner indexes
     * @param s the start index
     * @param e the ending index
     * @return the middle index
     */
    private int getMid(int s, int e) {
        return (s + e) / 2;
    }

    /**
     * A recursive function to get the minimum value in a given
     * range of array indexes. The following are parameters for
     * this function.
     * @param ss starting indexes of the segment represented by current node, i.e., st[si]
     * @param se ending indexes of the segment represented by current node, i.e., st[si]
     * @param qs starting indexes of query range
     * @param qe ending indexes of query range
     * @param index index of current node in the segment tree
     * @return the minimum value
     */
    private int RMQUtil(int ss, int se, int qs, int qe, int index)
    {
        // If segment of this node is a part of given range, then
        // return the min of the segment
        if (qs <= ss && qe >= se)
            return st[index];

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return Integer.MAX_VALUE;

        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return minVal(RMQUtil(ss, mid, qs, qe, 2 * index + 1),
                RMQUtil(mid + 1, se, qs, qe, 2 * index + 2));
    }

    /**
     * Return minimum of elements in range from index qs (query start)
     * to qe (query end).  It mainly uses RMQUtil().
     * @param n the length of the input array
     * @param qs starting indexes of query range
     * @param qe ending indexes of query range
     * @return the minimum value
     */
    public int RMQ(int n, int qs, int qe)
    {
        // Check for erroneous input values
        if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return -1;
        }

        return RMQUtil(0, n - 1, qs, qe, 0);
    }

    /**
     * A recursive function that constructs Segment Tree for array[ss..se].
     * si is index of current node in segment tree st.
     * @param arr the input array
     * @param ss starting indexes of the segment represented by current node, i.e., st[si]
     * @param se ending indexes of the segment represented by current node, i.e., st[si]
     * @param si index of current node in the segment tree. Initially 0 is passed as root is always at index 0
     * @return
     */
    private int constructSTUtil(int arr[], int ss, int se, int si)
    {
        // If there is one element in array, store it in current node of segment tree and return
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the minimum of two values in this node
        int mid = getMid(ss, se);
        st[si] = minVal(constructSTUtil(arr, ss, mid, si * 2 + 1),
                constructSTUtil(arr, mid + 1, se, si * 2 + 2));
        return st[si];
    }

    /**
     * Function to construct segment tree from given array. This function
     * allocates memory for segment tree and calls constructSTUtil() to
     * fill the allocated memory
     * @param arr the input array
     */
    public void constructST(int arr[])
    {
        int n = arr.length;
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        st = new int[max_size]; // allocate memory

        // Fill the allocated memory st
        constructSTUtil(arr, 0, n - 1, 0);
    }

}
