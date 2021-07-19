package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * @author chenzw
 * @date 2021/7/19
 */
public class Solution139 {
    //dp,f(i,j)表示坐标i，j之间的子字符串是否可拆分
    /*public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for(String k:wordDict){
            set.add(k);
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int len=1;len<=n;len++){
            for(int i=0;i<=n-len;i++){
                if(set.contains(s.substring(i,i+len))){
                    dp[i][i+len-1] = true;
                }else{
                    for(int j=i;j<i+len-1;j++){
                        if(dp[i][j]&&dp[j+1][i+len-1]){
                            dp[i][i+len-1] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][n-1];
    }*/

    //dp,优化，f(i)表示从坐标0到i-1之间子字符串是否可拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for(String k:wordDict){
            set.add(k);
        }
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                if(dp[j]&&set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
