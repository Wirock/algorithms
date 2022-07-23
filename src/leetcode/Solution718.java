package leetcode;

/**
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 *
 *
 * 示例：
 *
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 *
 *
 * 提示：
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * @author chenzw
 * @date 2021/7/4
 */
public class Solution718 {
    //dp
    //f(i,j)表示nums1中以第i项结尾和nums2的以第j项结尾的最长公共子数组的长度
    //nums1[i]!=nums2[j],有f(i,j) = 0
    //nums1[i]==nums2[j],有f(i,j) = f(i-1,j-1)+1
    /*public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        int ans = 0;
        for(int i=1;i<=nums1.length;i++){
            for(int j=1;j<=nums2.length;j++){
                if(nums1[i-1]!=nums2[j-1]) continue;
                dp[i][j] = dp[i - 1][j - 1] + 1;
                ans = Math.max(ans,dp[i][j]);
            }
        }
        return ans;
    }

    //空间优化
    public int findLength(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length+1];
        int ans = 0;
        for(int i=1;i<=nums1.length;i++){
            for(int j=nums2.length;j>0;j--){
                if(nums1[i-1]!=nums2[j-1]) continue;
                dp[j] = dp[j - 1] + 1;
                ans = Math.max(ans,dp[j]);
            }
        }
        return ans;
    }

    */

    //滑动串口，枚举所有对其方式
    /*public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int count = 0;
            for(int j=0;j<len;j++){
                if(nums1[i+j]==nums2[j]) {
                    count++;
                    ret = Math.max(ret, count);
                }else count=0;
            }
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int count = 0;
            for(int j=0;j<len;j++){
                if(nums1[j]==nums2[j]) {
                    count++;
                    ret = Math.max(ret, count);
                }else count=0;
            }
        }
        return ret;
    }*/

    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int count = 0;
            for(int j=0;j<len;j++){
                if(nums1[i+j]==nums2[j]) {
                    count++;
                    ret = Math.max(ret, count);
                }else count=0;
            }
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int count = 0;
            for(int j=0;j<len;j++){
                if(nums1[j]==nums2[j]) {
                    count++;
                    ret = Math.max(ret, count);
                }else count=0;
            }
        }
        return ret;
    }
}
