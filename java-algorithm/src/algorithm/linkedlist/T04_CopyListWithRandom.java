package algorithm.linkedlist;

import sun.misc.CRC16;

import java.util.HashMap;
import java.util.Random;

/**
 * 带随机指针单链表 复制
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/27
 */
public class T04_CopyListWithRandom {
    public static class RandomNode{
        public int value;

        public RandomNode next;

        public RandomNode rand;

        public RandomNode(int value){
            this.value = value;
        }

    }


    // 使用 hashMap 空间复杂度 O(N) 时间复杂度 O(N)
    public static RandomNode copy1(RandomNode head){
        if (head == null){
            return null;
        }
        HashMap<RandomNode,RandomNode> help = new HashMap<>();
        RandomNode cur = head;
        while (cur != null){
            help.put(cur,new RandomNode(cur.value));
            cur = cur.next;
        }
        RandomNode result = help.get(head);
        result.next = help.get(head.next);
        result.rand = help.get(head.rand);
        cur = result;
        while (head.next != null){
            head = head.next;
            cur = cur.next;
            cur.next = help.get(head.next);
            cur.rand = help.get(head.rand);
        }
        return result;
    }

    // 空间复杂度 O(1)  时间复杂度 O(N)
    public static RandomNode copy2(RandomNode head){
        if (head == null){
            return null;
        }

        RandomNode cur = head;
        // 在原节点后 创建 复制节点
        while (cur != null){
            RandomNode next = cur.next;
            RandomNode copyNode = new RandomNode(cur.value);
            cur.next = copyNode;
            copyNode.next = next;
            cur = next;
        }
        cur = head;
        // 给复制节点 设置rand指针
        while (cur != null) {
            // rand 可能为空
            cur.next.rand = cur.rand == null ? null : cur.rand.next;
            cur = cur.next.next;
        }
        // 分离原链表 和 复制链表
        RandomNode result = head.next;
        cur = head;
        while (cur != null){
            // 原始链表 下个节点
            RandomNode next = cur.next.next;
            RandomNode copyNext = cur.next;
            cur.next = next;
            // 是否有下个节点  没有 ? 复制节点 指向 null: 指向下个复制节点
            copyNext.next =  next == null ? null : next.next;

            cur = next;
        }
        return result;
    }



    public static void main(String[] args) {
        RandomNode first = new RandomNode(1);
        RandomNode node2 = new RandomNode(2);
        first.next = node2;
        RandomNode node3 = new RandomNode(3);
        node2.next = node3;
        RandomNode node4 = new RandomNode(4);
        node3.next = node4;
        RandomNode node5 = new RandomNode(5);
        node4.next = node5;
        RandomNode node6 = new RandomNode(6);
        node5.next = node6;
        first.rand = node3;
        node2.rand = first;
        node3.rand = node5;
        node4.rand = node6;
        node5.rand = node4;
        node6.rand = node2;
        printRandLinkedList(first);
        RandomNode randomNode = copy2(first);
        printRandLinkedList(randomNode);


    }





    public static void printRandLinkedList(RandomNode head) {
        RandomNode cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
