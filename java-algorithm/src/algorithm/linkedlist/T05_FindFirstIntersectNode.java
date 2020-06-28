package algorithm.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 找链表相交
 * 两个链表 都可能 有环 或 无环
 *
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/27
 */
public class T05_FindFirstIntersectNode {


    public static void main(String[] args) {
        Node head1 = new Node(0);

        Node head2 = new Node(0);

        Node intersectNode = getIntersectNode(head1, head2);


    }

    public static Node getIntersectNode(Node head1, Node head2) {
        Node loopNode1 = getLoopNode1(head1);

        Node loopNode2 = getLoopNode1(head2);

        // 分析
        // 情况1 两个 无环的链表 终点不同  没有交点  终点相同 有交点  比较链表长度
        // 情况2 一个有环 一个无环   不可能相交
        // 情况3 两个有环的相交  环外相交  环内相交
        // 情况4 两个有环 不相交
        if (loopNode1 == null && loopNode2 == null) {
            return bothNoLoop(head1, head2);
        } else if (loopNode1 != null && loopNode2 != null) {
            return bothLoop(head1,loopNode1, head2,loopNode2);
        } else {
            return null;
        }
    }

    // 都没有环   Y型
    public static Node bothNoLoop(Node head1, Node head2) {
        // 实现逻辑  找出 两个链表的最后一个节点 是否为同一个节点 是 那么相交
        // 相交的情况 计算链表长度差 找出长链表 先走长度差 然后 同时往后走 必定会出现相同节点
        Node tail1 = head1;
        Node tail2 = head2;
        int count1 = 1;
        int count2 = 1;
        while (tail1.next != null) {
            count1++;
            tail1 = tail1.next;
        }

        while (tail2.next != null) {
            count2++;
            tail2 = tail2.next;
        }
        if (tail1 == tail2) {
            // 相交 计算差值
            int abs = Math.abs(count1 - count2);
            // 设置 tail1为长链表头节点
            tail1 = count1 - count2 >= 0 ? head1 : head2;
            tail2 = tail1 == head1 ? head2 : head1;
            // 长链表 先移动差值个节点
            while (abs > 0) {
                abs--;
                tail1 = tail1.next;
            }
            // 同时出发 必定会相交 返回第一次相交的节点
            while (tail1 != null) {
                if (tail1 == tail2) {
                    return tail1;
                } else {
                    tail1 = tail1.next;
                    tail2 = tail2.next;
                }
            }
        }
        return null;
    }

    // 都有环
    public static Node bothLoop(Node head1, Node loopNode1, Node head2, Node loopNode2) {
        // 情况 1 不相交 6 6
        // 情况 2 相交 环外 >--0
        // 情况 3 相交 环上 ==O
        if (loopNode1 == loopNode2) {
            // 情况2  与 无环相交类似
            Node tail1 = head1;
            Node tail2 = head2;
            int count1 = 1;
            int count2 = 1;

            while (tail1.next != loopNode1) {
                count1++;
                tail1 = tail1.next;
            }

            while (tail2.next != loopNode1) {
                count2++;
                tail2 = tail2.next;
            }

            if (tail1 == tail2) {
                // 相交 计算差值
                int abs = Math.abs(count1 - count2);
                // 设置 tail1为长链表头节点
                if (count1 >= count2) {
                    tail1 = head1;
                    tail2 = head2;
                } else {
                    tail1 = head2;
                    tail2 = head1;
                }
                // 长链表 先移动差值个节点
                while (abs > 0) {
                    abs--;
                    tail1 = tail1.next;
                }
                // 同时出发 必定会相交 返回第一次相交的节点
                while (tail1 != loopNode1) {
                    if (tail1 == tail2) {
                        return tail1;
                    } else {
                        tail1 = tail1.next;
                        tail2 = tail2.next;
                    }
                }
            }
            return loopNode1;
        } else {
            // 情况1 3
            Node temp = loopNode1.next;
            // 在环上找 另一个入环点  找到即相交 找不到即不象交
            while (temp != loopNode1) {
                if (temp == loopNode2) {
                    return loopNode2;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    /**
     * 节点是否有环 返回入环点
     *
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Set<Node> help = new HashSet<>();
        Node cur = head;
        while (cur != null) {
            if (help.contains(cur)) {
                return cur;
            } else {
                help.add(cur);
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * 节点是否有环 返回入环点
     *
     * @param head
     * @return
     */
    public static Node getLoopNode1(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;

        slow = slow.next;
        fast = fast.next.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;

        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
