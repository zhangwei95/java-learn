package algorithm.linkedlist;

/**
 * 双链表节点
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/27
 */
public class DoubleNode {
    public int value;
    public DoubleNode next;
    public DoubleNode pre;

    public DoubleNode(int v) {
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
