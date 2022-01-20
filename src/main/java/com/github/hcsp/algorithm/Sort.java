package com.github.hcsp.algorithm;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {


        int[] array1 = new int[]{4, 8, 1, 7, 4, 0, 5, 8, 7, 5, 9, 6, 4, 0};
        //int[] array2 = new int[]{4, 8, 1, 7, 4, 0, 5, 8, 7, 5, 9, 6, 4, 0};
        int[] array2 = new int[]{9, 19, 6, 4, 6, 8, 1, 4, 8, 9, 201};


//        System.out.println(getMaxDigit(array2));


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
//        quickSort2(array);
//        mergeSort2(array);
//        insertionSort(array);
//        insertionSort(array);
//        HeapSort(array);
//        ShellSort(array);
//        bucketSort(array);
        radixSort(array);
//        System.out.println("计数：" + Arrays.toString(countingSort(array)));
//        System.out.println("计数2：" + Arrays.toString(countingSort2(array)));
        //System.out.println("array = " + Arrays.toString(mergeSort(array)));
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


//    public static void mergeSort(int[] arr) {
//        int mid = (0 + arr.length) / 2;
//
//    }

    // 归并排序

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = (0 + arr.length) / 2;

        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(leftArr), mergeSort(rightArr));
    }

    public static int[] merge(int[] leftArr, int[] rightArr) {
        int[] result = new int[leftArr.length + rightArr.length];
        int i = 0;
        while (leftArr.length > 0 && rightArr.length > 0) {
            if (leftArr[0] <= rightArr[0]) {
                result[i++] = leftArr[0];
                leftArr = Arrays.copyOfRange(leftArr, 1, leftArr.length);
            } else {
                result[i++] = rightArr[0];
                rightArr = Arrays.copyOfRange(rightArr, 1, rightArr.length);
            }
        }
        int leftLastIndex = 0;
        while (leftArr.length > 0 && leftLastIndex < leftArr.length) {
            result[i++] = leftArr[leftLastIndex++];
        }
        int rightLastIndex = 0;
        while (rightArr.length > 0 && rightLastIndex < rightArr.length) {
            result[i++] = rightArr[rightLastIndex++];
        }
        return result;
    }


    public static void mergeSort2(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int[] tempArr = new int[arr.length];
        mergeSort2(arr, 0, arr.length - 1, tempArr);
    }

    public static void mergeSort2(int[] arr, int left, int right, int[] tempArr) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort2(arr, left, mid, tempArr);
            mergeSort2(arr, mid + 1, right, tempArr);
            merge2(arr, left, right, mid, tempArr);
        }
    }


    public static void merge2(int[] arr, int left, int right, int mid, int[] tempArr) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tempArr[t++] = arr[i++];
            } else {
                tempArr[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            tempArr[t++] = arr[i++];
        }
        while (j <= right) {
            tempArr[t++] = arr[j++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = tempArr[t++];
        }
    }

    // 插入

    public static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    // 选择
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                swap(arr, i, min);
            }
        }
    }

    // 堆排序
    public static void HeapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }

    }

    public static void adjustHeap(int[] arr, int i, int arrLength) {
        int temp = arr[i];
        for (int j = 2 * i + 1; j < arrLength; j = 2 * j + 1) {
            if ((j + 1) < arrLength && arr[j] < arr[j + 1]) {
                j++;
            }
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


    // 希尔排序
    public static void ShellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j] < arr[j - gap]) {
                    swap(arr, j, j - gap);
                    j -= gap;
                }
            }

        }
    }

    // 计数排序
    public static int[] countingSort(int[] arr) {
        int initArrLength = arr.length;
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < initArrLength; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //  为赋值之前，初始值默认为 0
        int countArr[] = new int[max - min + 1];

        for (int j = 0; j < initArrLength; j++) {
            countArr[arr[j] - min]++;
        }
        // 步骤2- 为了保证稳定性
        for (int j = 1; j < countArr.length; j++) {
            countArr[j] = countArr[j] + countArr[j - 1];
        }
        //这里必须从后向前遍历，只有这样出现重复的元素，才会保持顺序的把最后面的重复元素，永远放在最右边。
        //从而保证排序的稳定性
        //如果从前向后排序，重复元素的顺序，刚好相反，所以就不是稳定的算法，但如果不关注稳定性，那么结果还是正确的

        int result[] = new int[initArrLength];
        for (int k = initArrLength - 1; k >= 0; k--) {
            int ops = arr[k] - min;
            int sumCount = countArr[ops];
            result[sumCount - 1] = arr[k];
            countArr[ops]--;
        }
        return result;
    }

    public static int[] countingSort2(int[] arr) {
        int initArrLength = arr.length;
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < initArrLength; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //  为赋值之前，初始值默认为 0
        int countArr[] = new int[max - min + 1];

        for (int j = 0; j < initArrLength; j++) {
            countArr[arr[j] - min]++;
        }

        int result[] = new int[initArrLength];
        int resultIndex = 0;
        for (int k = 0; k < countArr.length; k++) {
            while (countArr[k] > 0) {
                result[resultIndex] = k + min;
                countArr[k]--;
                resultIndex++;
            }
        }
        return result;
    }


    // 桶排序

    public static void bucketSort(int[] arr) {
        int initArrLength = arr.length;
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < initArrLength; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //桶的初始化
        int bucketSize = 5; // 设置桶的默认数量为5
        int bucketRange = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketRange][0];


        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] - min) / bucketSize;
            buckets[index] = arrAppend(buckets[index], arr[i]);
        }

        int resultIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length == 0) {
                continue;
            }
            insertionSort(bucket);
            for (int value : bucket) {
                arr[resultIndex++] = value;
            }
        }
    }

    // 基数排序

    public static void radixSort(int[] arr) {
        int mod = 10;
        int dev = 1;

        int maxDigit = getMaxDigit(arr);

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];
            for (int j = 0; j < arr.length; j++) {
                int counterIndex = (arr[j] % mod) / dev + mod;
                counter[counterIndex] = arrAppend(counter[counterIndex], arr[j]);
            }
            int ops = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[ops++] = value;
                }
            }
        }
    }

    /**
     * 获取最高位数
     */
    public static int getMaxDigit(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        int maxValue = arr[maxIndex];
        if (maxValue == 0) {
            return 1;
        }
        int length = 0;
        while (maxValue > 0) {
            maxValue = maxValue / 10;
            length++;
        }

        return length;
    }


    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private static int[] arrAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

}
