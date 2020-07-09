package algorithm.recur;

import algorithm.tree.Node;
import algorithm.tree.T00_GenerateTree;
import algorithm.tree.T05_PrintBinaryTree;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 是否完全二叉树
 * 所有
 * @Author: zhangwei
 * @Date: 2020/7/9 10:58
 */
public class T06_IsCBT {




    public static boolean isCBT(Node head){
        if (head == null){
            return true;
        }

        Queue<Node> seq = new LinkedList<>();
        seq.add(head);
        Node left = null;
        Node right = null;
        boolean missingNode = false;

        while(!seq.isEmpty()){
            Node poll = seq.poll();
            left = poll.left;
            right = poll.right;
            // 遍历后续节点时 发现前面节点丢失过节点  或者  前面没丢失过节点 但是 当前左孩子丢失 右孩子存在
            if ((missingNode && (left != null || right != null)) || left == null && right != null){
                return false;
            }

            if (left == null || right == null){
                missingNode = true;
            }

            if (left != null){
                seq.add(left);
            }

            if (right != null){
                seq.add(right);
            }

        }

        return true;

    }


    public static boolean isCBT2(Node head){
        if (head == null){
            return true;
        }

        return process(head).isCBT;
    }

    public static Info process(Node head){
        if (head == null){
            return new Info(true,true,0);
        }

        boolean isCBT = true;
        boolean isFull = true;
        int height = 1;

        Info left = process(head.left);
        Info right = process(head.right);

        height = Math.max(left.height,right.height) + 1;

        isFull = left.height == right.height && left.isFull && right.isFull;

        isCBT = left.height == right.height ? left.isFull && right.isCBT : left.height == right.height + 1 && (right.isFull && left.isCBT);

        return new Info(isCBT,isFull,height);
    }


    static class Info{
        boolean isCBT;
        boolean isFull;
        int height;


        public Info(boolean isCBT,boolean isFull,int height){
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }


    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int times = 1000000;

        for (int i = 0; i < times; i++) {
            Node head = T00_GenerateTree.generateRandomTree(maxLevel, maxValue);
            if (isCBT(head) != isCBT2(head)) {
                System.out.println("error");
                T05_PrintBinaryTree.printTree(head);
            }
        }

        Node head = new Node(10);
        head.left = new Node(5);
        head.right = new Node(15);
        System.out.println(isCBT2(head));

    }



}
