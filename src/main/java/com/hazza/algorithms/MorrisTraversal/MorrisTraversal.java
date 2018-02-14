package com.hazza.algorithms.MorrisTraversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * Description: Use Morris Traversal to do inorder, preorder, postorder.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 17-11-12
 * Time: 11:23 AM
 */
public class MorrisTraversal {

    /**
     * Inorder the binary tree.
     * @param root the root node
     * @return the output orders of nodes
     */
    String inorderMorrisTraversal(TreeNode root) {
        TreeNode cur = root, prev = null;
        StringBuffer sb = new StringBuffer();
        while (cur != null) {
            if (cur.left == null) {
                sb.append(cur.val + " ");
                cur = cur.right;
            } else {
                // find the predecessor
                prev = cur.left;
                while (prev.right != null && prev.right != cur)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    sb.append(cur.val + " ");
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Preorder the binary tree.
     * @param root the root node
     * @return the output orders of nodes
     */
    String preorderMorrisTraversal(TreeNode root) {
        TreeNode cur = root, prev = null;
        StringBuffer sb = new StringBuffer();
        while (cur != null) {
            if (cur.left == null) {
                sb.append(cur.val + " ");
                cur = cur.right;
            } else {
                // find the predecessor
                prev = cur.left;
                while (prev.right != null && prev.right != cur)
                    prev = prev.right;
                if (prev.right == null) {
                    sb.append(cur.val + " ");
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Postorder the binary tree.
     * @param root the root node
     * @return the output orders of nodes
     */
    String postorderMorrisTraversal(TreeNode root) {
        TreeNode dump = new TreeNode(0);
        dump.left = root;
        TreeNode cur = dump, prev = null;
        StringBuffer sb = new StringBuffer();

        while (cur != null) {
            if (cur.left == null) cur = cur.right;
            else {
                prev = cur.left;
                while (prev.right != null && prev.right != cur)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    sb.append(printReverse(cur.left, prev));
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }

        return sb.toString();
    }

    /**
     * Reverse output of the order from begin node to the end node.
     * @param from the begin node
     * @param to the end node
     * @return
     */
    private String printReverse(TreeNode from, TreeNode to) {
        List<Integer> temp = new ArrayList<>();
        TreeNode node = from;
        while (node != to.right) {
            temp.add(node.val);
            node = node.right;
        }
        Collections.reverse(temp);
        StringBuffer sb = new StringBuffer();
        for (int i: temp) sb.append(i + " ");

        return sb.toString();
    }
}
