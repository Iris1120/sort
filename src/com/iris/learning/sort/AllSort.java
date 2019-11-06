package com.iris.learning.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @Author:  copy
 * @Description: https://blog.csdn.net/weixin_38896998/article/details/84430929
 * @Date: 2019/11/6
 */
public class AllSort {

    private static int[] array = {-8, 9, 3, -7, 6, 0, -8, 9, -6, 0, 7, -2};
    private static Integer[] array1 = {18, 93, 43, 7, 346, 0, 2458, 9, 623, 0, 72, 82};

    public static void main(String[] args) {
        int[] arrayBubble = array.clone();
        bubbleSort(arrayBubble);
        System.out.println(Arrays.toString(arrayBubble) + "    bubbleSort");

        int[] arraySelection = array.clone();
        selectionSort(arraySelection);
        System.out.println(Arrays.toString(arraySelection) + "    selectionSort");

        int[] arrayInsert = array.clone();
        insertSort(arrayInsert);
        System.out.println(Arrays.toString(arrayInsert) + "    insertSort");

        int[] arrayShell = array.clone();
        shellSort(arrayShell);
        System.out.println(Arrays.toString(arrayShell) + "    shellSort");

        int[] arrayMerge = array.clone();
        System.out.println(Arrays.toString(mergeSort(arrayMerge)) + "    mergeSort");

        int[] arrayQuick = array.clone();
        quickSort(arrayQuick, 0, array.length - 1);
        System.out.println(Arrays.toString(arrayQuick) + "    quickSort");

        int[] arrayHeap = array.clone();
        heapSort(arrayHeap);
        System.out.println(Arrays.toString(arrayHeap) + "    heapSort");

        int[] arrayCount = array.clone();
        countSort(arrayCount);
        System.out.println(Arrays.toString(arrayCount) + "    countSort");


        Integer[] arrayBucket = array1.clone();
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, arrayBucket);
        System.out.println(bucketSort(list, 10) + "    bucketSort");

        Integer[] arrayRadix = array1.clone();
        int[] arrayRadixInt = Arrays.stream(arrayRadix).mapToInt(Integer::valueOf).toArray();
        radixSort(arrayRadixInt);
        System.out.println(Arrays.toString(arrayRadixInt) + "    radixSort");


    }


    private static void swap(int[] array, int i, int j) {
        if (array[i] != array[j]) {
            array[i] ^= array[j];
            array[j] ^= array[i];
            array[i] ^= array[j];
        }
    }

    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                minIndex = array[j] < array[minIndex] ? j : minIndex;
            }
            swap(array, minIndex, i);
        }
    }

    public static void insertSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
    }

    public static void shellSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int gap = array.length >> 1; gap >= 1; gap >>= 1) {
            for (int i = gap; i < array.length; i++) {
                int current = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && current < array[preIndex]) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = current;
            }

        }
    }

    public static int[] mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
        int mid = array.length >> 1;
        return merge(mergeSort(Arrays.copyOfRange(array, 0, mid)),
                mergeSort(Arrays.copyOfRange(array, mid, array.length)));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int i = 0, l = 0, r = 0; i < result.length; i++) {
            if (l == left.length) {
                result[i] = right[r++];
            } else if (r == right.length) {
                result[i] = left[l++];
            } else {
                result[i] = left[l] < right[r] ? left[l++] : right[r++];
            }
        }
        return result;
    }

    public static void quickSort(int[] array, int start, int end) {
        if (array == null || array.length < 2 ||
                start < 0 || end >= array.length || start >= end) {
            return;
        }

        int pivotalIndex = partition(array, start, end);
        quickSort(array, start, pivotalIndex - 1);
        quickSort(array, pivotalIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int i = start;
        for (int j = start + 1; j <= end; j++) {
            if (array[j] < pivot) {
                swap(array, ++i, j);
            }
        }
        swap(array, start, i);
        return i;
    }

    private static int len;

    public static void heapSort(int[] array) {
        if (array == null || (len = array.length) < 2) {
            return;
        }
        buildMaxHeap(array);
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0);
        }
    }

    private static void buildMaxHeap(int[] array) {
        for (int i = (len - 1) >> 1; i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    private static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        if (i << 1 < len && array[i << 1] > array[maxIndex]) {
            maxIndex = i << 1;
        }
        if ((i << 1) + 1 < len && array[(i << 1) + 1] > array[maxIndex]) {
            maxIndex = (i << 1) + 1;
        }
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }

    public static void countSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int max = array[0], min = array[0];
        for (int value : array) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        int[] bucket = new int[max - min + 1];
        for (int an : array) {
            bucket[an - min]++;
        }
        for (int i = 0, j = 0; j < array.length; ) {
            if (bucket[i] > 0) {
                array[j++] = min + i;
                bucket[i]--;
            } else {
                i++;
            }
        }
    }

    public static ArrayList<Integer> bucketSort(ArrayList<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2 || bucketSize < 1) {
            return array;
        }

        int max = array.get(0), min = array.get(0);
        for (Integer an : array) {
            max = Math.max(an, max);
            min = Math.min(an, min);
        }

        int bucketCount = (max - min) / bucketSize + 1;

        ArrayList<ArrayList<Integer>> bucketArray = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArray = new ArrayList<>();

        for (int i = 0; i < bucketCount; i++) {
            bucketArray.add(new ArrayList<>());
        }
        for (Integer anArray : array) {
            bucketArray.get((anArray - min) / bucketSize).add(anArray);
        }

        for (int i = 0; i < bucketCount; i++) {
            if (bucketCount == 1) {
                bucketSize--;
            }
            ArrayList<Integer> temp = bucketSort(bucketArray.get(i), bucketSize);
            resultArray.addAll(temp);
        }
        return resultArray;

    }

    public static void radixSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(array[i], max);
        }

        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<>());
        }
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int anArray : array) {
                int num = (anArray % mod) / div;
                bucketList.get(num).add(anArray);
            }
            int index = 0;
            for (ArrayList<Integer> aBucketList : bucketList) {
                for (Integer anABucketList : aBucketList) {
                    array[index++] = anABucketList;
                }
                aBucketList.clear();
            }
        }
    }
}
