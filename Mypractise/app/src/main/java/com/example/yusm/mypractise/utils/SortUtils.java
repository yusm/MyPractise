package com.example.yusm.mypractise.utils;
/*
 *
 * Created by iPanel@iPanel.cn
 * Date: 2019/2/27
 * Desc：
 */

import java.util.Arrays;

public class SortUtils {

    /**
     * 冒泡排序
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array){
        if(array==null||array.length==0)
            return array;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-1-i;j++){
                if(array[j+1]<array[j]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 选择排序
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array){
        if(array==null||array.length==0)
            return array;
        for(int i=0;i<array.length;i++){
            int minIndex = i;
            for(int j=i;j<array.length;i++){
                if(array[j]<array[minIndex])
                    minIndex = j;
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    /**
     * 插入排序
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array){
        if(array==null||array.length==0)
            return array;
        int current;
        for(int i=0;i<array.length;i++){
            current = array[i+1];
            int preIndex = i;
            while (preIndex>=0&&current<array[preIndex]){
                array[preIndex+1] = array[preIndex];
                preIndex--;
            }
            array[preIndex+1] = current;
        }
        return array;
    }

    /**
     * 希尔排序
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array){
        int len = array.length;
        int temp,gap = len/2;
        while (gap>0){
            for(int i=gap;i<len;i++){
                temp = array[i];
                int preIndex = i-gap;
                while (preIndex>=0&&array[preIndex]>temp){
                    array[preIndex+gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex+ gap] = temp;
            }
            gap/=2;
        }
        return array;
    }

    /**
     * 归并排序
     * @param array
     * @return
     */
    public static int[] mergeSort(int[] array){
        if(array.length<2) return array;
        int mid = array.length/2;
        int[] left = Arrays.copyOfRange(array,0,mid);
        int[] right = Arrays.copyOfRange(array,mid,array.length);
        return merge(mergeSort(left),mergeSort(right));
    }

    /**
     * 归并排序，将两端排序好的数组结合成一个排序数组
     * @param left
     * @param right
     * @return
     */
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length+right.length];
        for(int index=0,i=0,j=0;index<result.length;index++){
            if(i>=left.length)
                result[index] = right[j++];
            else if(j>=right.length)
                result[index] = left[i++];
            else if(left[i]>right[j])
                result[index] = right[j++];
            else result[index] = left[i++];
        }
        return result;
    }

    /**
     * 快速排序方法
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int[] quickSort(int[] array,int start,int end){
        if(array.length<1||start<0||end>=array.length||start>end)
            return null;
        int smallIndex = partition(array,start,end);
        if(smallIndex>start)
            quickSort(array,start,smallIndex-1);
        if(smallIndex<end)
            quickSort(array,smallIndex+1,end);
        return array;
    }

    /**
     * 快速排序算法——partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] array, int start, int end) {
        int pivot = (int) (start+Math.random()*(end-start+1));
        int smallIndex = start-1;
        swap(array,pivot,end);
        for(int i = start;i<=end;i++){
            if(array[i]<=array[end]){
                smallIndex++;
                if(i>smallIndex)
                    swap(array,i,smallIndex);
            }
        }
        return smallIndex;
    }

    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    private static void swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //声明全局变量，用于记录数组array的长度
    static int len;

    /**
     * 堆排序算法
     * @param array
     * @return
     */
    public static int[] heapSort(int[] array){
        len = array.length;
        if(len<1)
            return array;
        //1.构建一个最大堆
        buildMaxHeap(array);
        //2.循环将堆首位(最大值)与末位交换，然后在重新调整最大堆
        while (len>0){
            swap(array,0,len-1);
            len--;
            adjustHeap(array,0);
        }
        return array;
    }

    /**
     * 建立最大堆
     * @param array
     */
    private static void buildMaxHeap(int[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        for(int i=(len-1)/2;i>=0;i--){
            adjustHeap(array,i);
        }
    }

    /**
     * 调整使之成为最大堆
     * @param array
     * @param i
     */
    private static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if(i*2<len&&array[i*2]>array[maxIndex])
            maxIndex = i*2;
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if(i*2+1<len&&array[i*2+1]>array[maxIndex])
            maxIndex = i*2+1;
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置
        if(maxIndex!=i){
            swap(array,maxIndex,i);
            adjustHeap(array,maxIndex);
        }
    }

//    private static void merge(int[] nums1,int nums2[]){
//
//    }
}
