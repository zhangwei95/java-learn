package sort.heap;

import javafx.scene.web.WebHistory;

/**
 * 大根堆
 *
 * @author zhangwei
 * @Describe 类描述
 * @date 2020/6/26
 */
public class MaxHeap {

    private int[] heap;

    private int limit;

    private int heapSize;

    public MaxHeap(int limit) {
        heap = new int[limit];
        this.limit = limit;
        heapSize = 0;
    }

    /**
     * 扔出 最大数
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("heap empty");
        }
        int top = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return top;
    }

    /**
     * 入堆
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("heap full");
        }
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);
    }

    /**
     * 大数上浮 维持大根堆
     *
     * @param arr
     * @param index 当前上浮的数下标
     */
    public void heapInsert(int[] arr, int index) {

        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }

    }


    /**
     * 小数下沉 维持大根堆
     *
     * @param arr
     * @param index    小数下标
     * @param heapSize 当前堆大小
     */
    public void heapify(int[] arr, int index, int heapSize) {
        int left = (index << 1) + 1;
        while (left < heapSize){
            int right = left + 1;
            // 与较大的孩子交换
            int largest = right < heapSize && arr[right] > arr[left] ? right : left;

            largest = arr[largest] > arr[index]? largest : index;

            if (largest == index){
                break;
            }
            swap(arr,index,largest);
            index = largest;
            left = (index << 1) + 1;
        }

    }

    /**
     * 交换
     *
     * @param arr
     * @param index1
     * @param index2
     */
    public void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private boolean isEmpty() {
        if (heapSize == 0) {
            return true;
        }
        return false;
    }

    private boolean isFull() {
        if (heapSize == limit) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(32);

        for (int i = 0;i < 32;i++){
            int value = (int) (Math.random() * 1000);
            maxHeap.push(value);
        }

        while (!maxHeap.isEmpty()){
            System.out.print(maxHeap.pop()+",");
        }
    }
}
