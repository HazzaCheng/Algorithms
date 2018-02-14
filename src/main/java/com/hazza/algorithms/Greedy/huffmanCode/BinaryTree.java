package com.hazza.algorithms.Greedy.huffmanCode;

/**
 * Created with IntelliJ IDEA.
 * Description: A binary tree model, restore frequency and char.
 * Author: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 2017-08-12
 * Time: 9:44 PM
 */
class BinaryTree {
    BinaryTreeNode root;

    BinaryTree() {
        root = new BinaryTreeNode();
    }

    BinaryTree(BinaryTreeNode node) {
        root = new BinaryTreeNode(node);
    }
}

class BinaryTreeNode {
    BinaryTreeNode left;
    BinaryTreeNode right;
    int freq;
    FrequenceChar symbol;

    BinaryTreeNode() {
        this.left = null;
        this.right = null;
        this.freq = 0;
        this.symbol = null;
    }

    BinaryTreeNode(BinaryTreeNode node) {
        this.left = node.left;
        this.right = node.right;
        this.freq = node.freq;
        this.symbol = node.symbol;
    }

    BinaryTreeNode(int frequency) {
        this.left = null;
        this.right = null;
        this.freq = frequency;
        this.symbol = null;
    }

    BinaryTreeNode(int frequency, FrequenceChar symbol) {
        this.left = null;
        this.right = null;
        this.freq = frequency;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "(" + freq + ", " + symbol + ")";
    }
}
