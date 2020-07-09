package algorithm.recur;

import algorithm.tree.Node;
import algorithm.tree.T00_GenerateTree;
import algorithm.tree.T05_PrintBinaryTree;

/**
 * @Description: 是否满二叉树
 * 性质：节点个数 等于 2^h-1 h 高度
 * @Author: zhangwei
 * @Date: 2020/7/8 10:41
 */
public class T02_IsFull {


    public static boolean isFull(Node head) {
        int n = n(head);
        int h = h(head);
        return (1 << h) - 1 == n;
    }

    /**
     * 获取节点数
     *
     * @param head
     * @return
     */
    public static int n(Node head) {
        if (head == null) {
            return 0;
        }
        int leftNodes = n(head.left);
        int rightNodes = n(head.right);

        return leftNodes + rightNodes + 1;
    }

    /**
     * 获取高度
     *
     * @param head
     * @return
     */
    public static int h(Node head) {
        if (head == null) {
            return 0;
        }
        int leftHeight = h(head.left);
        int rightHeight = h(head.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    static class Info {
        int h;
        int n;

        public Info(int h, int n) {
            this.h = h;
            this.n = n;
        }
    }

    public static boolean isFull2(Node head) {
        Info process = process(head);
        return (1 << process.h) - 1 == process.n;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);

        return new Info(Math.max(left.h, right.h) + 1, left.n + right.n + 1);
    }


    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = T00_GenerateTree.generateRandomTree(maxLevel, maxValue);
            if (isFull(head) != isFull2(head)) {
                System.out.println("oops");
                T05_PrintBinaryTree.printTree(head);
            }
        }
//        Node head = new Node(57);
//        if (isFull(head) != isFull2(head)) {
//            System.out.println("oops");
//            T05_PrintBinaryTree.printTree(head);
//        }
    }
}
