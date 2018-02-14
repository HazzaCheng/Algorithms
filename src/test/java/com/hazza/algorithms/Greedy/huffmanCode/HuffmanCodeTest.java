package com.hazza.algorithms.Greedy.huffmanCode;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-08-14
 * Time: 8:09 PM
 */
public class HuffmanCodeTest {
    HuffmanCode test;
    List<FrequenceChar> chars;
    BinaryTree rightTree;

    String map2str(Map<Character, String> map) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<Character, String> temp: map.entrySet())
            list.add(temp.getKey() + temp.getValue());
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (String str: list) sb.append(str + " ");
        return sb.toString();
    }

    @Before
    public void setUp() {
        test = new HuffmanCode();
        chars = new ArrayList<>();
        chars.add(new FrequenceChar('a', 45));
        chars.add(new FrequenceChar('b', 13));
        chars.add(new FrequenceChar('c', 12));
        chars.add(new FrequenceChar('d', 16));
        chars.add(new FrequenceChar('e', 9));
        chars.add(new FrequenceChar('f', 5));
    }

    @Before
    public void generateRightTree() {
        BinaryTreeNode root = new BinaryTreeNode(100);
        root.left = new BinaryTreeNode(45, new FrequenceChar('a', 45));
        root.right = new BinaryTreeNode(55);
        root.right.left = new BinaryTreeNode(25);
        root.right.right = new BinaryTreeNode(30);
        root.right.left.left = new BinaryTreeNode(12, new FrequenceChar('c', 12));
        root.right.left.right = new BinaryTreeNode(13, new FrequenceChar('b', 13));
        root.right.right.left = new BinaryTreeNode(14);
        root.right.right.right = new BinaryTreeNode(16, new FrequenceChar('d', 16));
        root.right.right.left.left = new BinaryTreeNode(5, new FrequenceChar('f', 5));
        root.right.right.left.right = new BinaryTreeNode(9, new FrequenceChar('e', 9));

        rightTree = new BinaryTree(root);
    }

    boolean treeEqual(BinaryTree tree1, BinaryTree tree2) {
        return treeNodeEqual(tree1.root, tree2.root);
    }

    boolean treeNodeEqual(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 != null && node2 != null) {
            if (node1.freq != node2.freq
                    || (node1.symbol == null && node2.symbol != null)
                    || (node1.symbol != null && !node1.symbol.equals(node2.symbol))) {
                System.out.println("node1: " + node1);
                System.out.println("node2: " + node2);
                return false;
            }
            return (treeNodeEqual(node1.left, node2.left)) && (treeNodeEqual(node1.right, node2.right));
        }
        return false;
    }

    void printTree(BinaryTree tree) {
        printTreeNode(tree.root, 1);
    }

    void printTreeNode(BinaryTreeNode node, int level) {
        if (node != null) {
            BinaryTreeNode left = node.left;
            BinaryTreeNode right = node.right;
            System.out.println("level: " + level + " root: " + node + " left: " + left + " right: " + right);
            printTreeNode(left, level + 1);
            printTreeNode(right, level + 1);
        }
    }

    @Test
    public void testTree() {
        BinaryTree tree = test.makeHuffmanCodeTree(chars);

        System.out.println("Right Tree: ");
        printTree(rightTree);
        System.out.println("My tree: ");
        printTree(tree);

        assertTrue(treeEqual(rightTree, tree));
    }

    @Test
    public void testCode() {
        String right = "a0 b101 c100 d111 e1101 f1100 ";
        String res = map2str(test.makeVariablelengthCode(chars));
        System.out.println("code: " + res);

        assertEquals(right, res);
    }
}
