package algorithm.recur;

import algorithm.tree.Node;
import algorithm.tree.T00_GenerateTree;
import algorithm.tree.T05_PrintBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 最大的搜索树节点数
 * @Author: zhangwei
 * @Date: 2020/7/8 15:35
 */
public class T04_MaxSubBSTSize {


    /**
     * 获取最大的搜索树节点数
     *
     * @param head
     * @return
     */
    public static int getMaxSubBstSize(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(getMaxSubBstSize(head.left), getMaxSubBstSize(head.right));
    }


    public static int getBSTSize(Node head) {
        Queue<Integer> inSeq = new LinkedList<>();
        in(head, inSeq);
        int last = Integer.MIN_VALUE;
        int count = 0;
        while (!inSeq.isEmpty()) {
            Integer poll = inSeq.poll();
            if (last <= poll) {
                last = poll;
                count++;
            } else {
                return 0;
            }
        }
        return count;
    }

    public static void in(Node head, Queue<Integer> inSeq) {
        if (head == null) {
            return;
        }
        in(head.left, inSeq);
        inSeq.add(head.value);
        in(head.right, inSeq);

    }

    static class Info {
        boolean isBST;
        int maxSubBSTSize;
        int max;
        int min;

        public Info(boolean isBST, int maxSubBSTSize, int max, int min) {
            this.isBST = isBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.max = max;
            this.min = min;
        }
    }

    /**
     * 获取最大的搜索树节点数
     *
     * @param head
     * @return
     */
    public static int getMaxSubBstSize1(Node head) {
        Info process = process(head);
        return process == null? 0 : process.maxSubBSTSize;
    }


    public static Info process(Node head) {
        if (head == null) {
            return null;
        }

        boolean isBST = true;
        int maxSubBSTSize = 0;
        int max = 0;
        int min = 0;

        Info leftProcess = process(head.left);
        Info rightProcess = process(head.right);

        if (leftProcess != null && rightProcess != null) {
            isBST = leftProcess.isBST && rightProcess.isBST && rightProcess.min >= head.value && leftProcess.max <= head.value;
            maxSubBSTSize = isBST ? leftProcess.maxSubBSTSize + rightProcess.maxSubBSTSize + 1 : Math.max(leftProcess.maxSubBSTSize, rightProcess.maxSubBSTSize);
            max = isBST ? rightProcess.max : 0;
            min = isBST ? leftProcess.min : 0;
        } else if (leftProcess != null) {
            isBST = leftProcess.isBST && leftProcess.max <= head.value;
            maxSubBSTSize = isBST ? leftProcess.maxSubBSTSize + 1 : leftProcess.maxSubBSTSize;
            max = isBST ? head.value : 0;
            min = isBST ? leftProcess.min : 0;
        } else if (rightProcess != null) {
            isBST = rightProcess.isBST && rightProcess.min >= head.value;
            maxSubBSTSize = isBST ? rightProcess.maxSubBSTSize + 1 : rightProcess.maxSubBSTSize;
            max = isBST ? rightProcess.max : 0;
            min = isBST ? head.value : 0;
        } else {
            maxSubBSTSize = 1;
            max = head.value;
            min = head.value;
        }

        return new Info(isBST, maxSubBSTSize, max, min);
    }


    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int times = 1000000;

        for (int i = 0; i < times; i++) {
            Node head = T00_GenerateTree.generateRandomTree(maxLevel, maxValue);
            if (getMaxSubBstSize1(head) != getMaxSubBstSize(head)) {
                System.out.println("error");
                T05_PrintBinaryTree.printTree(head);
            }
        }

    }


}
