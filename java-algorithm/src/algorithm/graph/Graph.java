package algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description: 图
 * @Author: zhangwei
 * @Date: 2020/7/14 17:28
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
