package com.hazza.algorithms.BellmanFord;

import com.hazza.algorithms.ds.Edge;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created with IntelliJ IDEA.
 * Description: Find the shortest path from the source vertex using Bellman-Ford's Algorithm.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-2-12
 * Time: 9:05 PM
 */
public class FindShortestPath {
    // dist[i] => the shortest distance form source vertex to vertex i
    private static int[] dist;
    // path[i] => the predecessor vertex of the vertex i
    private static int[] path;
    // the unreachable distance
    private static int INF = Integer.MAX_VALUE - 1000; // prevent integer out of bounds

    /**
     * Util class, cannot be instantiated.
     */
    private FindShortestPath() {}

    /**
     * Initialized the arrays.
     * @param m The number of vertices.
     */
    private static void init(int m) {
        dist = new int[m + 1];
        path = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            dist[i] = INF;
            path[i] = i;
        }
    }

    /**
     * Find the shortest path from source vertex to any vertices and store the paths.
     * @param m The number of vertices.
     * @param source The source vertex.
     * @param edges The edges.
     */
    public static boolean bellmanFord(int m, int source, Edge[] edges) {
        init(m);
        int edgesNum = edges.length;
        dist[source] = 0;

        // relax for m-1 times at most
        for (int i = 0; i < m - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < edgesNum; j++) {
                if (dist[edges[j].x] + edges[j].weight < dist[edges[j].y]) {
                    dist[edges[j].y] = dist[edges[j].x] + edges[j].weight;
                    path[edges[j].y] = edges[j].x;
                    flag = true;
                }
            }
            if (!flag) break;
        }

        // check the negative cycles
        for (int i = 0; i < edgesNum; i++)
            if (dist[edges[i].x] + edges[i].weight < dist[edges[i].y])
                return false;
//                throw new RuntimeException("These edges contain negative cycles!");
        return true;
    }

    /**
     * Get the distance from the source to the v.
     * @param v The vertex.
     * @return The distance.
     */
    public static int getDistanceFromSource(int v) {
        return dist[v];
    }

    /**
     * Print the path from the source to v.
     * @param source The source vertex.
     * @param v The vertex.
     */
    public static void printPath(int source, int v) {
        if (source == v) return;
        Deque<Integer> stack = new ArrayDeque<>();
        int root = v;
        stack.push(root);
        while (root != source) {
            root = path[root];
            stack.push(root);
        }
        stack.pop();
        System.out.print(source);
        while (!stack.isEmpty())
            System.out.print(" --> " + stack.pop());
        System.out.println(" Total distance: " + dist[v]);
    }
}
