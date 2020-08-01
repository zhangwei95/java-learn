package algorithm.graph;


import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 图节点
 * @Author: zhangwei
 * @Date: 2020/7/14 15:38
 */
public class Node {
    public int value;

    public int in;

    public int out;

    /**
     * 可直接到达点
     */
    public List<Node> next;

    /**
     * 构成的 边
     */
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        next = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
