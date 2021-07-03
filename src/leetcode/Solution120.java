package leetcode;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 示例 2：
 *
 * 输入：triangle = [[-10]]
 * 输出：-10
 *
 *
 * 提示：
 *
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *
 *
 * 进阶：
 *
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 * @author chenzw
 * @date 2021/6/23
 */
public class Solution120 {
    //dp,设f(i,j)表示以第i行第j个数结尾的路径的最短距离
    //f(i,j) = min(min(f(i-1,j-1),f(i-1,j))+triangle[i][j])
    //滚动数组空间优化，只需要一维空间
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0]=triangle.get(0).get(0);
        for(int i=1;i<n;i++){
            List<Integer> row = triangle.get(i);
            dp[row.size()-1]=row.get(row.size()-1)+dp[row.size()-2];
            for(int j=row.size()-2;j>0;j--){//倒序，保证更新到dp[j]时，dp[j-1]是上一行的
                dp[j] = row.get(j)+Math.min(dp[j-1],dp[j]);
            }
            dp[0]=row.get(0)+dp[0];
        }
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            ans = Math.min(ans,dp[i]);
        }
        return ans;
    }
}
