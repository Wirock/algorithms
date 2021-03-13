package leetcode;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *
 * 动态规划
 *
 * dp[i][j]表示s的前i位与p的前j位是否匹配，dp[0][j]表示空字符与p的前j位是否匹配,dp[i][0]表示s的前i位与空字符是否匹配
 * 可得边界条件dp[0][0]为true,dp[0][j](j>0)为false,dp[i][0](i>0)为false
 *
 *  s[i]表示s的第i个字符(java表示为s.charAt(i-1))，p[j]表示p的第j个字符(java表示为p.charAt(j-1))
 *  两种情况
 *  1. p[j]不为'*'时，只需判断s[i]与p[j]是否匹配来递推,
 *      1)如果s[i]与p[j]匹配，则dp[i][j]=dp[i-1][j-1]，
 *      2)否则dp[i][j]=false;
 *  2. p[j]为'*'时，则p[j]必须与p[j-1]一起判断，p[j-1]和p[j]一起表示0个或任意个p[i-1],
 *      1)如果s[i]与p[j-1]匹配，在p[j-1]和p[j]表示0个p[i-1]和多个p[j-1]时都有可能匹配，则dp[i][j]=dp[i][j-2]||dp[i][j-1]||dp[i-1][j];三个部分依次为0个，1个，2个以上的匹配可能
 *      2)如果s[i]与p[j-1]不匹配，那只有p[j-1]和p[j]表示0个p[i-1]时有可能匹配，dp[i][j]=dp[i][j-2]
 * @author chenzw
 * @date 2021/3/13
 */
public class Solution10 {

    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;//两个空字符串匹配
        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=p.length();j++){
                if(p.charAt(j-1)!='*'){
                    if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.'){
                        dp[i][j]=dp[i-1][j-1];
                    }
                }else{
                    if(p.charAt(j-2)==s.charAt(i-1)||p.charAt(j-2)=='.'){
                        dp[i][j]=dp[i][j-2]||dp[i][j-1]||dp[i-1][j];
                    }else{
                        dp[i][j]=dp[i][j-2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("a","a*"));
        System.out.println(isMatch("a","aa"));
        System.out.println(isMatch("",""));
        System.out.println(isMatch("a","a.*"));
        System.out.println(isMatch("a","a"));
        System.out.println(isMatch("a","aa*"));
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","c*a*b*"));
    }
}
