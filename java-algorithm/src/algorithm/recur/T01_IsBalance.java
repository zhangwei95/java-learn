package algorithm.recur;

import algorithm.tree.Node;
import algorithm.tree.T00_GenerateTree;
import algorithm.tree.T05_PrintBinaryTree;

/**
 * @Description: 给定头节点 判定该树是否为平衡树
 * 所有节点左右子树高度差不超过1
 * @Author: zhangwei
 * @Date: 2020/7/8 09:51
 */
public class T01_IsBalance {


    public static boolean isBalanced1(Node head) {
        boolean[] flag = new boolean[1];
        flag[0] = true;
        process1(head, flag);
        return flag[0];
    }

    public static int process1(Node head, boolean[] flag) {
        if (!flag[0] || head == null) {
            return -1;
        }

        int leftHeight = process1(head.left, flag);
        int rightHeight = process1(head.right, flag);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            flag[0] = false;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    static class Info {
        private boolean isBalanced;
        private int height;
        public Info(boolean isBalanced,int height){
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalance2(Node head){
        Info info = process2(head);
        return info.isBalanced;
    }

    /**
     * 每个递归子过程 反馈父过程需要信息
     * @param head
     * @return
     */
    public static Info process2(Node head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);
        boolean isBalance = true;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height)>1){
            isBalance = false;
        }
        return new Info(isBalance,height);
    }




    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = T00_GenerateTree.generateRandomTree(maxLevel, maxValue);
            if (isBalanced1(head) != isBalance2(head)) {
                System.out.println("oops");
                T05_PrintBinaryTree.printTree(head);
            }
        }


    }
}
