package com.hazza.algorithms.SPFA;

/**
 * Created with IntelliJ IDEA.
 * Description: Represent an edge include two points and its weight.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-10
 * Time: 9:05 PM
 */
public class Edge implements Comparable<Edge> {
    int x, y;   // two points
    int weight; // the weight of this edge

    public Edge(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}
