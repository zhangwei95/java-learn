package algorithm.graph;

import java.util.*;

/**
 * @Description: 拓扑排序
 * 前提 ：有向无环图
 * 应用场景：依赖关系问题  先后顺序
 * @Author: zhangwei
 * @Date: 2020/7/14 17:19
 */
public class T03_TopSort {

    public static List<Node> topSort(Graph graph) {
        List<Node> result = new ArrayList<>();

        // 入度表
        HashMap<Node, Integer> inMap = new HashMap<>();

        // 无前驱点 队列
        Queue<Node> zeroInQueue = new LinkedList<>();

        HashMap<Integer, Node> nodes = graph.nodes;

        Collection<Node> values = nodes.values();
        values.forEach(item -> {
            inMap.put(item, item.in);
            if (item.in == 0) {
                zeroInQueue.add(item);
            }
        });

        while (!zeroInQueue.isEmpty()) {
            Node poll = zeroInQueue.poll();
            result.add(poll);
            List<Node> next = poll.next;
            next.forEach(item -> {
                inMap.put(item, inMap.get(item) - 1);
                if (inMap.get(item) == 0) {
                    zeroInQueue.add(item);
                }
            });
        }

        return result;

    }


    public static void main(String[] args) {
        Integer[][] matrix = {{1, 2, 3}, {3, 3, 4}, {5, 4, 5}};
        Graph graph = GraphGenerate.createGraph(matrix);
        List<Node> nodes = topSort(graph);
    }

}
