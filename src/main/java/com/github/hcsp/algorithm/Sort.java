package com.github.hcsp.algorithm;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] array1 = new int[]{4, 8, 1, 7, 4, 0, 5, 8, 7, 5, 9, 6, 4, 0};
        //int[] array2 = new int[]{4, 8, 1, 7, 4, 0, 5, 8, 7, 5, 9, 6, 4, 0};
        int[] array2 = new int[]{9, 9, 6, 4, 6, 8, 1, 4, 8, 9};
        sort1(array1);
        sort2(array2);

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }

    // 排序算法1
    // 按照从小到大排序
    public static void sort1(int[] array) {
        bubbleSort(array);
    }

    // 排序算法2
    // 按照从小到大排序
    public static void sort2(int[] array) {
        //quickSort(array);
        quickSort2(array);
    }


    // 冒泡排序
    public static void bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    // 快速排序-单边扫描
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);

    }

    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }


    // 快速排序-双边扫描


    public static void quickSort2(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        quickSort2(arr, 0, arr.length - 1);
    }

    public static int[] quickSort2(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = quickSortPartition(arr, left, right);
            quickSort2(arr, left, partitionIndex - 1);
            quickSort2(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    public static int quickSortPartition(int[] arr, int left, int right) {
        int pivotValue = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivotValue) {
                right--;
            }
            if (left < right) {
                arr[left++] = arr[right];
            }

            while (left < right && arr[left] <= pivotValue) {
                left++;
            }
            if (left < right) {
                arr[right--] = arr[left];
            }

        }
        arr[left] = pivotValue;
        return left;
    }


    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
