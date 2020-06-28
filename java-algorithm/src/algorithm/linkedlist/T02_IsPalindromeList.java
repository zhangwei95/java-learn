package algorithm.linkedlist;

import java.util.Stack;

/**
 * 是否回文链表
 *
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/27
 */
public class T02_IsPalindromeList {

    /**
     * 使用额外空间 O（N）
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeList(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> help = new Stack<>();
        help.push(head);
        Node cur = head;
        // 将所有 节点 按顺序压栈
        while (cur.next != null) {
            help.push(cur.next);
            cur = cur.next;
        }
        cur = head;
        // 将栈弹出 为原来链表的逆序链  比较一致则为回文链表
        while (!help.empty()) {
            Node pop = help.pop();
            if (pop.value != cur.value) {
                return false;
            }
            cur = cur.next;
        }

        return true;
    }

    /**
     * 使用额外空间 O（1）
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeList1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head; // 这里用作慢指针  快速找到链表中点位置
        Node n2 = head.next.next; // 这里用作快指针
        // n2
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }

        n2 = n1.next;
        n1.next = null; // 中点 指向 null
        //             n1 n2
        // ps 0->1->2->3->3->2->1->0->null
        // n1 作为 pre指针 n3 作为 next指针 进行 中点后链表 逆序
        Node n3 = null;
        // 将 中点之后的所有节点指针反转
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        //            null
        //             ↑
        // ps 0->1->2->3<-3<-2<-1<-0
        // 从两端 遍及 比较
        n3 = n1; // 最后节点  逆序后的头节点
        n2 = head; // 原链表头节点
        boolean result = true;
        // 两个反向链表  往中点靠 比对每个节点值 当到达中点时 都没有出现值不一致的节点 则为回文链表
        while (n1.next != null && n2.next != null) {
            if (n1.value != n2.value) {
                result = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        n1 = n3;
        Node pre = null;
        Node next = null;
        // 恢复之前逆序的部分
        while (n1 != null) {
            next = n1.next;
            n1.next = pre;
            pre = n1;
            n1 = next;
        }

        return result;
    }

    public static void main(String[] args) {
        Node one = null;
        one = new Node(0);
        one.next = new Node(1);
        one.next.next = new Node(2);
        one.next.next.next = new Node(3);
        one.next.next.next.next = new Node(3);
        one.next.next.next.next.next = new Node(2);
        one.next.next.next.next.next.next = new Node(1);
        one.next.next.next.next.next.next.next = new Node(0);
        Node two = null;
        two = new Node(0);
        two.next = new Node(1);
        two.next.next = new Node(2);
        two.next.next.next = new Node(3);
        two.next.next.next.next = new Node(3);
        two.next.next.next.next.next = new Node(2);
        two.next.next.next.next.next.next = new Node(1);
        two.next.next.next.next.next.next.next = new Node(0);
        two.next.next.next.next.next.next.next.next = new Node(8);
        System.out.println(one);
        System.out.println(isPalindromeList1(one));
        System.out.println(two);
        System.out.println(isPalindromeList1(two));
    }

}
