package sort.select;

import sort.SortMethod;

/**
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/26
 */
public class SelectSort {
    //简单选择排序
    public static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int j = 0;
            int temp;
            // 保存每趟找到的最小数
            int k = i;
            for (j = i + 1; j < nums.length; j++) {
                if (nums[k] > nums[j]) {
                    k = j;
                }
            }
            // 如果找到了最小数，并且不是当前待排数关键字，交换
            if (k != i) {
                temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }
//            System.out.print("选择第" + i + "趟排序：");
//            SortMethod.printArray(nums);
        }
//        SortMethod.printArray(nums);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = SortMethod.generateRandomArray(maxSize, maxValue);
            int[] arr2 = SortMethod.copyArray(arr1);
            selectSort(arr1);
            SortMethod.comparator(arr2);
            if (!SortMethod.isEqual(arr1, arr2)) {
                succeed = false;
                SortMethod.printArray(arr1);
                SortMethod.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
