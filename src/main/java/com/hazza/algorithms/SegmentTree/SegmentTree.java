package com.hazza.algorithms.SegmentTree;

/**
 * Created with IntelliJ IDEA.
 * Description: Show segment tree operations like construction, query and update.
 * And it can get the sum Of a range Of elements.
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-09-07
 * Time: 10:14 PM
 */
class SegmentTree
{
    int st[]; // The array that stores segment tree nodes

    /**
     * Constructor to construct segment tree from given array.
     * This constructor  allocates memory for segment tree and calls
     * constructSTUtil() to  fill the allocated memory.
     * @param arr the input array
     */
    SegmentTree(int arr[])
    {
        int n = arr.length;
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        st = new int[max_size]; // Memory allocation

        constructSTUtil(arr, 0, n - 1, 0);
    }

    /**
     * A utility function to get the middle index from corner indexes.
     * @param s start index
     * @param e end index
     * @return middle index
     */
    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    /**
     * A recursive function to get the sum of values in given range
     * of the array.  The following are parameters for this function.
     * @param ss starting indexes of the segment represented by current node, i.e., st[si]
     * @param se ending indexes of the segment represented by current node, i.e., st[si]
     * @param qs starting indexes of query range
     * @param qe ending indexes of query range
     * @param si index of current node in the segment tree. Initially 0 is passed as root is always at index 0
     * @return the sum of numbers from qs to qe
     */
    int getSumUtil(int ss, int se, int qs, int qe, int si)
    {
        // If segment of this node is a part of given range, then return the sum of the segment
        if (qs <= ss && qe >= se)
            return st[si];

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    /**
     * A recursive function to update the nodes which have the given
     * index in their range. The following are parameters.
     * @param ss starting indexes of the segment represented by current node, i.e., st[si]
     * @param se ending indexes of the segment represented by current node, i.e., st[si]
     * @param i index of the element to be updated. This index is in input array.
     * @param diff  value to be added to all nodes which have i in range
     * @param si index of current node in the segment tree. Initially 0 is passed as root is always at index 0
     */
    void updateValueUtil(int ss, int se, int i, int diff, int si)
    {
        // Base Case: If the input index lies outside the range of this segment
        if (i < ss || i > se)
            return;

        // If the input index is in range of this node, then update the value of the node and its children
        st[si] = st[si] + diff;
        if (se != ss) {
            int mid = getMid(ss, se);
            updateValueUtil(ss, mid, i, diff, 2 * si + 1);
            updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
        }
    }

    /**
     * The function to update a value in input array and segment tree.
     * It uses updateValueUtil() to update the value in segment tree
     * @param arr the input array
     * @param i the index to be updated
     * @param new_val the new value
     */
    void updateValue(int arr[], int i, int new_val)
    {
        int n = arr.length;
        // Check for erroneous input index
        if (i < 0 || i > n - 1) {
            System.out.println("Invalid Input");
            return;
        }

        // Get the difference between new value and old value
        int diff = new_val - arr[i];

        // Update the value in array
        arr[i] = new_val;

        // Update the values of nodes in segment tree
        updateValueUtil(0, n - 1, i, diff, 0);
    }

    /**
     * Return sum of elements in range from index qs (query start) to
     * qe (query end), it mainly uses getSumUtil().
     * @param n the length of the input array
     * @param qs starting indexes of query range
     * @param qe ending indexes of query range
     * @return sum of elements in range from index qs to qe
     */
    int getSum(int n, int qs, int qe)
    {
        // Check for erroneous input values
        if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return -1;
        }
        return getSumUtil(0, n - 1, qs, qe, 0);
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
    int constructSTUtil(int arr[], int ss, int se, int si)
    {
        // If there is one element in array, store it in current node of segment tree and return
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the sum of values in this node
        int mid = getMid(ss, se);
        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
                constructSTUtil(arr, mid + 1, se, si * 2 + 2);
        return st[si];
    }

}
