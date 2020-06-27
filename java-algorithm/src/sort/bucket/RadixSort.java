package sort.bucket;

import sort.SortMethod;

/**
 * 基数排序
 *
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/26
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int maxBits = getMaxBits(arr);
        int radix = 10;
        int N = arr.length;
        // help
        int[] help = new int[N];

        // 有几位便利几次
        for (int digit = 1; digit <= maxBits; digit++) {

            //  十进制 radix = 10   0...9
            int[] count = new int[radix];

            // 给对应位置计数  ps:  103   d = 1   count[3]++
            for (int i = 0; i < N; i++) {
                int d = getDigit(arr[i], digit);
                count[d]++;
            }

            // 求 当前位 <= 当前位置数的总和
            for (int i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }

            // 按当前位的大小顺序 进桶顺序排序  ps: count[2] = 10  代表的 当前位 <=2 的一共有10个 所有当前数应该在下标9号位值
            //从后往前遍历 相同的数 都是后进桶的先出来  保证了算法的稳定性
            for (int i = N - 1; i >= 0; i--) {
                int d = getDigit(arr[i], digit);
                help[count[d] - 1] = arr[i];
                count[d]--;
            }

            // 覆盖原始数组
            for (int i = 0; i < N; i++) {
                arr[i] = help[i];
            }
        }


    }

    /**
     * 获取对应位 的数值
     *
     * @param number
     * @param digit  位  1 个 2 十 3 百 ....
     * @return
     */
    public static int getDigit(int number, int digit) {
        return (number / (int) Math.pow(10, digit - 1)) % 10;
    }


    public static int getMaxBits(int[] arr) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }

        int bit = 0;
        while (max > 0) {
            bit++;
            max /= 10;
        }
        return bit;

    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = SortMethod.generateRandomArray(maxSize, maxValue);
            int[] arr2 = SortMethod.copyArray(arr1);
            radixSort(arr1);
            SortMethod.comparator(arr2);
            if (!SortMethod.isEqual(arr1, arr2)) {
                succeed = false;
                SortMethod.printArray(arr1);
                SortMethod.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = SortMethod.generateRandomArray(maxSize, maxValue);
        SortMethod.printArray(arr);
        radixSort(arr);
        SortMethod.printArray(arr);


    }

}
