package algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 二叉树层次遍历
 * @Author: zhangwei
 * @Date: 2020/6/29 11:31
 */
public class T03_LevelTraversalBT {


    public static void levelTraversal(Node head) {

        if (head == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node top = queue.poll();
            System.out.print(top.value + " ");

            if (top.left != null) {
                queue.add(top.left);
            }

            if (top.right != null) {
                queue.add(top.right);
            }

        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        levelTraversal(head);
    }


}
