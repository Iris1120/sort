package com.iris.learning.sort;

import java.util.*;

/**
 * @Author:  copy
 * @Description: 假设有一组长度为N的待排关键字序列K[1....n]。首先将这个序列划分成M个的子区间(桶) 。
 * 然后基于某种映射函数，将待排序列的关键字k映射到第i个桶中(即桶数组B的下标i)，
 * 那么该关键字k就作为B[i]中的元素(每个桶B[i]都是一组大小为N/M的序列)。
 * 接着对每个桶B[i]中的所有元素进行比较排序(可以使用快排)。
 * 然后依次枚举输出B[0]….B[M]中的全部内容即是一个有序序列。
 * bindex=f(key)其中，bindex 为桶数组B的下标(即第bindex个桶), k为待排序列的关键字。
 * 桶排序之所以能够高效，其关键在于这个映射函数，它必须做到：如果关键字k1<k2，那么f(k1)<=f(k2)。
 * 也就是说B(i)中的最小数据都要大于B(i-1)中最大数据。很显然，映射函数的确定与数据本身的特点有很大的关系。
 * @Date: 2019/9/26
 */
public class BucketSort {
    /**
     * 映射函数
     * @param x
     * @return
     */
    private static int f(int x) {
        return x / 10;
    }

    public static void bucketSort(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        int bucketNum = 10;  //这里默认为10，规定待排数[0,100)
        List<List<Integer>> buckets = new ArrayList<>(); //桶的索引

        for (int i = 0; i < bucketNum; i++){
            buckets.add(new LinkedList<>()); //用链表比较合适
        }

        //划分桶
        for (int value : arr) {
            buckets.get(f(value)).add(value);
        }

        //对每个桶进行排序
        for (int i = 0; i < bucketNum ; i++) {
            if (!buckets.get(i).isEmpty()){
                Collections.sort(buckets.get(i));
            }
        }

        int k = 0;
        for (List<Integer> bucket:buckets){
            for (int ele : bucket){
                arr[k++] = ele;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 50, 49, 80};
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}


/**
 * 桶排序分析：
 *
 * 桶排序利用函数的映射关系，减少了几乎所有的比较工作。
 * 实际上，桶排序的f(k)值的计算，其作用就相当于快排中划分，希尔排序中的子序列，归并排序中的子问题，
 * 已经把大量数据分割成了基本有序的数据块(桶)。然后只需要对桶中的少量数据做先进的比较排序即可。
 *
 * 对N个关键字进行桶排序的时间复杂度分为两个部分：
 *
 * (1) 循环计算每个关键字的桶映射函数，这个时间复杂度是O(N)。
 *
 * (2) 利用先进的比较排序算法对每个桶内的所有数据进行排序，其时间复杂度为  ∑ O(Ni*logNi) 。其中Ni 为第i个桶的数据量。
 *
 * 很显然，第(2)部分是桶排序性能好坏的决定因素。
 * 尽量减少桶内数据的数量是提高效率的唯一办法(因为基于比较排序的最好平均时间复杂度只能达到O(N*logN)了)。
 * 因此，我们需要尽量做到下面两点：
 *
 * (1) 映射函数f(k)能够将N个数据平均的分配到M个桶中，这样每个桶就有[N/M]个数据量。
 *
 * (2) 尽量的增大桶的数量。极限情况下每个桶只能得到一个数据，这样就完全避开了桶内数据的“比较”排序操作。
 * 当然，做到这一点很不容易，数据量巨大的情况下，f(k)函数会使得桶集合的数量巨大，空间浪费严重。
 * 这就是一个时间代价和空间代价的权衡问题了。
 *
 * 对于N个待排数据，M个桶，平均每个桶[N/M]个数据的桶排序平均时间复杂度为：
 *
 * O(N)+O(M*(N/M)*log(N/M))=O(N+N*(logN-logM))=O(N+N*logN-N*logM)
 *
 * 当N=M时，即极限情况下每个桶只有一个数据时。桶排序的最好效率能够达到O(N)。
 *
 * 总结： 桶排序的平均时间复杂度为线性的O(N+C)，其中C=N*(logN-logM)。
 * 如果相对于同样的N，桶数量M越大，其效率越高，最好的时间复杂度达到O(N)。
 * 当然桶排序的空间复杂度 为O(N+M)，如果输入数据非常庞大，而桶的数量也非常多，则空间代价无疑是昂贵的。
 * 此外，桶排序是稳定的。
 */