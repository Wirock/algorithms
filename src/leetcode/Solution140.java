package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 140. 单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 *
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 *
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * @author chenzw
 * @date 2021/7/19
 */
public class Solution140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<>();
        Set<String> set = new HashSet<>(wordDict);
        dfs(ret,set,s,0,new StringBuilder());
        return ret;
    }

    private void dfs(List<String> ret, Set<String> set, String s, int cur, StringBuilder sb){
        if(cur==s.length()){
            ret.add(sb.toString());
            return;
        }
        for(int i=cur+1;i<=s.length();i++){
            String str = s.substring(cur, i);
            if(set.contains(str)){
                int start = sb.length();
                if(sb.length()>0)sb.append(" ");
                sb.append(str);
                dfs(ret,set,s,i,sb);
                sb.delete(start,sb.length());
            }
        }
    }
}
