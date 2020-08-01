package algorithm.graph;

/**
 * @Description: 图的边
 * @Author: zhangwei
 * @Date: 2020/7/14 15:43
 */
public class Edge {
    /**
     * 权重
     */
    public int weight;

    /**
     * 起点
     */
    public Node from;

    /**
     * 终点
     */
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
