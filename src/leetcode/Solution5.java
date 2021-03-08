package leetcode;

/**
 * 5.最长回文子串
 * @author chenzw
 * @date 2021/3/8
 */
public class Solution5 {
    //暴力匹配,o(n^3)
    public static String longestPalindrome1(String s) {
        if(s==null){
            return null;
        }
        if(s.length()<2){
            return s;
        }
        char[] chars = s.toCharArray();
        int begin = 0;
        int maxLen = 1;
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                if(j-i+1>maxLen&&validPalindrome(chars,i,j)){
                    maxLen = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }
    private static boolean validPalindrome(char[] chars,int left,int right){
        while(left<right){
            if(chars[left]!=chars[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //动态规划,o(n^2)
    public static String longestPalindrome2(String s) {
        if(s==null){
            return null;
        }
        int len = s.length();
        if(len<2){
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        //dp[i][j]表示是s[i..j]是否回文串
        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();
        //单个字符为回文串
        for(int i=0;i<len;i++){
            dp[i][i]=true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
