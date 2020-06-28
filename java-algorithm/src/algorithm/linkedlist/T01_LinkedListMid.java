package algorithm.linkedlist;

/**
 *
 * 链表找中点
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/27
 */
public class T01_LinkedListMid {

    /**
     * 找中节点 (奇数)或 中前节点（偶数）
     * @param head
     * @return
     */
    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next ==null ||head.next.next==null){
            return head;
        }
        Node fast = head;
        Node slow = head;
        slow = slow.next;
        fast = fast.next.next;

        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 找中节点 (奇数)或 中后节点（偶数）
     * @param head
     * @return
     */
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next ==null ||head.next.next==null){
            return head;
        }
        Node fast = head;
        Node slow = head;
        slow = slow.next;
        fast = fast.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /**
     * 找中节点 (奇数)或 中前节点（偶数）前一节点
     * @param head
     * @return
     */
    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next ==null ||head.next.next==null){
            return head;
        }
        Node fast = head;
        Node slow = head;
        fast = fast.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 找中节点 (奇数)或 中后节点（偶数）前一节点
     * @param head
     * @return
     */
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next ==null ||head.next.next==null){
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        Node first = new Node(1);
        Node node2 = new Node(2);
        first.next = node2;
        Node node3 = new Node(3);
        node2.next = node3;
        Node node4 = new Node(4);
        node3.next = node4;
        Node node5 = new Node(5);
        node4.next = node5;
        Node node6 = new Node(6);
        node5.next = node6;
//        node6.next = new Node(7);
        Node node = midOrDownMidPreNode(first);
        System.out.print(node.toString());

    }


}
