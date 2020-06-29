package sort.bubble;

import sort.SortMethod;

/**
 * 冒泡排序
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/26
 */
public class BubbleSort {

    //冒泡排序
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = false; // 是否交换 没交换待办已经排好序了
            for (int j = 0; j < nums.length - 1 - i; j++) {
                int temp = nums[j];
                if (nums[j] > nums[j + 1]) {
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = true;
                }
            }
//            System.out.print("冒泡第" + i + "趟排序：");
//            SortMethod.printArray(nums);
            if (!flag) {
                return;
            }
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
            bubbleSort(arr1);
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
