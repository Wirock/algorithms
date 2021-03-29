package leetcode;

/**
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 *
 * @author chenzw
 * @date 2021/3/29
 */
public class Solution32 {
    public static int longestValidParentheses(String s) {
        if(s.length()<2)return 0;
        int maxLen=0;
        int[] dp = new int[s.length()+1];//dp[i]表示以s的第i个字符结尾的有效字串
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){
                    dp[i]=(i<2?0:dp[i-2])+2;
                }else if(i-1-dp[i-1]>=0&&s.charAt(i-1-dp[i-1])=='('){
                    dp[i]=(i-2-dp[i-1]<0?0:dp[i-2-dp[i-1]])+dp[i-1]+2;
                }
                maxLen=Math.max(dp[i],maxLen);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses(""));
        System.out.println(longestValidParentheses(")(((()())))("));
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses("()"));
        System.out.println(longestValidParentheses("())"));
        System.out.println(longestValidParentheses("(())"));
    }
}
