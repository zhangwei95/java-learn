package sort;

/**
 * 归并排序
 * @author zw
 */
public class MergeSort {
    public static void merSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            merSort(arr, left, mid);//左边归并排序，使得左子序列有序
            merSort(arr, mid + 1, right);//右边归并排序，使得右子序列有序
            merge(arr, left, mid, right);//合并两个子序列
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        //如果左边大于右边
        while (i <= mid && j <= right) {
            //两个数组那边大哪边先进临时数组
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        //将临时的数组塞回原数组
        for (int l = 0; l < temp.length; l++) {
            arr[l + left] = temp[l];
        }

    }

    public static void main(String[] args) {
        int[] test = {9, 2, 6, 3, 5, 7, 10, 11, 12};
        merSort(test, 0, test.length - 1);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
    }

}
