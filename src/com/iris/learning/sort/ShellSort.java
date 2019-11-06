package com.iris.learning.sort;

import java.util.Arrays;

/**
 * @Author:  copy
 * @Description: 希尔排序是插入排序的一种高效率的实现，也叫缩小增量排序。
 * 简单的插入排序中，如果待排序列是正序时，时间复杂度是O(n)，如果序列是基本有序的，使用直接插入排序效率就非常高。
 * 希尔排序就利用了这个特点。基本思想是：先将整个待排记录序列分割成为若干子序列分别进行直接插入排序，
 * 待整个序列中的记录基本有序时再对全体记录进行一次直接插入排序。
 * 时间复杂度可以达到O(n^1.3)
 * @Date: 2019/9/26
 */
public class ShellSort {

    /**
     * 希尔排序的一趟插入
     * @param arr 待排数组
     * @param d 增量
     */
    private static void shellInsert(int[] arr, int d) {
        for (int i = d; i < arr.length; i++) {
            int j = i - d;
            int temp = arr[i]; //记录要插入的数据
            while (j >= 0 && arr[j] > temp) { //从后向前，找到比其小的数的位置
                arr[j + d] = arr[j];  //向后挪动
                j -= d;
            }
            if (j != i - d) {  //存在比其小的数
                arr[j + d] = temp;
            }
        }
    }

    public static void shellSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int d = arr.length / 2;
        while (d >= 1) {
            shellInsert(arr, d);
            d /= 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 50, 49, 80};
        ShellSort.shellSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
