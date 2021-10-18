package leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 *
 * 提示：
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * @author chenzw
 * @date 2021/3/4
 */
public class Solution4 {
    //O(m+n)双指针
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int totalLength = (nums1.length + nums2.length);
        int mid = totalLength / 2;
        boolean odd = (totalLength & 1) == 1;
        int i = 0;
        int j = 0;
        double[] midNum = new double[]{0.0, 0.0};
        while (i + j <= mid) {
            int index = (i + j) % 2;
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] <= nums2[j]) {
                    midNum[index] = nums1[i++];
                } else {
                    midNum[index] = nums2[j++];
                }
            } else if (i == nums1.length) {
                midNum[index] = nums2[j++];
            } else {
                midNum[index] = nums1[i++];
            }
        }
        if (odd) {
            return midNum[mid % 2];
        }
        return (midNum[0] + midNum[1]) / 2;
    }

    //O(log(m+n))二分查找
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m - 1) / 2;
        int right = (n + m) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (findKthElement(nums1, 0, nums2, 0, left) + findKthElement(nums1, 0, nums2, 0, right)) / 2.0;
    }

    //二分查找
    private static int findKthElement(int[] nums1, int start1, int[] nums2, int start2, int k) {
        int len1 = nums1.length - start1;
        int len2 = nums2.length - start2;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return findKthElement(nums2, start2, nums1, start1, k);
        if (len1 == 0) return nums2[start2 + k];

        if (k == 0) return Math.min(nums1[start1], nums2[start2]);

        //取num1的第k/2个元素nums1[i]和num2的第k/2个元素nums2[j]比较
        int i = start1 + Math.min(len1 - 1, (k - 1) / 2);
        int j = start2 + Math.min(len2 - 1, (k - 1) / 2);
        //nums1[i] > nums2[j],则第k大的元素在num1[i]的左侧或num2[j]的右侧中
        //nums1[i] <= nums2[j],则第k大的元素在num1[i]的右侧或num2[j]的左侧中
        if (nums1[i] > nums2[j]) {
            return findKthElement(nums1, start1, nums2, j + 1, k - (j - start2 + 1));
        } else {
            return findKthElement(nums1, i + 1, nums2, start2, k - (i - start1 + 1));
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays2(new int[]{1, 2, 3, 4, 5}, new int[]{6}));
        System.out.println(findMedianSortedArrays2(new int[]{0, 0, 0, 0, 0}, new int[]{-1, 0, 0, 0, 0, 0, 1}));
        System.out.println(findMedianSortedArrays2(new int[]{1, 3}, new int[]{2, 7}));
    }
}
