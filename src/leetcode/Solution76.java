package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * @author chenzw
 * @date 2021/6/9
 */
public class Solution76 {

    public static String minWindow(String s, String t) {
        Map<Character,Integer> needMap = new HashMap<>();//记录需要的各个字母的数量
        Map<Character,Integer> countMap = new HashMap<>();//记录窗口中所需字母的数量
        for(int i=0;i<t.length();i++){
            needMap.put(t.charAt(i),needMap.getOrDefault(t.charAt(i),0)+1);
        }
        //双指针，左右边界
        int left=0;
        int right=s.length()-1;
        //去掉左右边界不需要的字母
        while(left<=right&&!needMap.containsKey(s.charAt(left)))left++;
        while(left<=right&&!needMap.containsKey(s.charAt(right)))right--;
        //目标字符串的左右边界
        int begin = -1;
        int end = s.length();
        //计算滑动窗口中以满足需求的数量
        int total = 0;
        for(int i=left;i<=right;i++){
            if(needMap.containsKey(s.charAt(i))){
                Integer count = countMap.getOrDefault(s.charAt(i),0);
                if(count<needMap.get(s.charAt(i)))total++;
                countMap.put(s.charAt(i),count+1);
                if(total==t.length()){
                    while(left<=right) {
                        if (!needMap.containsKey(s.charAt(left))) {
                            left++;
                        }else if(countMap.get(s.charAt(left))>needMap.get(s.charAt(left))){
                            countMap.put(s.charAt(left),countMap.get(s.charAt(left))-1);
                            left++;
                        }else{
                            break;
                        }
                    }
                    if(i-left<end-begin){
                        begin = left;
                        end = i;
                    }
                    countMap.put(s.charAt(left),countMap.get(s.charAt(left))-1);
                    left++;
                    total--;
                }
            }
        }
        if(begin<0)return "";
        return s.substring(begin,end+1);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "a"));
        System.out.println(minWindow("a", "b"));
        System.out.println(minWindow("bdab","ab"));
        System.out.println(minWindow("aaaaaaaaaaaabbbbbcdd","abcdd"));
    }
}
