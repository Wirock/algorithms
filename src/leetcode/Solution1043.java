package leetcode;

/**
 * 1043. 分隔数组以得到最大和
 * 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 *
 * 返回将数组分隔变换后能够得到的元素最大和。本题所用到的测试用例会确保答案是一个 32 位整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：数组变为 [15,15,15,9,10,10,10]
 * 示例 2：
 *
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 *
 * 输入：arr = [1], k = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 109
 * 1 <= k <= arr.length
 * Created by Chenzw on 2023/4/19 15:52
 */
public class Solution1043 {
    /*暴力dfs超时
    public int maxSumAfterPartitioning(int[] arr, int k) {
        return dfs(arr, 0, 0, k );
    }

    private int dfs(int[] arr, int s, int sum, int k){
        if(s==arr.length){
            return sum;
        }
        int ans = 0;
        for(int i=s;i<Math.min(arr.length,s+k);i++){
            ans = Math.max(ans, dfs(arr, i+1, sum +  getVal(arr, s, i), k));
        }
        return ans;
    }*/

    //dp
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n+1];
        dp[1] = arr[0];
        for(int i=2;i<=n;i++){
            for(int j=1;j<=Math.min(k, i);j++){
                dp[i] = Math.max(dp[i], dp[i-j]+getVal(arr, i-j, i-1));
            }
        }
        return dp[n];
    }

    private int getVal(int[] arr, int b, int e){
        if(e<b){
            return 0;
        }
        int max = 0;
        for(int i=b;i<=e;i++){
            max = Math.max(max,arr[i]);
        }
        return max*(e-b+1);
    }
}
