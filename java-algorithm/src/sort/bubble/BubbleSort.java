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
            int flag = 0;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                int temp = nums[j];
                if (nums[j] > nums[j + 1]) {
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = 1;
                }
            }
            System.out.print("冒泡第" + i + "趟排序：");
            SortMethod.printArray(nums);
            if (flag == 0) {
                return;
            }
        }
    }

}
