package model;

import common.CommonUtil;

/**
 * 归并排序
 * @author chenzw
 * @date 2022/8/7
 */
public class MergeSort {
    public void mergeSort(int[] q, int l, int r){
        if(l>=r){
            return;
        }
        int mid = l + r >> 1;
        mergeSort(q, l, mid);
        mergeSort(q, mid+1, r);
        int k = 0;
        int i = l;
        int j = mid+1;
        int[] temp = new int[r-l+1];
        while(i <= mid && j<=r){
            if(q[i]<=q[j]){
                temp[k++] = q[i++];
            }else{
                temp[k++] = q[j++];
            }
        }
        while(i<=mid){
            temp[k++] = q[i++];
        }
        while(j<=r){
            temp[k++] = q[j++];
        }
        for(i = l,k = 0; i <= r; i++, k++){
            q[i] = temp[k];
        }
    }

    public static void main(String[] args) {
        int[] test = {1,3,8,2,31,53,2,3,9,64,7};
        new MergeSort().mergeSort(test,0,test.length-1);
        CommonUtil.printArray(test);
    }
}
