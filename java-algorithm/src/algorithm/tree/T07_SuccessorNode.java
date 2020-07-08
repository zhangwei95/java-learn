package algorithm.tree;

/**
 * @Description: 后继节点  (中序遍历中的下一个节点)
 * 前驱节点 (中序遍历中的前一个节点)
 * @Author: zhangwei
 * @Date: 2020/7/6 23:15
 */
public class T07_SuccessorNode {

    static class Node{
        private int value;
        private Node left;
        private Node right;
        private Node parent;
        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getSuccessorNode(Node node){
        if (node == null){
            return null;
        }

        if (node.right != null){
            // 右孩子不为空
            // 其右孩子的最左子树节点即为当前节点的后继节点
            return getMostLeft(node.right);
        } else {
            // 左中右
            // 其父节点
            // 当前节点是左孩子 其父就是后继
            // 当前节点是右孩子 找到最上一个不是右孩子的父节点的父
            Node parent = node.parent;


            while (parent != null && parent.right == node){
                node = parent;
                parent = parent.parent;
            }


            return parent;
        }




    }


    /**
     * 获取最左节点
     * @param node
     * @return
     */
    public static Node getMostLeft(Node node){
        Node mostLeft = node;
        while (mostLeft.left != null){
            mostLeft = mostLeft.left;
        }
        return mostLeft;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }


}
