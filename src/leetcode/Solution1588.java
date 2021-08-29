package leetcode;

/**
 * 1588. 所有奇数长度子数组的和
 * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
 *
 * 子数组 定义为原数组中的一个连续子序列。
 *
 * 请你返回 arr 中 所有奇数长度子数组的和 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,4,2,5,3]
 * 输出：58
 * 解释：所有奇数长度子数组和它们的和为：
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 * 示例 2：
 *
 * 输入：arr = [1,2]
 * 输出：3
 * 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
 * 示例 3：
 *
 * 输入：arr = [10,11,12]
 * 输出：66
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 1000
 * @author chenzw
 * @date 2021/8/29
 */
public class Solution1588 {
    //前缀和
    /*public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        if(n==0)return 0;
        int ans = 0;
        int[] sum = new int[n+1];
        for(int i=1;i<sum.length;i++)sum[i]=sum[i-1]+arr[i-1];
        for(int i=1;i<=n;i+=2){
            for(int j=0;j<=n-i;j++){
                ans+=sum[j+i]-sum[j];
            }
        }
        return ans;
    }*/
    //数学，计算每个元素在多少个子数组中出现
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int ans = 0;
        for(int i=0;i<n;i++){
            int leftCount = i+1;//坐标[0,i]的元素数
            int rightCount = n-i;//坐标[i,n-1]的元素数
            int leftOdd = (leftCount+1)>>1;//坐标[0,i]中包含i的奇数个元素的子数组数量
            int leftEven = leftCount>>1;//坐标[0,i]中包含i的偶数个元素的子数组数量
            int rightOdd = (rightCount+1)>>1;//坐标[i,n-1]中包含i的奇数个元素的子数组数量
            int rightEven = rightCount>>1;//坐标[i,n-1]中包含i的偶数个元素的子数组数量
            ans += (leftOdd*rightOdd+leftEven*rightEven)*arr[i];
        }
        return ans;
    }
}
