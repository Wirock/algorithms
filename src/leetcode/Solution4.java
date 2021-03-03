package leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
 *
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * @author chenzw
 * @date 2021/3/4
 */
public class Solution4 {
    //O(m+n)
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int totalLength = (nums1.length+nums2.length);
        int mid = totalLength/2;
        boolean odd = (totalLength&1)==1;
        int i=0;
        int j=0;
        double[] midNum = new double[]{0.0,0.0};
        while(i+j<=mid){
            int index = (i+j)%2;
            if(i<nums1.length&&j<nums2.length){
                if(nums1[i]<=nums2[j]){
                    midNum[index] = nums1[i++];
                }else{
                    midNum[index] = nums2[j++];
                }
            }else if(i==nums1.length){
                midNum[index] = nums2[j++];
            }else{
                midNum[index] = nums1[i++];
            }
        }
        if(odd){
            return midNum[mid%2];
        }
        return (midNum[0]+midNum[1])/2;
    }
    //O(log(m+n))
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        //todo
        return 0.0;
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays1(new int[]{1,2,3,4,5},new int[]{}));
    }
}
