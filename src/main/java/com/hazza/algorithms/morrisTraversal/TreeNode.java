package com.hazza.algorithms.morrisTraversal;

import java.sql.Array;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * Description: The structure of tree node.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 17-11-12
 * Time: 10:44 AM
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    static TreeNode genTreeFromPreoderInorder(int[] pre, int[] in, int len) {
        if (len <= 0) return null;
        int leftLen = 0;
        TreeNode node = new TreeNode(pre[0]);
        for (int i = 0; i < len; i++) {
            if (in[i] == pre[0]) {
                leftLen = i;
                break;
            }
        }
        node.left = genTreeFromPreoderInorder(Arrays.copyOfRange(pre, 1, pre.length),
                in, leftLen);
        node.right = genTreeFromPreoderInorder(Arrays.copyOfRange(pre, leftLen + 1, pre.length),
                Arrays.copyOfRange(in, leftLen + 1, in.length), len - leftLen - 1);

        return node;
    }

    static void outPutInOrder(TreeNode node) {
        if (node != null) {
            outPutInOrder(node.left);
            System.out.print(node.val + " ");
            outPutInOrder(node.right);
        }
    }
}
