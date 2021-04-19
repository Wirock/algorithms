package leetcode;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 * @author chenzw
 * @date 2021/3/8
 */
public class Solution5 {
    //暴力匹配,o(n^3)
    public static String longestPalindrome1(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        int begin = 0;
        int maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (j - i + 1 > maxLen && validPalindrome(chars, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private static boolean validPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //动态规划,o(n^2)
    public static String longestPalindrome2(String s) {
        if (s == null) {
            return null;
        }
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        //dp[i][j]表示是s[i..j]是否回文串
        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();
        //单个字符为回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
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

    public static String longestPalindrome3(String s) {
        if (s == null) {
            return null;
        }
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        //dp[i][j]表示是s[i..j]是否回文串
        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();
        //单个字符为回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int i = len-2; i>=0; i--) {
            for (int  j= i+1; j < len; j++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;//左边界不等于右边界，一定不是回文串
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

    public static void main(String[] args) {
        System.out.println(longestPalindrome3("abcb"));
    }
}
