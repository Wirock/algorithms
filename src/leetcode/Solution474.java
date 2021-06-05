package leetcode;

/**
 * 474. 一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * 示例 2：
 *
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 * @author chenzw
 * @date 2021/6/6
 */
public class Solution474 {

    /*public static int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length+1][m+1][n+1];//dp[k][i][j]表示前k个子符串，0不超过m个，1不超过n个的最大数量
        for(int k=1;k<=strs.length;k++){
            String s = strs[k-1];
            int c0 = 0;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='0')c0++;
            }
            int c1 = s.length()-c0;
            for(int i=0;i<=m;i++){
                for(int j=0;j<=n;j++){
                    if(i>=c0&&j>=c1){
                        dp[k][i][j] = Math.max(dp[k-1][i][j],dp[k-1][i-c0][j-c1]+1);
                    }else{
                        dp[k][i][j] = dp[k-1][i][j];
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }*/

    //优化，每一轮只于上一轮相关，dp数组减少一个维度
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for(String s:strs){
            int c0 = 0;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='0')c0++;
            }
            int c1 = s.length()-c0;
            for(int i=m;i>=c0;i--){
                for(int j=n;j>=c1;j--){
                    dp[i][j] = Math.max(dp[i][j],dp[i-c0][j-c1]+1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(findMaxForm(new String[]{"10","0001","111001","1","0"},4,2));
        System.out.println(findMaxForm(new String[]{"10","0","1"},1,1));
    }
}
