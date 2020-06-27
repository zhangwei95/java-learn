package sort.merge;

import sort.SortMethod;

import java.util.logging.Logger;

/**
 * 归并排序
 * <p>
 * 归并思想：
 * 1、将两个有序的子序列 合并 得到一个有序的序列
 * 2、子序列 的 有序性 由 1 保证
 *
 * @author zw
 */
public class MergeSort {
    /**
     * 递归归并
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void merSort(int[] arr, int left, int right) {
        if (arr == null || left >= right) {
            return;
        }
        right = Math.min(right, arr.length - 1);

        int mid = left + ((right - left) >> 1);

        // 左边归并排序，使得左子序列有序
        merSort(arr, left, mid);

        // 右边归并排序，使得右子序列有序
        merSort(arr, mid + 1, right);

        // 合并两个子序列
        merge(arr, left, mid, right);
    }

    /**
     * 非递归实现
     *  @param arr
     * @param left
     * @param right
     */
    public static void merSort2(int[] arr, int left, int right) {

        if (arr == null || left >= right) {
            return;
        }

        int mergeSize = 1;
        int N = right + 1;
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
                merge(arr, L, M, R);
                // 走下一个合并组
                L = R + 1;
            }

            if (mergeSize > (N >> 1)) {
                break;
            }

            mergeSize <<= 1;
        }

    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i = 0;
        int[] temp = new int[right - left + 1];

        int pl = left;
        int pr = mid + 1;

        // 只要两个子序列都没有越界 就进行比较
        while (pl <= mid && pr <= right) {
            // 两个数组那边大哪边先进临时数组
            if (arr[pl] < arr[pr]) {
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

    }

    public static void main(String[] args) {
        int[] test = SortMethod.generateRandomArray(100, 100);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
        System.out.println(" ");
        merSort2(test, 0, test.length - 1);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
    }

}
