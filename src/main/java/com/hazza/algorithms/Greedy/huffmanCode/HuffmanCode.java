package com.hazza.algorithms.Greedy.huffmanCode;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description: Use Greedy algorithms to create Huffman code.
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-08-12
 * Time: 9:22 PM
 */
public class HuffmanCode {

    /**
     * Make huffman code and save it in a binary tree.
     *
     * @param chars the list of characters
     * @return a binary tree of Huffman Code
     */
    BinaryTree makeHuffmanCodeTree(List<FrequenceChar> chars) {
        int n = chars.size();
        // create a comparator used in priority queue
        Comparator<BinaryTreeNode> comparator = Comparator.comparingInt(o -> o.freq);
        PriorityQueue<BinaryTreeNode> q = new PriorityQueue<>(n, comparator);
        // initialize the priority queue
        for (FrequenceChar c: chars) q.add(new BinaryTreeNode(c.frequency, c));
        for (int i = 1; i <= n-1; i++) {
            BinaryTreeNode node = new BinaryTreeNode();
            node.left = q.poll();
            node.right = q.poll();
            node.freq = node.left.freq + node.right.freq;
            q.add(node);
        }
        BinaryTree tree = new BinaryTree(q.peek());
        return tree;
    }

    /**
     * Make huffman code and save it in a map,
     * whose key is the character and the value is the variable-length code;
     *
     * @param chars the list of characters
     * @return a map whose key is the character and the value is the variable-length code
     */
    Map<Character, String> makeVariablelengthCode(List<FrequenceChar> chars) {
        BinaryTree tree = makeHuffmanCodeTree(chars);
        Map<Character, String> map = new HashMap<>();
        dfs(tree.root, map, new StringBuilder());
        return map;
    }

    /**
     * Use dfs to traverse a binary tree.
     *
     * @param node the binary tree node
     * @param map a map whose key is the character and the value is the variable-length code
     * @param path the variable-length code
     */
    void dfs(BinaryTreeNode node, Map<Character, String> map, StringBuilder path) {
        if (node.left == null && node.right == null) {
            map.put(node.symbol.c, path.toString());
            return;
        }
        if (node.left != null) {
            path.append('0');
            dfs(node.left, map, path);
            path.deleteCharAt(path.length() - 1);
        }
        if (node.right != null) {
            path.append('1');
            dfs(node.right, map, path);
            path.deleteCharAt(path.length() - 1);
        }
    }

}

