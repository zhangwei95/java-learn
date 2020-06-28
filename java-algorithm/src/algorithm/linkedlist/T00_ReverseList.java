package algorithm.linkedlist;

/**
 * @Description: 链表反转
 * @Author: zhangwei
 * @Date: 2020/6/27 23:16
 */
public class T00_ReverseList {



    public static Node reverseList(Node head){
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static DoubleNode DoubleReverseList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
