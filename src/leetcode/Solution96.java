package leetcode;

/**
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= n <= 19
 * @author chenzw
 * @date 2021/6/17
 */
public class Solution96 {
    //递归
    /*public int numTrees(int n) {
        return numTrees(1,n);
    }

    public int numTrees(int left,int right){
        if(left==right)return 1;
        int num=numTrees(left+1,right);
        num+=numTrees(left,right-1);
        for(int i=left+1;i<right;i++){
            num+=numTrees(left,i-1)*numTrees(i+1,right);
        }
        return num;
    }*/
    //dp[i][j]表示区间[i,j]中的节点能组成的二叉树的种数
    //dp[i][j]=dp[i+1][j]+dp[i][j-1]+Σdp[i][k-1]*dp[k+1][j]
    public static int numTrees(int n) {
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i]=1;
        }
        for(int len=1;len<n;len++){
            for(int i=0;i<n-len;i++){
                dp[i][i+len]+=dp[i+1][i+len];//根节点为i
                dp[i][i+len]+=dp[i][i+len-1];//根节点为i+len
                for(int k=i+1;k<i+len;k++){
                    dp[i][i+len]+=dp[i][k-1]*dp[k+1][i+len];//根节点为（i+1,i+len-1）
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        System.out.println(numTrees(1));
        System.out.println(numTrees(3));
        System.out.println(numTrees(5));
        System.out.println(numTrees(19));
    }
}
