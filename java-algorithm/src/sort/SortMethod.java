package sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author zw
 */
public class SortMethod {
    static void printArray(int[] source) {
        for (int i = 0; i < source.length; i++) {
            System.out.print("\t" + source[i]);
        }
        System.out.println();

    }

    static void printArray(Date[] source) {
        for (int i = 0; i < source.length; i++) {
            System.out.print("\t" + source[i]);
        }
        System.out.println();

    }

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
            System.out.print("直接插入第" + i + "趟排序：");
            printArray(nums);
        }
    }

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
            printArray(nums);
        }
    }

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
            printArray(nums);
            if (flag == 0) {
                return;
            }
        }
    }

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
            printArray(nums);
        }
    }

    //快速排序
    public static void quickSort(int[] nums, int L, int r) {
        if (L < r) {
            int i = L;//序列起始位置
            int j = r;//序列终止位置
            int x = nums[L];//取起始位置作为基准数，保存基准数
            while (i < j) {
                while (i < j && nums[j] >= x) {//从右至左遍历序列，直到遍历到小于基准数的数
                    j--;
                }
                if (i < j) {
                    nums[i++] = nums[j];//将遍历到的小于基准数的数放到基准数的位置
                }
                while (i < j && nums[i] <= x) {//从左至右遍历序列，直到遍历到大于基准数的数
                    i++;
                }
                if (i < j) {
                    nums[j--] = nums[i];//将遍历到的大于基准数的数放到之前替换基准数的数的位置
                }
            }//遍历完 i=j，i的左边全小于等于基准数，i的右边全是大于等于基准数
            nums[i] = x;
            //递归将基准数左右两侧再次排序，最终得到有序数列
            quickSort(nums, L, i - 1);
            quickSort(nums, i + 1, r);
        }
    }

    //快速排序 时间
    public static void quickSort(Date[] dates, int L, int r) {
        if (L < r) {
            int i = L;//序列起始位置
            int j = r;//序列终止位置
            Date x = dates[L];//取起始位置作为基准数，保存基准数
            while (i < j) {
                while (i < j && dates[j].after(x)) {//从右至左遍历序列，直到遍历到小于基准数的数
                    j--;
                }
                if (i < j) {
                    dates[i++] = dates[j];//将遍历到的小于基准数的数放到基准数的位置
                }
                while (i < j && dates[i].before(x)) {//从左至右遍历序列，直到遍历到大于基准数的数
                    i++;
                }
                if (i < j) {
                    dates[j--] = dates[i];//将遍历到的大于基准数的数放到之前替换基准数的数的位置
                }
            }//遍历完 i=j，i的左边全小于等于基准数，i的右边全是大于等于基准数
            dates[i] = x;
            //递归将基准数左右两侧再次排序，最终得到有序数列
            quickSort(dates, L, i - 1);
            quickSort(dates, i + 1, r);
        }
    }
    //归并排序

    //希尔排序

    //基数排序

    //堆排序

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		int[] nums=new int[]{4,5,8,3,2,1,6,9,7,9,7,6};
//		quickSort(nums, 0, nums.length-1);
//		SortMethod me=new SortMethod();
//		for (int i : nums) {
//			System.out.print(i+" ");
//		}
        String time1 = "2018-1-1";
        String time2 = "2018-1-2";
        String time3 = "2018-1-3";
        String time4 = "2018-1-4";
        String time5 = "2018-1-5";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null, d2 = null, d3 = null, d4 = null, d5 = null;
        try {
            d1 = simpleDateFormat.parse(time1);
            d2 = simpleDateFormat.parse(time2);
            d3 = simpleDateFormat.parse(time3);
            d4 = simpleDateFormat.parse(time4);
            d5 = simpleDateFormat.parse(time5);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        time1 = simpleDateFormat.format(d1);
        Date[] dates = new Date[]{d2, d5, d1, d3, d4};
        printArray(dates);
        quickSort(dates, 0, 4);
        printArray(dates);


    }
}

