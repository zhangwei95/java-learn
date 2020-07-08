package algorithm.recur;

import algorithm.tree.Node;
import algorithm.tree.T00_GenerateTree;
import algorithm.tree.T05_PrintBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 是否二叉搜索树
 * 性质：任意一节点的左孩子一定小于等于其父节点 其父节点一定小于等于右孩子
 * 中序遍历 正序
 * @Author: zhangwei
 * @Date: 2020/7/8 11:10
 */
public class T03_IsBST {

    public static boolean isBST(Node head) {

        Queue<Integer> queue = new LinkedList<Integer>();

        in(head, queue);
        boolean flag = true;
        int last = 0;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll < last) {
                flag = false;
                break;
            }
            last = poll;
        }
        return flag;
    }

    public static void in(Node head, Queue<Integer> queue) {
        if (head == null) {
            return;
        }
        in(head.left, queue);
        queue.add(head.value);
        in(head.right, queue);
    }


    static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST2(Node head){
        Info process = process(head);
        return process == null || process.isBST;
    }

    public static Info process(Node head){
        if (head == null){
//            return new Info(true,0,0);
            return null;
        }
        boolean isBST = true;
        int min = 0;
        int max = 0;

        Info left = process(head.left);
        Info right = process(head.right);

        if (left != null && right != null){
            isBST = left.isBST && right.isBST && left.max <= head.value && head.value <= right.min;
            min = left.min;
            max = right.max;
        } else if (left == null && right != null) {
            isBST = right.isBST && right.min >= head.value;
            min = head.value;
            max = right.max;
        } else  if (left != null){
            isBST = left.isBST && left.max <= head.value;
            min = left.min;
            max = head.value;
        } else {
            min = head.value;
            max = head.value;
        }
        return new Info(isBST,max,min);

    }


    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = T00_GenerateTree.generateRandomBST(maxLevel, maxValue);
            if (isBST(head) != isBST2(head)) {
                System.out.println("oops");
                T05_PrintBinaryTree.printTree(head);
            }
        }


//        Node head = new Node(39);
//        head.left = new Node(38);
//        if (isBST(head) != isBST2(head)) {
//            System.out.println("oops");
//            T05_PrintBinaryTree.printTree(head);
//        }
    }


}
