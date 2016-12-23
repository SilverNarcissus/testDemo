package DataStructure;

/**
 * Created by SilverNarcissus on 2016/12/14.
 */
public class Edge {
    /**
     * 起始点
     */
    public int from;
    /**
     * 结束点
     */
    public int to;
    /**
     * 距离
     */
    public int dist;

    public Edge(int from, int to, int dist) {
        this.from = from;
        this.to = to;
        this.dist = dist;
    }

    public Edge(Edge e) {
        this.from = e.from;
        this.to = e.to;
        this.dist = e.dist;
    }
}
