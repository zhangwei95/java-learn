package algorithm.recur;

import algorithm.tree.Node;
import algorithm.tree.T00_GenerateTree;
import algorithm.tree.T05_PrintBinaryTree;

import java.util.*;

/**
 * @Description: 树上的最远距离
 * @Author: zhangwei
 * @Date: 2020/7/9 16:56
 */
public class T08_MaxDistance {


    public static int getMaxDistance(Node head) {
        int max = 0;
        List<Node> pre = new ArrayList<>();
        Map<Node, Node> parentMap = new HashMap<>();
        initMap(parentMap, head);
        process(head, pre);
        for (int i = 0; i < pre.size(); i++) {
            for (Node node : pre) {
                max = Math.max(max, distance(parentMap, pre.get(i), node));
            }
        }
        return max;
    }

    public static void initMap(Map<Node, Node> parentMap, Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> levelQueue = new LinkedList<>();
        levelQueue.add(head);

        while (!levelQueue.isEmpty()) {
            Node poll = levelQueue.poll();

            if (poll.left != null) {
                parentMap.put(poll.left, poll);
                levelQueue.add(poll.left);
            }

            if (poll.right != null) {
                parentMap.put(poll.right, poll);
                levelQueue.add(poll.right);
            }
        }
    }

    public static void process(Node head, List<Node> pre) {
        if (head == null) {
            return;
        }
        pre.add(head);
        process(head.left, pre);
        process(head.right, pre);
    }


    public static int distance(Map<Node, Node> parentMap, Node n1, Node n2) {

        Node cur = n1;

        Set<Node> n1Set = new HashSet<>();
        n1Set.add(n1);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            n1Set.add(cur);
        }
        cur = n2;
        Node ancestor = null;
        while (cur != null) {
            if (n1Set.contains(cur)) {
                ancestor = cur;
                break;
            }
            cur = parentMap.get(cur);
        }
        Node tmpN1 = n1;
        int distance1 = 0;
        while (tmpN1 != null && tmpN1 != ancestor) {
            tmpN1 = parentMap.get(tmpN1);
            distance1++;
        }
        Node tmpN2 = n2;
        int distance2 = 0;
        while (tmpN2 != null && tmpN2 != ancestor) {
            tmpN2 = parentMap.get(tmpN2);
            distance2++;
        }

        return distance1 + distance2 + 1;
    }


    public static int getMaxDistance2(Node head) {
        return process(head).maxDistance;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        int maxDistance = 0;
        int height = 0;

        Info left = process(head.left);
        Info right = process(head.right);

        height = Math.max(left.height, right.height) + 1;
        maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), left.height + right.height + 1);

        return new Info(maxDistance, height);

    }

    static class Info {
        int maxDistance;
        int height;


        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 10000;
        int times = 100000;

        for (int i =0 ; i<10000;i++){
            Node head = T00_GenerateTree.generateRandomTree(maxLevel, maxValue);

            if (getMaxDistance(head) != getMaxDistance2(head)){
                System.out.println("error");
                T05_PrintBinaryTree.printTree(head);
            }
        }

    }
}
