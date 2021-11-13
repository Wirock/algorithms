package leetcode;

/**
 * 520. 检测大写字母
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：word = "USA"
 * 输出：true
 * 示例 2：
 *
 * 输入：word = "FlaG"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= word.length <= 100
 * word 由小写和大写英文字母组成
 * @author chenzw
 * @date 2021/11/13
 */
public class Solution520 {
    public boolean detectCapitalUse(String word) {
        int n = word.length();
        char[] cs = word.toCharArray();
        boolean first = cs[0]<'a';
        boolean none = cs[0]>='a';
        boolean all = cs[0]<'a';
        for(int i=1;i<n;i++){
            if(cs[i]<'a'){
                first = false;
                none = false;
            }else{
                all = false;
            }
        }
        return first||none||all;
    }
}
