package algorithm.recur;

import algorithm.tree.Node;
import algorithm.tree.T00_GenerateTree;
import algorithm.tree.T05_PrintBinaryTree;

import java.util.*;

/**
 * @Description: 最近的交汇点
 * @Author: zhangwei
 * @Date: 2020/7/9 13:37
 */
public class T07_LowestAncestor {


    public static Node getLowestAncestor(Node head, Node n1, Node n2) {
        if (head == null || n1 == null || n2 == null) {
            return null;
        }

        // 父关系对应表
        Map<Node, Node> parentMap = new HashMap<>();
        initMap(head, parentMap);
        parentMap.put(head, null);

        if (n1 == head || n2 ==head){
            return head;
        }

        Set<Node> n1Set = new HashSet<>();
        n1Set.add(n1);

        Node cur = parentMap.get(n1);
        while (cur != null) {
            n1Set.add(cur);
            cur = parentMap.get(cur);
        }
        cur = n2;
        while (cur != null) {
            if (n1Set.contains(cur)) {
                return cur;
            }
            cur = parentMap.get(cur);
        }

        return cur;
    }

    public static void initMap(Node head, Map<Node, Node> map) {
        if (head == null) {
            return;
        }
        Queue<Node> levelQueue = new LinkedList<>();
        levelQueue.add(head);

        while (!levelQueue.isEmpty()) {
            Node poll = levelQueue.poll();

            if (poll.left != null) {
                map.put(poll.left, poll);
                levelQueue.add(poll.left);
            }

            if (poll.right != null) {
                map.put(poll.right, poll);
                levelQueue.add(poll.right);
            }
        }
    }


    public static Node getLowestAncestor1(Node head, Node n1, Node n2) {
        return process(head, n1, n2).ancestor;
    }

    public static Info process(Node head, Node n1, Node n2) {
        if (head == null) {
            return new Info(null, false, false);
        }
        Node ancestor = null;


        Info leftInfo  = process(head.left, n1, n2);
        Info rightInfo = process(head.right, n1, n2);

        boolean findN1 = head == n1 || leftInfo.findN1 || rightInfo.findN1;
        boolean findN2 = head == n2 || leftInfo.findN2 || rightInfo.findN2;

        if (leftInfo.ancestor != null){
            ancestor = leftInfo.ancestor;
        }

        if (rightInfo.ancestor != null){
            ancestor = rightInfo.ancestor;
        }

        if (ancestor == null){
            if (findN1 && findN2){
                ancestor = head;
            }
        }

        return new Info(ancestor, findN1, findN2);
    }


    static class Info {
        Node ancestor;
        boolean findN1;
        boolean findN2;

        public Info(Node ancestor, boolean findN1, boolean findN2) {
            this.ancestor = ancestor;
            this.findN1 = findN1;
            this.findN2 = findN2;
        }
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }


    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 10000;
        int times = 100000;

        for (int i = 0; i< times; i++){
            Node head = T00_GenerateTree.generateRandomTree(maxLevel, maxValue);
            Node n1 = pickRandomOne(head);
            Node n2 = pickRandomOne(head);
            Node lowestAncestor = getLowestAncestor(head, n1, n2);
            Node lowestAncestor1 = getLowestAncestor1(head, n1, n2);

            if (lowestAncestor != lowestAncestor1){
                T05_PrintBinaryTree.printTree(head);
                System.out.println("error");
            }
        }


    }

}
