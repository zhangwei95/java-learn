package algorithm.tree;

import javax.sound.midi.Soundbank;
import java.util.Stack;

/**
 * @Description: 非递归遍历二叉树
 * @Author: zhangwei
 * @Date: 2020/6/28 21:29
 */
public class T02_UnRecursiveTraversalBT {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node left1 = new Node(2);
        Node right1 = new Node(3);
        Node left11 = new Node(4);
        Node right11 = new Node(5);
        Node left12 = new Node(6);
        Node right12 = new Node(7);

        head.left = left1;
        head.right = right1;
        left1.left = left11;
        left1.right = right11;
        right1.left = left12;
        right1.right = right12;
        // 先 1 2 4 5 3 6 7
        pre(head);
        System.out.println();
        // 中 4 2 5 1 6 3 7
        mid(head);
        System.out.println();
        // 后  4 5 2 6 7 3 1
        pos(head);
        System.out.println();
    }

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> help = new Stack<>();
        help.push(head);
        while (!help.empty()) {
            Node pop = help.pop();
            System.out.print(pop.value + " ");
            if (pop.right != null) {
                help.push(pop.right);
            }
            if (pop.left != null) {
                help.push(pop.left);
            }
        }

    }

    public static void mid(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> help = new Stack<>();
        while (!help.isEmpty() || head != null) {
            if (head != null) {
                help.push(head);
                head = head.left;
            } else {
                head = help.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }

        System.out.println();
    }


    public static void pos(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> help = new Stack<>();
        Node c = null;
        help.push(head);
        Node h = null; // 当前打印的点
        while (!help.isEmpty()) {
            c = help.peek();
            if (c.left != null && c.left != h && c.right != h) {
                help.push(c.left);
            } else if (c.right != null && c.right != h) {
                help.push(c.right);
            } else {
                System.out.println(help.pop().value + " ");
                // h 始终是打完的点
                h = c;
            }
        }

    }


}
