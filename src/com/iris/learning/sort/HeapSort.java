package com.iris.learning.sort;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author:  copy
 * @Description: 49, 38, 65, 97, 76, 13, 27, 49
 * @Date: 2019/9/25
 */
public class HeapSort {
    /**
     * 堆筛选，除了start之外，start~end均满足大顶堆的定义。
     * 调整之后start~end称为一个大顶堆。
     *
     * @param arr   待调整数组
     * @param start 起始指针
     * @param end   结束指针
     */
    private static void heapAdjust(int[] arr, int start, int end) {

        int temp = arr[start];

        for (int i = 2 * start + 1; i <= end; i = 2 * i + 1) {
            //左右孩子的节点分别为2*i+1,2*i+2
            //选择出左右孩子较小的下标
            if (i < end && arr[i] < arr[i + 1]) {
                i++;
            }
            if (temp >= arr[i]) {
                break;
            }
            arr[start] = arr[i];
            start = i;
        }
        arr[start] = temp;
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        //建立大顶堆
        for (int i = arr.length / 2; i >= 0; i--) {
            heapAdjust(arr, i, arr.length - 1);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapAdjust(arr, 0, i - 1);
        }

    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr={49,38,65,97,76,13,50,49,80};
        HeapSort.heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

}
