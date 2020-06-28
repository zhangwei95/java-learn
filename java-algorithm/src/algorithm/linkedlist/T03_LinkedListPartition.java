package algorithm.linkedlist;

/**
 * 链表分区
 * 按 key值分区   <key   =key   >key
 *
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/27
 */
public class T03_LinkedListPartition {

    // 空间复杂度 O(N)
    public static Node partition(Node head, int key) {
        // 转化为数组的荷兰国旗问题
        if (head == null || head.next == null) {
            return head;
        }
        int count = 1;
        Node cur = head;
        while (cur.next != null) {
            count++;
            cur = cur.next;
        }
        Node[] arr = new Node[count];
        cur = head;
        count = 0;
        while (cur != null) {
            arr[count++] = cur;
            cur = cur.next;
        }
        arrPartition(arr, key);
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }
        arr[arr.length - 1].next = null;
        return arr[0];
    }

    public static void arrPartition(Node[] arr, int key) {
        int index = 0;
        int less = -1;
        int more = arr.length;
        while (index != more) {
            if (arr[index].value < key) {
                swap(arr, index++, ++less);
            } else if (arr[index].value == key) {
                index++;
            } else {
                swap(arr, index, --more);
            }
        }
    }


    // 空间复杂度 O(1)
    public static Node partition1(Node head ,int key){
        Node sh = null; // 小于区 头节点
        Node st = null; // 小于区 尾节点
        Node eh = null; // 等于区 头节点
        Node et = null; // 等于区 尾节点
        Node bh = null; // 大于区 头节点
        Node bt = null; // 大于区 尾节点
        while (head != null){
            // 保存下一节点
            Node next = head.next;
            head.next = null;
            if (head.value == key){
                 // 当前节点应位于等于区
                if (eh == null){
                    eh = head;
                    et = head;
                } else {
                    et.next = head;
                    et = head;
                }
            } else if ( head.value < key){
                // 当前节点应位于小于区
                if (sh == null){
                    sh = head;
                    st = head;
                } else {
                    st.next = head;
                    st = head;
                }
            } else {
                // 当前节点应位于大于区
                if (bh == null){
                    bh = head;
                    bt = head;
                } else {
                    bt.next = head;
                    bt = head;
                }
            }
            head = next;
        }
        // 没有小于区
        if (st != null){
            st.next = eh;
            // 没有等于区
            et = et == null ? st : et;
        }
        // 没有等于区
        if (et != null){
            et.next = bh;
        }
        return sh != null ? sh :(eh != null? eh : bh);
    }


    public static void swap(Node[] arr, int p1, int p2) {
        Node temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }


    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        System.out.println(head1.toString());
        // head1 = listPartition1(head1, 4);
        head1 = partition(head1, 5);
        System.out.println(head1.toString());

    }
}
