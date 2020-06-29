package sort.insert;

import sort.SortMethod;

/**
 * 直接插入排序
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/26
 */
public class DirectInsertSort {

    // 直接插入排序
    public static void directInsertSort(int[] nums) {
        int i, j;
        int temp;
        // 排序n-1趟
        for (i = 1; i < nums.length; i++) {
            // 待排数保存
            temp = nums[i];
            // 每趟最多比较次数 i
            for (j = i - 1; j >= 0 && nums[j] > temp; j--) {
                // 如果扫描到的数大于待排数，则向后移一位
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = temp;
//            System.out.print("直接插入第" + i + "趟排序：");
//            SortMethod.printArray(nums);
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = SortMethod.generateRandomArray(maxSize, maxValue);
            int[] arr2 = SortMethod.copyArray(arr1);
            directInsertSort(arr1);
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
