package algorithm.linkedlist;

/**
 * 单链表节点
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/27
 */
public class Node {
    public int value;
    public Node next;

    public Node(int v) {
        value = v;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next+
                '}';
    }
}
