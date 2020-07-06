package algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: 二叉树 序列化 反序列化
 * @Author: zhangwei
 * @Date: 2020/6/29 11:39
 */
public class T04_SerializeAndReconstructTree {

    /**
     * 先  后  层  序列化 可 反序列化
     *
     * 中 序 无法还原
     *
     * 不同的树  中序 可能一样
     * 1           2
     *  \        /
     *   2     1
     *   中序 1 2  加空节点  null 1 null 2 null
     */
    public static Queue<Integer> preSerialize(Node head){
        Queue<Integer> serialize = new LinkedList<>();
        pres(head,serialize);
        return serialize;
    }

    /**
     * 前序序列化
     * @param head
     * @param serialize
     */
    public static void pres(Node head,Queue<Integer> serialize){
        if (head == null){
            serialize.add(null);
            return;
        }
        serialize.add(head.value);
        pres(head.left, serialize);
        pres(head.right,serialize);
    }


    /**
     * 根据前序 反序列化二叉树
     * 中左右
     * @param preQueue
     * @return
     */
    public static Node buildByPreQueue(Queue<Integer> preQueue){
        if (preQueue == null || preQueue.isEmpty()){
            return null;
        }
        return preB(preQueue);
    }

    public static Node preB(Queue<Integer> preQueue){
        Integer value = preQueue.poll();

        if (value == null){
            return null;
        }
        Node n = new Node(value);

        n.left = preB(preQueue);
        n.right = preB(preQueue);
        return n;
    }

    /**后序序列化*/
    public static Queue<Integer> posSerialize(Node head){
        if (head == null){
            return null;
        }
        Queue<Integer> postQueue = new LinkedList<>();
        posSerialize(head,postQueue);
        return postQueue;
    }

    /**
     * 后序遍历生成
     * 左右中
     * */
    public static void posSerialize(Node head, Queue<Integer> postQueue){
        if (head == null){
            postQueue.add(null);
            return;
        }
        posSerialize(head.left,postQueue);
        posSerialize(head.right,postQueue);
        postQueue.add(head.value);
    }

    /**
     * 后序反序列化
     * 左右中  转 中右左
     * @param postQueue
     * @return
     */
    public static Node buildByPos(Queue<Integer> postQueue){
        if (postQueue == null || postQueue.isEmpty()){
            return null;
        }

        Stack<Integer> reverseQueue = new Stack<>();
        // 左右中 转 中右左
        while (!postQueue.isEmpty()){
            reverseQueue.push(postQueue.poll());
        }
        Node node = postB(reverseQueue);
        return node;

    }


    /** 后序反序列化*/
    public static Node postB(Stack<Integer> reverseQueue){
        Integer pop = reverseQueue.pop();
        if (pop == null){
            return null;
        }
        Node head = new Node(pop);
        head.right = postB(reverseQueue);
        head.left = postB(reverseQueue);
        return head;
    }

    /**按层序列化*/
    public static Queue<Integer> levelSerialize(Node node){
        if (node == null){
            return null;
        }

        Queue<Integer> queue = new LinkedList<>();

        Queue<Node> nodeQueue = new LinkedList<>();

        nodeQueue.add(node);

        while (!nodeQueue.isEmpty()){
            Node poll = nodeQueue.poll();
            if (poll == null){
                queue.add(null);
            } else {
                queue.add(poll.value);
                nodeQueue.add(poll.left);
                nodeQueue.add(poll.right);
            }
        }
        return queue;
    }

    /**
     * 按层构建
     * @param queue
     * @return
     */
    public static Node buildByLevel(Queue<Integer> queue){
        if (queue == null || queue.isEmpty()){
            return null;
        }

        Node head = new Node(queue.poll());
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(head);
        while (!nodeQueue.isEmpty()){
            Node poll  = nodeQueue.poll();
            Integer left = queue.poll();
            if (left != null){
                poll.left = new Node(left);
                nodeQueue.add(poll.left);
            }
            Integer right = queue.poll();
            if (right != null){
                poll.right = new Node(right);
                nodeQueue.add(poll.right);
            }

        }

        return head;
    }



    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 10000;
        int testTimes = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = T00_GenerateTree.generateRandomBST(maxLevel, maxValue);
            if (head == null){
                continue;
            }
            T05_PrintBinaryTree.printTree(head);
            Queue<Integer> pre = preSerialize(head);
            Queue<Integer> pos = posSerialize(head);
            Queue<Integer> level = levelSerialize(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPos(pos);
            T05_PrintBinaryTree.printTree(preBuild);
            T05_PrintBinaryTree.printTree(posBuild);
            Node levelBuild = buildByLevel(level);
//            if (!T00_GenerateTree.isSameValueStructure(preBuild, posBuild) ) {
//                System.out.println("Oops!");
//            }
            if (!T00_GenerateTree.isSameValueStructure(preBuild, posBuild) || !T00_GenerateTree.isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

}
