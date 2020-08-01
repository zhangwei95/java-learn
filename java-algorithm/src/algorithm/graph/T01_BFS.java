package algorithm.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 广度优先遍历图
 * @Author: zhangwei
 * @Date: 2020/7/14 15:48
 */
public class T01_BFS {


    public static void bfs(Node node){
        if (node == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);

        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll.value);
            poll.next.forEach(item->{
                if (!set.contains(item)){
                    set.add(item);
                    queue.add(item);
                }
            });

        }

    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next.add(node2);
        node1.next.add(node3);
        node1.next.add(node4);
        node2.next.add(node1);
        node2.next.add(node4);
        node2.next.add(node5);
        node3.next.add(node1);
        node3.next.add(node4);
        node3.next.add(node5);
        node4.next.add(node1);
        node4.next.add(node2);
        node4.next.add(node3);
        node4.next.add(node5);
        node5.next.add(node2);
        node5.next.add(node3);
        node5.next.add(node4);
        bfs(node4);
    }










}
