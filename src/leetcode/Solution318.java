package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 *
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 *
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 *
 *
 * 提示：
 *
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 * @author chenzw
 * @date 2021/11/17
 */
public class Solution318 {
    //状态压缩
    /*public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<words[i].length();j++){
                masks[i]|=1<<(words[i].charAt(j)-'a');
            }
        }
        int ans = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if((masks[i]&masks[j])==0){
                    ans = Math.max(ans,words[i].length()*words[j].length());
                }
            }
        }
        return ans;
    }*/

    //优化,相同素组成的字符串，只考虑最长的即可。用hash来记录每一个掩码对应的最大长度;
    public int maxProduct(String[] words) {
        int n = words.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            int mask = 0;
            for(int j=0;j<words[i].length();j++){
                mask|=1<<(words[i].charAt(j)-'a');
            }
            map.put(mask,Math.max(map.getOrDefault(mask,0),words[i].length()));
        }
        int ans = 0;
        for(Integer i :map.keySet()){
            for(Integer j :map.keySet()){
                if((i&j)==0){
                    ans = Math.max(ans,map.get(i)*map.get(j));
                }
            }
        }
        return ans;
    }

}
