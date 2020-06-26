package sort.insert;

import sort.SortMethod;

/**
 * 二分插入排序
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/26
 */
public class BinaryInsertSort {

    // 二分插入排序
    public static void binaryInsertSort(int[] nums) {
        // 排序n-1趟
        for (int i = 1; i < nums.length; i++) {
            // 待排序关键字>待排序前一关键字，说明已经有序，反正则要排序
            if (nums[i] < nums[i - 1]) {
                int temp = nums[i];
                int left = 0;
                int right = i - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    // int mid=(left+right)/2; //溢出问题待考虑
                    // left左边的都是比待排序关键字小的
                    if (nums[mid] < temp) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                // 将left起到待排关键字的右边的序列全部向右移动一位
                for (int j = i; j > left; j--) {
                    nums[j] = nums[j - 1];
                }
                // 插入有序数列
                nums[left] = temp;
            }
            System.out.print("二分插入第" + i + "趟排序：");
            SortMethod.printArray(nums);
        }
    }
}
