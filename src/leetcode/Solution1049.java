package leetcode;

/**
 * 1049. 最后一块石头的重量 II
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *
 *
 *
 * 示例 1：
 *
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 示例 2：
 *
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 * 示例 3：
 *
 * 输入：stones = [1,2]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * @author chenzw
 * @date 2021/6/8
 */
public class Solution1049 {

    /*public static int lastStoneWeightII(int[] stones) {
        boolean[][][] dp = new boolean[stones.length][stones.length][101];//dp[i][j][k]表示stones下标[i,j]之间的全部元素能否得到k
        for(int i=0;i<stones.length;i++){
            dp[i][i][stones[i]]=true;
        }
        for(int len=1;len<stones.length;len++){
            for(int i=0;i<stones.length-len;i++){
                for(int k=0;k<=100;k++){
                    for(int m=i;m<i+len;m++){
                        for(int n=0;n<=100-k;n++){
                            dp[i][i+len][k] = dp[i][i+len][k]||dp[i][m][n]&&dp[m+1][i+len][n+k]||dp[i][m][n+k]&&dp[m+1][i+len][n];
                            if(dp[i][i+len][k])break;
                        }
                        if(dp[i][i+len][k])break;
                    }
                }
            }
        }
        for(int k=0;k<=100;k++){
            if(dp[0][stones.length-1][k])return k;
        }
        return 100;
    }*/

    //优化，把石头分为两堆，每次从两个堆各取一个出来粉碎，把较大的石子剩余的部分放回它所属的堆，如此循环直到其中一个堆没有石头剩下。
    //剩下的部分为两个堆石子重量总和的差。要使剩下的部分最小，只要使分成的两堆石子的重量差最小，即每堆石子的总重量尽可能接近全部石子的总重量的一半
    //转换为01背包问题，设所有石子总重量为sum,在容量不超过sum/2,总价值,价值不超过sum/2的条件下能拿到的最大价值
    //f(i,j) = Math.max(f(i-1,j),f(i-1,j-stones[i-1])) f(i,j)表示前i个元素，重量不超过j的最大值
    public static int lastStoneWeightII(int[] stones) {
        int sum=0;
        for(int i=0;i<stones.length;i++){
            sum+=stones[i];
        }
        int[] dp = new int[sum/2+1];
        for(int i=0;i<stones.length;i++){
            for(int j=sum/2;j>0;j--){
                if(j>=stones[i])dp[j] = Math.max(dp[j],dp[j-stones[i]]+stones[i]);
            }
        }
        return sum-dp[sum/2]-dp[sum/2];
    }
    public static void main(String[] args) {
        System.out.println(lastStoneWeightII(new int[]{2,7,4,1,8,1}));
        System.out.println(lastStoneWeightII(new int[]{31,26,33,21,40}));
        System.out.println(lastStoneWeightII(new int[]{1,2}));
    }
}
