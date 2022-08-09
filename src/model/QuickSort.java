package model;

import common.CommonUtil;

/**
 * 快速排序
 * @author chenzw
 * @date 2022/8/7
 */
public class QuickSort {
    public void quickSort(int[] q, int l, int r){
        if (l >= r) return;
        int i = l-1;
        int j = r+1;
        int x = q[l+r>>1];
        while(i<j){
            do{
                i++;
            }while(q[i]<x);
            do{
                j--;
            }while(q[j]>x);
            if(i<j){
                int temp = q[i];
                q[i] = q[j];
                q[j] = temp;
            }
        }
        quickSort(q,l,j);
        quickSort(q,j+1,r);
    }

    public static void main(String[] args) {
        int[] test = {1,3,8,2,31,53,2,3,9,64,7};
        new QuickSort().quickSort(test,0,test.length-1);
        CommonUtil.printArray(test);
    }
}
