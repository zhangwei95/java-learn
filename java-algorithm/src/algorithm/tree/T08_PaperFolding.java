package algorithm.tree;

/**
 * @Description: 描述
 * @Author: zhangwei
 * @Date: 2020/7/7 16:19
 */
public class T08_PaperFolding {

    // 1： 1凹
    // 2： 2凹 1凹 2凸
    // 3:  3凹 2凹 3凸 1凹 3凹 2凸 3凸
    public static void printAllFolds(int N) {
        process(1, N, true);
    }

    /**
     *
     * @param i 当前第i层
     * @param N 最多N层
     * @param down 是否是凹
     */
    public static void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.println(down ? i + "凹" : i + "凸");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        printAllFolds(4);
    }

}
