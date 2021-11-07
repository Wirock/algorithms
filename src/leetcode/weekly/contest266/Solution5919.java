package leetcode.weekly.contest266;

import java.util.HashSet;
import java.util.Set;

/**
 * 5919. 所有子字符串中的元音 显示英文描述
 * 通过的用户数141
 * 尝试过的用户数150
 * 用户总通过次数141
 * 用户总提交次数156
 * 题目难度Medium
 * 给你一个字符串 word ，返回 word 的所有子字符串中 元音的总数 ，元音是指 'a'、'e'、'i'、'o' 和 'u' 。
 *
 * 子字符串 是字符串中一个连续（非空）的字符序列。
 *
 * 注意：由于对 word 长度的限制比较宽松，答案可能超过有符号 32 位整数的范围。计算时需当心。
 *
 *
 *
 * 示例 1：
 *
 * 输入：word = "aba"
 * 输出：6
 * 解释：
 * 所有子字符串是："a"、"ab"、"aba"、"b"、"ba" 和 "a" 。
 * - "b" 中有 0 个元音
 * - "a"、"ab"、"ba" 和 "a" 每个都有 1 个元音
 * - "aba" 中有 2 个元音
 * 因此，元音总数 = 0 + 1 + 1 + 1 + 1 + 2 = 6 。
 * 示例 2：
 *
 * 输入：word = "abc"
 * 输出：3
 * 解释：
 * 所有子字符串是："a"、"ab"、"abc"、"b"、"bc" 和 "c" 。
 * - "a"、"ab" 和 "abc" 每个都有 1 个元音
 * - "b"、"bc" 和 "c" 每个都有 0 个元音
 * 因此，元音总数 = 1 + 1 + 1 + 0 + 0 + 0 = 3 。
 * 示例 3：
 *
 * 输入：word = "ltcd"
 * 输出：0
 * 解释："ltcd" 的子字符串均不含元音。
 * 示例 4：
 *
 * 输入：word = "noosabasboosa"
 * 输出：237
 * 解释：所有子字符串中共有 237 个元音。
 *
 *
 * 提示：
 *
 * 1 <= word.length <= 105
 * word 由小写英文字母组成
 * @author chenzw
 * @date 2021/11/7
 */
public class Solution5919 {
    /*public long countVowels(String word) {
        int n = word.length();
        char[] cs = word.toCharArray();
        Set<Character> candidate = new HashSet();
        candidate.add('a');
        candidate.add('e');
        candidate.add('i');
        candidate.add('o');
        candidate.add('u');
        long ans = 0;
        int k=candidate.contains(cs[0])?1:0;
        //计算以cs[0]开始的字串的元音数
        ans+=k;
        for(int i=1;i<n;i++){
            k=candidate.contains(cs[i])?k+1:k;
            ans+=k;
        }
        long j=ans;
        //计算以cs[i]开始的字串的元音数
        for(int i=0;i<n-1;i++){
            if(candidate.contains(cs[i])){
                j=j-(n-i);
            }
            ans+=j;
        }
        return ans;
    }*/

    public long countVowels(String word) {
        int n = word.length();
        char[] cs = word.toCharArray();
        Set<Character> candidate = new HashSet();
        candidate.add('a');
        candidate.add('e');
        candidate.add('i');
        candidate.add('o');
        candidate.add('u');
        long ans = 0;
        //对于每个元音，计算包含它的字串数量
        for(int i=0;i<n;i++){
            if(!candidate.contains(cs[i]))continue;
            ans+=(long)(i+1)*(n-i);
        }
        return ans;
    }
}
