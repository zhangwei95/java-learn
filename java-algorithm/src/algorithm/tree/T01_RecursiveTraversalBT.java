package algorithm.tree;

/**
 * @Description: 递归遍历二叉树
 * @Author: zhangwei
 * @Date: 2020/6/28 21:29
 */
public class T01_RecursiveTraversalBT {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node left1 = new Node(2);
        Node right1 = new Node(3);
        Node left11 = new Node(4);
        Node right11 = new Node(5);
        Node left12 = new Node(6);
        Node right12 =new Node(7);

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
        // 递归序列 124442555213666377731
        // 递归序   每个节点 都会经过3次 在第一次经过时打印 先 第二次 中 第三次 后
    }

    public static void pre(Node head){
        if (head == null){
            return;
        }
        System.out.print(head.value + " ");
        pre(head.left);
        pre(head.right);

    }

    public static void mid(Node head){
        if (head == null){
            return;
        }
        mid(head.left);
        System.out.print(head.value + " ");
        mid(head.right);
    }

    public static void pos(Node head){
        if (head == null){
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.value + " ");
    }


























}
