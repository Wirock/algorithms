package leetcode;

/**
 * 879. 盈利计划
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 *
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 *
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 *
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * 输出：2
 * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * 总的来说，有两种计划。
 * 示例 2：
 *
 * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * 输出：7
 * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 *
 *
 * 提示：
 *
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 * @author chenzw
 * @date 2021/6/9
 */
public class Solution879 {
    //f(k,i,j)=f(k-1,i,j)+f(k-1,i-group[k],j-profit[k])    f(k,i,j)表示前k项工程不超过i人，得到利润j的选择数
    /*public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int mod = (int)1e9+7;
        int totalProfit = 0;
        for(int i=0;i<profit.length;i++){
            totalProfit += profit[i];
        }
        long[][] dp = new long[n+1][totalProfit+1];//状态转移方程第k个只与第k-1个相关，故可减少一个维度， dp[i][j]表示在在前k个项目中选择，人员不超过i的利润且利润为j的选择数
        for(int i=0;i<=n;i++){
           dp[i][0]=1;
        }
        for(int k=0;k<group.length;k++){
            //两个循环都倒序，保证下面dp[i-group[k]][j-profit[k]在dp[i][j]后更新，因为由状态转移方程看，它应该是上一轮的
            for(int i=dp.length-1;i>=1;i--){
                for(int j=dp[i].length-1;j>=0;j--){
                   if(i>=group[k]&&j>=profit[k])dp[i][j] = (dp[i][j]+dp[i-group[k]][j-profit[k]])%mod;
                }
            }
        }

        int ans=0;
        for(int j=minProfit;j<=totalProfit;j++){
            ans = (int)(ans+dp[n][j])%mod;
        }
        return ans;
    }*/

    //优化，不必计算每一个利润的数量， 令f(k,i,j)表示前k项工程不超过i人，得到利润最少为j的选择数，则j的范围[0,sum(profit)]缩小至[0,minProfit]
    //f(k,i,j)=f(k-1,i,j)+f(k-1,i-group[k],max(j-profit[k],0))
    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int mod = (int)1e9+7;
        int[][] dp = new int[n+1][minProfit+1];//状态转移方程第k个只与第k-1个相关，故可减少一个维度， dp[i][j]表示在在前k个项目中选择，人员不超过i的利润且利润至少为j的选择数
        for(int i=0;i<=n;i++){
            dp[i][0]=1;
        }
        for(int k=0;k<group.length;k++){
            //两个循环都倒序，保证下面dp[i-group[k]][j-profit[k]在dp[i][j]后更新，因为由状态转移方程看，它应该是上一轮的
            for(int i=dp.length-1;i>=1;i--){
                for(int j=dp[i].length-1;j>=0;j--){
                    if(i>=group[k])dp[i][j] = (dp[i][j]+dp[i-group[k]][Math.max(j-profit[k],0)])%mod;
                }
            }
        }
        return dp[n][minProfit];
    }
    public static void main(String[] args) {
        System.out.println(profitableSchemes(5,3,new int[]{2,2},new int[]{2,3}));
        System.out.println(profitableSchemes(10,5,new int[]{2,3,5},new int[]{6,7,8}));
    }
}
