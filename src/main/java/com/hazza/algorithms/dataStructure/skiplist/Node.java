package com.hazza.algorithms.dataStructure.skiplist;

/**
 * Created with IntelliJ IDEA.
 * Description: A generic class used to store data.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-8-4
 * Time: 4:43 PM
 */
public class Node<T extends Comparable> {
    T data;
    /**
     * levels array
     */
    Node[] forward;

    Node(T data, int level) {
        this.data = data;
        this.forward = new Node[level];
    }

    @Override
    public String toString() {
        return "[data = " + data + ", level = " + forward.length + "]";
    }
}
