package com.hazza.algorithms.utils.ds;

/**
 * Created with IntelliJ IDEA.
 * Description: Represent an edge in the flow network including two points, cap, flow and its cost.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-6-12
 * Time: 4:30 PM
 */
public class CostFlowEdge {
    public int from, to;
    public int flow, cap;
    public int cost;

    public CostFlowEdge() {
        from = 0;
        to = 0;
        flow = 0;
        cap = 0;
        cost = 0;
    }

    public CostFlowEdge(int from, int to, int flow, int cap, int cost) {
        this.from = from;
        this.to = to;
        this.flow = flow;
        this.cap = cap;
        this.cost = cost;
    }
}
