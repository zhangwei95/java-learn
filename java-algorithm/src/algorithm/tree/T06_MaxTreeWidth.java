package algorithm.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: 树最大宽度
 * @Author: zhangwei
 * @Date: 2020/7/6 16:13
 */
public class T06_MaxTreeWidth {


    /**
     * 获取树的最大宽度
     * @param head
     * @return
     */
    public static int getMaxTreeWidthWithMap(Node head){
        if (head == null){
            return 0;
        }

        // 层级map
        HashMap<Node,Integer> levelMap = new HashMap<>(64);

        levelMap.put(head,1);

        Queue<Node> queue = new LinkedList<>();

        queue.add(head);

        // 当前层
        int currentLevel = 1;
        // 当前层节点数
        int currentNodes = 0;
        //最大节点数
        int maxWidth = 0;

        while (!queue.isEmpty()){
            Node poll = queue.poll();
            int curLevel = levelMap.get(poll);
            if (poll.left != null){
                levelMap.put(poll.left,curLevel + 1);
                queue.add(poll.left);
            }

            if (poll.right != null){
                levelMap.put(poll.right,curLevel + 1);
                queue.add(poll.right);
            }
            if (currentLevel == curLevel){
                currentNodes++;
            } else {
                maxWidth = Math.max(maxWidth,currentNodes);
                currentNodes = 1;
                currentLevel++;
            }

        }
        // 最后一层与倒数第二层PK 大小
        maxWidth = Math.max(maxWidth,currentNodes);
        return maxWidth;

    }


    /**
     * 无Map 获取最大宽度
     * @return
     */
    public static int getMaxWidthWithOutMap(Node node){
        if (node == null){
            return 0;
        }

        Queue<Node> nodeQueue = new LinkedList<>();

        nodeQueue.add(node);

        Node curEnd = node;

        Node nextEnd = null;

        int maxWidth = 0;

        int curLevelNodes = 0; // 当前层的节点数

        while (!nodeQueue.isEmpty()){
            Node poll = nodeQueue.poll();

            if (poll.left != null){
                nodeQueue.add(poll.left);
                nextEnd = poll.left;
            }

            if (poll.right != null){
                nodeQueue.add(poll.right);
                nextEnd = poll.right;
            }
            curLevelNodes++;
            if (curEnd == poll){
                maxWidth = Math.max(maxWidth,curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }

        }

        return maxWidth;
    }


    public static void main(String[] args) {
        for (int i = 0; i< 100; i++){
            Node node = T00_GenerateTree.generateRandomTree(8, 10000);
            int maxWidthWithOutMap = getMaxWidthWithOutMap(node);
            int maxTreeWidthWithMap = getMaxTreeWidthWithMap(node);
            if (maxTreeWidthWithMap != maxWidthWithOutMap) {
                System.out.println(maxTreeWidthWithMap);
                System.out.println(maxWidthWithOutMap);
                System.out.println("Oops!");
            }

        }



    }



}
