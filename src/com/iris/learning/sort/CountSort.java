package com.iris.learning.sort;

import java.util.Arrays;

/**
 * @Author: yunrong@wacai.com
 * @Description: 虽然前面基于比较的排序的下限是O(nlogn)。
 * 但是确实也有线性时间复杂度的排序，只不过有前提条件，就是待排序的数要满足一定的范围的整数，而且计数排序需要比较多的辅助空间。
 * 其基本思想是，用待排序的数作为计数数组的下标，统计每个数字的个数。然后依次输出即可得到有序序列。
 * @Date: 2019/9/26
 */
public class CountSort {

    private static int max(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int ele : arr){
            if (ele > max){
                max = ele;
            }
        }
        return max;
    }

    public static void countSort(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        int max = max(arr);
        int[] count = new int[max + 1];
        for (int value : arr) {
            count[value]++;
        }

        int k = 0;
        for (int i = 0; i <= max; i++) {
            for (int j = 0; j < count[i] ; j++) {
                arr[k++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 50, 49, 80};
        countSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
