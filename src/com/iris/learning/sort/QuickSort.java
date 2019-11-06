package com.iris.learning.sort;

import java.util.Arrays;

/**
 * @Author:  copy
 * @Description: 快速排序一听名字就觉得很高端，在实际应用当中快速排序确实也是表现最好的排序算法。其思想是来自冒泡排序，冒泡排序是通过相邻元素的比较和交换把最小的冒泡到最顶端，而快速排序是比较和交换小数和大数，这样一来不仅把小数冒泡到上面同时也把大数沉到下面。
 *
 * 举个栗子：对5,3,8,6,4这个无序序列进行快速排序，思路是右指针找比基准数小的，左指针找比基准数大的，交换之。
 *
 * 5,3,8,6,4 用5作为比较的基准，最终会把5小的移动到5的左边，比5大的移动到5的右边。
 *
 * 5,3,8,6,4 首先设置i,j两个指针分别指向两端，j指针先扫描（思考一下为什么？）4比5小停止。然后i扫描，8比5大停止。交换i,j位置。
 *
 * 5,3,4,6,8 然后j指针再扫描，这时j扫描4时两指针相遇。停止。然后交换4和基准数。
 *
 * 4,3,5,6,8 一次划分后达到了左边比5小，右边比5大的目的。之后对左右子序列递归排序，最终得到有序序列。
 *
 * 上面留下来了一个问题为什么一定要j指针先动呢？首先这也不是绝对的，这取决于基准数的位置，因为在最后两个指针相遇的时候，要交换基准数到相遇的位置。一般选取第一个数作为基准数，那么就是在左边，所以最后相遇的数要和基准数交换，那么相遇的数一定要比基准数小。所以j指针先移动才能先找到比基准数小的数。
 *
 * 快速排序是不稳定的，其时间平均时间复杂度是O(nlgn)。
 * @Date: 2019/9/25
 */
public class QuickSort {

    private static int partition(int[] arr, int left, int right){
        int pivotKey = arr[left];

        while (left < right){
            while (left < right && arr[right] >= pivotKey){
                right--;
            }
            arr[left] = arr[right]; //把小的移动到左边
            while (left < right && arr[left] <= pivotKey){
                left++;
            }
            arr[right] = arr[left]; //把大的移动到右边
        }
        arr[left] = pivotKey; //最后把pivot赋值到中间
        return left;
    }

    private static void sort(int[] arr, int left, int right){
        if(left >= right){
            return ;
        }
        int pivotPos = partition(arr, left, right);
        sort(arr, left, pivotPos - 1);
        sort(arr, pivotPos + 1, right);
    }

    public static void quickSort(int[] arr){

        if (arr == null || arr.length == 0){
            return;
        }
        sort(arr, 0, arr.length -1);
    }


    public static void main(String[] args) {
        int[] arr = {10,3,6,2,1};
        QuickSort.quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
