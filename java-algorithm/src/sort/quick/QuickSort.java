package sort.quick;

import sort.SortMethod;

/**
 * 快排
 *
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/25
 */
public class QuickSort {

    /**
     * 将 L 到 R 上的数分区  左边是小于 arr[R]的数  右边是大于arr[R]的数
     * @param arr 需要排的数组
     * @param L 左边界
     * @param R 右边界
     * @return
     */
    public static int partition(int[] arr ,int L,int R){
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;

    }


    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // L..R partition arr[R]  [   <=arr[R]   arr[R]    >arr[R]  ]
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }



    /**
     * 荷兰国旗问题
     *
     * @param arr 给定数组 arr
     * @param L   划分范围左
     * @param R   划分范围右
     *            R = arr.length-1；
     *            以arr[R] 为界
     *            划分成3个区域
     *             L       |   M        |   R
     *            <arr[R] |   arr[R]   |   >arr[R]
     *                   |            |
     *            return 左右 边界
     */
    public static int[] netherLandsFlag(int[] arr, int L, int R) {
        if (arr == null || L > R) {
            return new int[]{-1, -1};
        }

        if (L == R) {
            return new int[]{L, R};
        }
        // 左边界
        int less = L - 1;
        // 右边界  开始跳过 arr[R] 从arr[R-1] 开始交换
        int more = R;
        int i = L;

        int limit = arr[R];

        while (i < more) {
            if (arr[i] < limit) {
                // 当前值 小于 基准值时   左边界 后推
                swap(arr, i++, ++less);
            } else if (arr[i] == limit) {
                i++;
            } else {
                // 当前值 大于 基准值时 交换当前值与右边界值 右边界 前推
                swap(arr, i, --more);
            }
        }

        // 将 arr[R]的值 放到 右边界区的第一个
        swap(arr, R, more);

        // arr[R]的区域   L...Less < arr[R]   more+1...R >arr[R]
        return new int[]{less + 1, more};
    }


    /**
     * 较 quickSort1  优化了 与 arr[R] 重复值的  多余比较
     * @param arr
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void  process2(int[] arr ,int L , int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherLandsFlag(arr, L, R);

        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void  process3(int[] arr ,int L , int R) {
        if (L >= R) {
            return;
        }
        // 随机抽取一个数 作为基准数
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherLandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }


    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    public static void main(String[] args) {
        int testTime = 1;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0;i<testTime;i++){
            int[] arr1 = SortMethod.generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "Nice!" : "Oops!");
    }


    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null) {
            return false;
        }

        if (arr1 != null && arr2 == null) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
