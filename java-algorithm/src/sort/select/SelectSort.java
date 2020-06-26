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
            System.out.print("选择第" + i + "趟排序：");
            SortMethod.printArray(nums);
        }
    }
}
