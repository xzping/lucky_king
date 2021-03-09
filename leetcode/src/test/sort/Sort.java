package test.sort;

import javax.swing.*;

public class Sort {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
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

    public static void insertionSort(int[] arr) {
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

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int left = leftIndex;
        int right = rightIndex;
        int base = arr[left]; // 基准
        // 从左右两边交替扫描，直到left=right
        while (left < right) {
            while (right > left && arr[right] >= base) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= base) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = base;
        quickSort(arr, leftIndex, left - 1);
        quickSort(arr, left + 1, rightIndex);
    }

    public static void main(String[] args) {
        int[] arr = {8, 1, 20, 23, 12, 16, 97, 66, 52};
//        Sort.bubbleSort(arr);
//        Sort.selectionSort(arr);
//        Sort.insertionSort(arr);
        Sort.quickSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
