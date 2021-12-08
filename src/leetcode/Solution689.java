package leetcode;

import java.util.Arrays;

/**
 * 689. 三个无重叠子数组的最大和
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
 *
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,1,2,6,7,5,1], k = 2
 * 输出：[0,3,5]
 * 解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
 * 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 * 示例 2：
 *
 * 输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
 * 输出：[0,2,4]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] < 216
 * 1 <= k <= floor(nums.length / 3)
 * @author chenzw
 * @date 2021/12/8
 */
public class Solution689 {
    /*
    * 若不考虑输出方案，仅是求「三个无重叠子数组的最大和」的最大值。

只需要使用动态规划求解即可：定义 f[i][j]f[i][j] 为考虑前 ii 个数，凑成无重叠子数组数量为 jj 个时的最大值。

最终答案为 f[n - 1][3]f[n−1][3]。

不失一般性的考虑 f[i][j]f[i][j] 该如何计算（以最优方案是否包含 nums[i]nums[i] 进行讨论）：

最优方案中包含 num[i]num[i]：由于这 jj 个无重叠，因此前面的 j - 1j−1 个子数组不能覆盖 [i - k + 1, i][i−k+1,i]。即只能在 [0, i - k][0,i−k] 中选 j - 1j−1 个子数组。此时有：
f[i][j] = f[i - k][j - 1] + \sum_{idx = i - k + 1}^{i} nums[idx]
f[i][j]=f[i−k][j−1]+
idx=i−k+1
∑
i
​
 nums[idx]

其中求解 \sum_{idx = i - k + 1}^{i} nums[idx]∑
idx=i−k+1
i
​
 nums[idx] 部分可以使用「前缀和」优化

最优方案不包含 num[i]num[i]：当明确了 nums[i]nums[i] 对最优方案无贡献，此时问题等价于考虑前 i - 1i−1 个数，凑成 jj 个不重叠子数组的最大值。此时有：
f[i][j] = f[i - 1][j]
f[i][j]=f[i−1][j]

最终 f[i][j]f[i][j] 为上述两种方案中的最大值。

然后考虑「如何回溯出字典序最小的具体方案」，常规的回溯具体方案的做法是，从最终答案 f[n - 1][3]f[n−1][3] 开始往回追溯。

利用 f[n - 1][3]f[n−1][3] 仅由两个可能的节点（f[n - 2][3]f[n−2][3] 和 f[n - 1 - k][2]f[n−1−k][2]）转移而来，通过判断 f[n - 1][3]f[n−1][3] 等于 f[n - 2][3]f[n−2][3] 还是 f[n - 1 - k][2] + \sum_{idx = n - k }^{n - 1} nums[idx]f[n−1−k][2]+∑
idx=n−k
n−1
​
 nums[idx] 来决定回溯点为何值。

但该做法只能确保回溯出字典序最大的方案是正确（当两个可能的前驱节点都能转移到 f[i][j]f[i][j] 时优先采纳靠后的位置），而我们需要回溯出字典序最小的方案。

在上述解法的基础上，有两种「求解字典序最小具体方案」的做法：

将正序 DP 调整为反序 DP。修改状态定义为 f[i][j]f[i][j] 为考虑 [i, n - 1][i,n−1] 中进行选择，凑出无重叠子数组数量为 jj 个时的最大值（最终答案为 f[0][3]f[0][3]）。转移过程分析同理，然后从下标 idx = 0idx=0 开始进行回溯，优先采纳 idxidx 小的方案即可；

仍然采取正序 DP 的做法，但对原数组进行翻转，从而将回溯「字典序最大」转换为「字典序最小」具体方案。

一些细节：为了避免对边界的处理，我们令动规数组 ff 和前缀和数组 sunsun 的下标从 11 开始。

代码（P1P1 为反序 DP 做法，P2P2 为翻转数组做法）：
。*/
    public int[] maxSumOfThreeSubarrays1(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        long[][] f = new long[n + 10][4];
        for (int i = n - k + 1; i >= 1; i--) {
            for (int j = 1; j < 4; j++) {
                f[i][j] = Math.max(f[i + 1][j], f[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]);
            }
        }
        int[] ans = new int[3];
        int i = 1, j = 3, idx = 0;
        while (j > 0) {
            if (f[i + 1][j] > f[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]) {
                i++;
            } else {
                ans[idx++] = i - 1;
                i += k; j--;
            }
        }
        return ans;
    }

    //滑动窗口
    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int[] ans = new int[3];
        int sum1 = 0, maxSum1 = 0, maxSum1Idx = 0;
        int sum2 = 0, maxSum12 = 0, maxSum12Idx1 = 0, maxSum12Idx2 = 0;
        int sum3 = 0, maxTotal = 0;
        for (int i = k * 2; i < nums.length; ++i) {
            sum1 += nums[i - k * 2];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= k * 3 - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Idx = i - k * 3 + 1;
                }
                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    maxSum12Idx1 = maxSum1Idx;
                    maxSum12Idx2 = i - k * 2 + 1;
                }
                if (maxSum12 + sum3 > maxTotal) {
                    maxTotal = maxSum12 + sum3;
                    ans[0] = maxSum12Idx1;
                    ans[1] = maxSum12Idx2;
                    ans[2] = i - k + 1;
                }
                sum1 -= nums[i - k * 3 + 1];
                sum2 -= nums[i - k * 2 + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution689().maxSumOfThreeSubarrays2(new int[]{1,2,1,2,6,7,5,1},2)));
    }
}
