package leetcode;

/**
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 *
 * 返回符合要求的 最少分割次数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 *
 * 输入：s = "ab"
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 * @author chenzw
 * @date 2021/7/7
 */
public class Solution132 {
    public static int minCut(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[n][n];
        for(int diff=0;diff<n;diff++){
            for(int i=0;i<n-diff;i++){
                int right = i+diff;
                if(isPalindrome(chars,i,right)){
                    dp[i][right]=0;
                }else{
                    dp[i][right]=diff;
                }
            }
        }
        for(int i=1;i<n;i++){
            if(dp[0][i]>0) {
                for(int j=0;j<i;j++){
                    dp[0][i] = Math.min(dp[0][i], dp[0][j]+dp[j+1][i]+1);
                }
            }
        }
        return dp[0][n-1];
    }

    private static boolean isPalindrome(char[] chars,int left,int right){
        while(left<right){
            if(chars[left++]!=chars[right--])return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(minCut("aab"));
        //System.out.println(minCut("aabbccddeedd"));
    }
}
