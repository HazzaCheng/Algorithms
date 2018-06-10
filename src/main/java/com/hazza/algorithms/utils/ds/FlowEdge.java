package com.hazza.algorithms.utils.ds;

/**
 * Created with IntelliJ IDEA.
 * Description: Represent an edge in the flow network including two points, cap and flow.
 * User: HazzaCheng
 * Contact: hazzacheng@gmail.com
 * Date: 18-6-10
 * Time: 7:29 PM
 */
public class FlowEdge {
    public int from, to;
    public int flow, cap;

    public FlowEdge() {
        from = 0;
        to = 0;
        flow = 0;
        cap = 0;
    }

    public FlowEdge(int from, int to, int flow, int cap) {
        this.from = from;
        this.to = to;
        this.flow = flow;
        this.cap = cap;
    }
}
