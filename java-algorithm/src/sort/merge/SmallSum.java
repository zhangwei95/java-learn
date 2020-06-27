package sort.merge;

import sort.SortMethod;

/**
 *
 * @Describe 小和问题
 *
 * 输入
 * 1 3 5 4 2
 *
 * 比 1 小的 0个
 * 比 3 小的 1个1
 * 比 5 小的 1个1 1个3
 * 比 4 小的 1个1 1个3
 * 比 2 小的 1个1
 *
 * 结果 1+1+3+1+3+1=10
 * @author zhangwei
 * @date 2020/6/25
 */
public class SmallSum {

    /**
     *  根据归并排序改造
     *  每一次合并 进行比较的时候 右序列 > 左序列
     *  计算 大于左序列当前比较数的个数  并求和  当前小数 * 右序列剩余个数
     *
     *  example
     *  [1,2,6]   [1,4,5,6]
     *     ↑         ↑
     *  当次比较的小数和  2 * (3 - 1 + 1)
     *  Number * ( right - rightPosition + 1)
     *  @param arr
     */
    public static int smallSum(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }

        int mergeSize = 1;
        int sum = 0;
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
                sum += merge(arr, L, M, R);
                // 走下一个合并组
                L = R + 1;
            }

            if (mergeSize > (N >> 1)) {
                break;
            }

            mergeSize <<= 1;
        }
        return sum;
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int i = 0;

        int sum = 0;

        int[] temp = new int[right - left + 1];

        int pl = left;
        int pr = mid + 1;

        // 只要两个子序列都没有越界 就进行比较
        while (pl <= mid && pr <= right) {
            // 两个数组那边小哪边先进临时数组
            if (arr[pl] < arr[pr]) {
                // 右序列数 较大  就计算 右序列里有多少个比左序列大的数
                sum += arr[pl] * (right - pr + 1);
                temp[i++] = arr[pl++];
            } else {
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


        return sum;
    }


    public static void main(String[] args) {
        int[] test = SortMethod.generateRandomArray(10, 100);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println(" ");
        System.out.println(smallSum(test));
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
    }

}
