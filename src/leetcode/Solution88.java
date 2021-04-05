package leetcode;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * @author chenzw
 * @date 2021/4/5
 */
public class Solution88 {
    /*public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len=m;
        while(n-->0){
            while(m>0&&nums2[n]<nums1[m-1])m--;
            for(int i=len;i>m;i--){
                nums1[i]=nums1[i-1];
            }
            nums1[m]=nums2[n];
            len++;
        }
    }*/
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index=m+n-1;
        while(m>0&&n>0){
            nums1[index--]=nums1[m-1]>nums2[n-1]?nums1[--m]:nums2[--n];
        }
        while(n>0){
            nums1[index--]=nums2[--n];
        }
    }

    public static void main(String[] args) {
    }
}
