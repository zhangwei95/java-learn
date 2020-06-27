package sort.heap;

import sort.SortMethod;

import java.util.Arrays;


/**
 * 堆排序
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/26
 */
public class Sort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 构建大根堆
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }


        // 将 大根堆的 最大值 与 大根堆最后一个元素交换  并将最后一个元素排除
        int heapSize = arr.length;
        while (heapSize > 0) {
            SortMethod.swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) { // 下方还有孩子的时候
            // 两个孩子中，谁的值大，把下标给largest
            // 1）只有左孩子，left -> largest
            // 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
            // 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和较大的孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            SortMethod.swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = SortMethod.generateRandomArray(maxSize, maxValue);
            int[] arr2 = SortMethod.copyArray(arr1);
            heapSort(arr1);
            Arrays.sort(arr2);
            if (!SortMethod.isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
