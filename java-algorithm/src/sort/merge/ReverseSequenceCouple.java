package sort.merge;

/**
 *
 * 逆序对个数问题
 * 输入：
 * [4,5,1,3,2]
 *
 * 逆序对
 * 41,43,42
 * 51,53,52
 * 32
 *
 * 输出
 * 7
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/25
 */
public class ReverseSequenceCouple {


    public static int ReverseSequenceCoupleCount(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }

        int mergeSize = 1;
        int count = 0;
        int N = arr.length;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                // 找出左组序列 最后一个
                int M = L + mergeSize - 1;

                if (M >= N) {
                    break;
                }

                // 找出右组序列 最后一个
                int R = Math.min((M + mergeSize), N-1);
                // 对两个子序列进行合并
                count += merge(arr, L, M, R);
                // 走下一个合并组
                L = R + 1;
            }

            if (mergeSize > (N >> 1)) {
                break;
            }

            mergeSize <<= 1;
        }
        return count;
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int i = 0;

        int count = 0;

        int[] temp = new int[right - left + 1];

        int pl = left;
        int pr = mid + 1;

        // 只要两个子序列都没有越界 就进行比较
        while (pl <= mid && pr <= right) {
            // 两个数组那边小哪边先进临时数组
            if (arr[pl] <= arr[pr]) {
                temp[i++] = arr[pl++];
            } else {
                // 左序列数 较大  就计算 左序列里有多少个比右序列大的数
                count += (mid - pl + 1);
                temp[i++] = arr[pr++];
            }
        }

        // 只要有一个子序列越界了  就把另外一个序列的剩余数 塞到临时数值内
        while (pl <= mid) {
            temp[i++] = arr[pl++];
        }

        while (pr <= right) {
            temp[i++] = arr[pr++];
        }

        // 将临时的数组塞回原数组
        for (int j = 0; j < temp.length; j++) {
            arr[j + left] = temp[j];
        }


        return count;
    }


    public static void main(String[] args) {
        int[] test = generateRandomArray(10, 100);
//        test = new int []{3 ,6, 3, 0, 3, 3, 3, 1};
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + ",");
        }

        System.out.println(" ");

        System.out.println(ReverseSequenceCoupleCount(test));

        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + ",");
        }
    }


    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxSize + 1) * Math.random());
        }
        return arr;
    }
}
