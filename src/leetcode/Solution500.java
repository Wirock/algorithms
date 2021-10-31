package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 500. 键盘行
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 *
 * 美式键盘 中：
 *
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * American keyboard
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 * 示例 2：
 *
 * 输入：words = ["omk"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i] 由英文字母（小写和大写字母）组成
 * @author chenzw
 * @date 2021/10/31
 */
public class Solution500 {
    //hash
    public String[] findWords(String[] words) {
        List<String> list = new LinkedList<>();
        for(String w:words){
            if(check(w.toLowerCase()))list.add(w);
        }
        String[] ans = new String[list.size()];
        list.toArray(ans);
        return ans;
    }
    Map<Character,Integer> map = new HashMap<>();
    {
        for(char c:"qwertyuiop".toCharArray())map.put(c,1);
        for(char c:"asdfghjkl".toCharArray())map.put(c,2);
        for(char c:"zxcvbnm".toCharArray())map.put(c,3);
    }
    private boolean check(String word){
        int line = map.get(word.charAt(0));
        for(int i=1;i<word.length();i++){
            if(map.get(word.charAt(i))!=line)return false;
        }
        return true;
    }
}
