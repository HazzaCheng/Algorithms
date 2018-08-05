package com.hazza.algorithms.dataStructure.skiplist;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description: A simple implementation for skip list.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-8-4
 * Time: 4:51 PM
 */
public class SkipList<T extends Comparable> {
    private static final int DEFAULTL_LEVEL = 3;
    private static final int MAXIMUM_LEVEL = 16;
    private int level;
    /**
     * Whether contains duplicates.
     */
    private boolean isDuplicate;
    private Node<T> head;
    /**
     * To ensure the K.
     */
    private Random random;
    /**
     * Used to updates skip list.
     */
    private Node<T>[] updates;

    public SkipList(boolean isDuplicate) {
        this.isDuplicate = isDuplicate;
        head = new Node<>(null, MAXIMUM_LEVEL);
        random = new Random();
        level = DEFAULTL_LEVEL;
        updates = new Node[MAXIMUM_LEVEL];

        for (int i = 0; i < DEFAULTL_LEVEL; i++) {
            head.forward[i] = null;
        }
    }

    /**
     * Get the random k.
     * @return Random k.
     */
    private int randomK() {
        int k = 1, n = 2;

        while (random.nextInt(n) == 1) {
            ++k;
            if (k > MAXIMUM_LEVEL) {
                k = 0;
            }
        }

        return k;
    }

    /**
     * Find the data node and updates array.
     * @param data The target.
     * @return The inserted position.
     */
    private Node<T> find(T data) {
        Node<T> cur = head;

        for (int i = level - 1; i >= 0; i--) {
            while (cur.forward[i] != null && cur.forward[i].data.compareTo(data) < 0) {
                cur = cur.forward[i];
            }
            updates[i] = cur;
        }
        cur = cur.forward[0];

        return cur;
    }

    /**
     * Determine if the list contains data.
     * @param data The target.
     * @return True or false.
     */
    public Boolean isFound(T data) {
        Node<T> cur = head;

        for (int i = level - 1; i >= 0; i--) {
            while (cur.forward[i] != null && cur.forward[i].data.compareTo(data) < 0) {
                cur = cur.forward[i];
            }
        }
        cur = cur.forward[0];

        return cur != null && cur.data.compareTo(data) == 0;
    }

    /**
     * Insert the node into skip list.
     * @param data The inserted data.
     */
    public void insert(T data) {
        Node<T> cur = find(data);

        // cannot have duplicates
        if (!isDuplicate && cur != null && cur.data.compareTo(data) == 0) {
            return;
        }

        int k = randomK();

        if (k >= level) {
            for (int i = level; i < k; i++) {
                updates[i] = head;
            }
            level = k;
        }

        Node<T> newNode = new Node<>(data, k);

        for (int i = 0; i < k; i++) {
            newNode.forward[i] = updates[i].forward[i];
            updates[i].forward[i] = newNode;
        }
    }

    /**
     * Convert the array int skip list.
     * @param datas The array.
     */
    public void arrayToSkipList(T[] datas) {
        for (T data: datas) {
            insert(data);
        }
    }

    /**
     * Delete the node from skip list.
     * @param data The deleted node.
     */
    public void delete(T data) {
        Node<T> cur = find(data);

        if (cur != null && cur.data.compareTo(data) == 0) {
            for (int i = 0; i < level; i++) {
                if (updates[i].forward[i] != cur) {
                    break;
                }
                updates[i].forward[i] = cur.forward[i];
            }

            // decrease list level if the level only had the deleted node
            while (level > 0 && head.forward[level - 1] == null) {
                --level;
            }
        }
    }

    /**
     * Print the skip list.
     */
    public void print() {
        int w = 0;
        Node<T> tmp = head;
        while (tmp.forward[0] != null) {
            tmp = tmp.forward[0];
            ++w;
        }

        for (int i = level - 1; i >= 0; i--) {
            Node<T> cur = head.forward[i];
            tmp = head.forward[0];
            for (int j = 0; j < w; j++) {
                if (cur == null || cur.data.compareTo(tmp.data) != 0) {
                    System.out.print("    ");
                } else {
                    System.out.printf("%3s ", cur.data);
                    cur = cur.forward[i];
                }
                tmp = tmp.forward[0];
            }
            System.out.println();
        }
    }
}
