package main.sort;

public class SortDemo {
    /**
     * 冒泡排序
     * 时间复杂度：最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     */
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)
     */
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * 插入排序
     * 最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     */
    public void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int current = arr[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    // 快速排序

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 10, 17, 22, 15, 5, 9};
        SortDemo demo = new SortDemo();
//        demo.bubbleSort(arr);
//        demo.selectionSort(arr);
        demo.insertionSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
