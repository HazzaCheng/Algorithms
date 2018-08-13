package com.hazza.algorithms.tree.LSM;

/**
 * Created with IntelliJ IDEA.
 * Description: Key-value pairs.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-8-4
 * Time: 2:23 PM
 */
public class KVTuple implements Comparable<KVTuple> {
    String key;
    String value;

    public KVTuple() {
    }

    public KVTuple(String key, String value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public int compareTo(KVTuple kvTuple) {
        return this.key.compareTo(kvTuple.key);
    }

    @Override
    public String toString() {
        return this.key + ": " + this.value;
    }

}
