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
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (findKthElement(nums1, 0, n - 1, nums2, 0, m - 1, left) + findKthElement(nums1, 0, n - 1, nums2, 0, m - 1, right))/2.0;
    }


    private static int findKthElement(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return findKthElement(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return findKthElement(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return findKthElement(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays2(new int[]{1,2,3,4,5},new int[]{6}));
        System.out.println(findMedianSortedArrays2(new int[]{0,0,0,0,0},new int[]{-1,0,0,0,0,0,1}));
        System.out.println(findMedianSortedArrays2(new int[]{1,3},new int[]{2,7}));
    }
}
