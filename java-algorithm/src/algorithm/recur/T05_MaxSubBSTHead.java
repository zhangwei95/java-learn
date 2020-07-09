package algorithm.recur;

import algorithm.tree.Node;
import algorithm.tree.T00_GenerateTree;
import algorithm.tree.T05_PrintBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 最大搜索树 头节点
 * @Author: zhangwei
 * @Date: 2020/7/9 10:20
 */
public class T05_MaxSubBSTHead {
    /**
     * 获取最大的搜索树节点数
     *
     * @param head
     * @return
     */
    public static Node getMaxSubBstHead(Node head) {
        if (head == null) {
            return head;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return head;
        }

        Node leftMax = getMaxSubBstHead(head.left);
        Node rightMax = getMaxSubBstHead(head.right);

        return getBSTSize(leftMax) >= getBSTSize(rightMax) ? leftMax : rightMax;
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
        Node bstHead;
        boolean isBST;
        int maxSubBSTSize;
        int max;
        int min;

        public Info(Node bstHead, boolean isBst, int maxSubBSTSize, int max, int min) {
            this.bstHead = bstHead;
            this.isBST = isBst;
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
    public static Node getMaxSubBstHead1(Node head) {
        Info process = process(head);
        return process == null ? null : process.bstHead;
    }


    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Node maxHead = head;
        boolean isBST = true;
        int maxSubBSTSize = 1;
        int min = head.value;
        int max = head.value;
        Info leftProcess = process(head.left);
        Info rightProcess = process(head.right);

        if(leftProcess != null) {
            min = Math.min(min, leftProcess.min);
            max = Math.max(max, leftProcess.max);
        }

        if(rightProcess != null) {
            min = Math.min(min, rightProcess.min);
            max = Math.max(max, rightProcess.max);
        }

        if (leftProcess != null && rightProcess != null) {
            // 当前树是否是搜索树
            isBST = leftProcess.isBST && rightProcess.isBST && rightProcess.min >= head.value && leftProcess.max <= head.value;
            maxHead = isBST ? head : leftProcess.maxSubBSTSize >= rightProcess.maxSubBSTSize ? leftProcess.bstHead : rightProcess.bstHead;
            maxSubBSTSize = isBST ? leftProcess.maxSubBSTSize + rightProcess.maxSubBSTSize + 1 : Math.max(leftProcess.maxSubBSTSize, rightProcess.maxSubBSTSize);
        } else if (leftProcess != null) {
            isBST = leftProcess.isBST && leftProcess.max <= head.value;
            maxHead = isBST ? head : leftProcess.bstHead;
            maxSubBSTSize = isBST ? leftProcess.maxSubBSTSize + 1 : leftProcess.maxSubBSTSize;
        } else if (rightProcess != null) {
            isBST = rightProcess.isBST && rightProcess.min >= head.value;
            maxHead = isBST ? head : rightProcess.bstHead;
            maxSubBSTSize = isBST ? rightProcess.maxSubBSTSize + 1 : rightProcess.maxSubBSTSize;
        }

        return new Info(maxHead, isBST, maxSubBSTSize, max, min);
    }


    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int times = 1000000;

        for (int i = 0; i < times; i++) {
            Node head = T00_GenerateTree.generateRandomTree(maxLevel, maxValue);
            if (getMaxSubBstHead1(head) != getMaxSubBstHead(head)) {
                System.out.println("error");
                T05_PrintBinaryTree.printTree(head);
            }
        }

    }

}
