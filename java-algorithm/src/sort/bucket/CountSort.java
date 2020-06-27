package sort.bucket;

import sort.SortMethod;

import java.util.Arrays;

/**
 * 计数排序
 * 思想：拿一个长度等于 样本数据 最大值的额外空间 来计每个样本的出现次数
 * 优点：时间复杂度 O(N)
 * 缺点：样本数值 过大时  浪费空间
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/26
 */
public class CountSort {


    // only for 样本数 小
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }
        /**
         * 桶数量优化  取差值
         */
        int[] bucket = new int[max - min + 1];

        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]-min]++;
        }
        int j = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[j++] = i + min;
            }
        }

    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 200;
        int testTime = 10000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = SortMethod.generateRandomArray(maxSize, maxValue);
            int[] arr1 = SortMethod.copyArray(arr);
            countSort(arr);
            Arrays.sort(arr1);
            succeed = SortMethod.isEqual(arr, arr1);
            if (!succeed){
                SortMethod.printArray(arr);
                SortMethod.printArray(arr1);
            }
        }
        System.out.println(succeed ? "Nice!" : "Fuck Me");
    }


}
