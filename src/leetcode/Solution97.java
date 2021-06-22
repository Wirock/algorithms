package leetcode;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 *
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 *
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *
 *
 * 提示：
 *
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 * @author chenzw
 * @date 2021/6/16
 */
public class Solution97 {
    //f(i,j)表示s1的前i个字符和s2的前j个字符能否交错组成s3的前i+j个字符
    //f(i,j)=f(i-1,j)||f(i,j-1)
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int p = s3.length();
        if(m+n!=p)return false;

        //boolean[][] dp = new boolean[m + 1][n + 1];
        //空间优化，没一行只与上一行有关，故使用一维空间即可
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <=m; i++) {
            for (int j = 0; j <= n; j++) {
                int k = i+j-1;
                if(i>0)dp[j] = s1.charAt(i - 1) == s3.charAt(k) && dp[j] ;
                if(j>0)dp[j]|= s2.charAt(j-1) == s3.charAt(k) && dp[j - 1];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(isInterleave("", "", ""));
    }
}
