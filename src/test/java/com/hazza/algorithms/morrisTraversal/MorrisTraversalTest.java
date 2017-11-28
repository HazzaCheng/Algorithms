package com.hazza.algorithms.morrisTraversal;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created with IntelliJ IDEA.
 *
 * Description:
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 17-11-12
 * Time: 11:33 AM
 */
public class MorrisTraversalTest {
    MorrisTraversal test;
    private int[] pre = {6, 2, 1, 4, 3, 5, 7, 9, 8};
    private int[] in = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Before
    public void setUp() {
        test = new MorrisTraversal();
    }

    @Test
    public void testInoder() {
        TreeNode root = TreeNode.genTreeFromPreoderInorder(pre, in, 9);
        TreeNode.outPutInOrder(root);
        String expected = "1 2 3 4 5 6 7 8 9 ";
        String res = test.inorderMorrisTraversal(root);
        assertEquals(expected, res);
    }

    @Test
    public void testPreOrder() {
        TreeNode root = TreeNode.genTreeFromPreoderInorder(pre, in, 9);
        TreeNode.outPutInOrder(root);
        String expected = "6 2 1 4 3 5 7 9 8 ";
        String res = test.preorderMorrisTraversal(root);
        assertEquals(expected, res);
    }

    @Test
    public void testPostOrder() {
        TreeNode root = TreeNode.genTreeFromPreoderInorder(pre, in, 9);
        TreeNode.outPutInOrder(root);
        String expected = "1 3 5 4 2 8 9 7 6 ";
        String res = test.postorderMorrisTraversal(root);
        assertEquals(expected, res);
    }

}
