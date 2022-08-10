package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1417. 重新格式化字符串
 * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
 *
 * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
 *
 * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "a0b1c2"
 * 输出："0a1b2c"
 * 解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
 * 示例 2：
 *
 * 输入：s = "leetcode"
 * 输出：""
 * 解释："leetcode" 中只有字母，所以无法满足重新格式化的条件。
 * 示例 3：
 *
 * 输入：s = "1229857369"
 * 输出：""
 * 解释："1229857369" 中只有数字，所以无法满足重新格式化的条件。
 * 示例 4：
 *
 * 输入：s = "covid2019"
 * 输出："c2o0v1i9d"
 * 示例 5：
 *
 * 输入：s = "ab123"
 * 输出："1a2b3"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 500
 * s 仅由小写英文字母和/或数字组成。
 * Created by Chenzw on 2022/8/11 0:50
 */
public class Solution1417 {
    //队列分组
    public String reformat1(String s) {
        Queue<Character> q1 = new LinkedList<>();
        Queue<Character> q2 = new LinkedList<>();
        for(char c:s.toCharArray()){
            if(c>='0'&&c<='9'){
                q1.offer(c);
            }else{
                q2.offer(c);
            }
        }
        if(q1.size()<q2.size()){
            Queue<Character> q = q2;
            q2 = q1;
            q1 = q;
        }
        if(q1.size()-q2.size()<=1){
            StringBuilder sb = new StringBuilder(s.length());
            while(!q1.isEmpty()){
                sb.append(q1.poll());
                if(!q2.isEmpty()){
                    sb.append(q2.poll());
                }
            }
            return sb.toString();
        }
        return "";
    }

    //双指针
    public String reformat12(String s) {
        if(s.length()<2){
            return s;
        }
        char[] cs = s.toCharArray();
        int i = 0;
        int j = 1;
        int cd = 0;
        for(char c:cs){
            if(c<='9'){
                cd++;
            }
        }
        while(i<cs.length&&j<cs.length){
            while(i<cs.length){
                if(cd>cs.length/2){
                    if(cs[i]>'9'){
                        break;
                    }
                }else{
                    if(cs[i]<='9'){
                        break;
                    }
                }
                i += 2;
            }
            while(j<cs.length){
                if(cd>cs.length/2){
                    if(cs[j]<='9'){
                        break;
                    }
                }else{
                    if(cs[j]>'9'){
                        break;
                    }
                }
                j += 2;
            }
            if(i<cs.length&&j<cs.length){
                char t = cs[i];
                cs[i] = cs[j];
                cs[j] = t;
            }
        }
        if(i>=cs.length&&j>=cs.length){
            return new String(cs);
        }
        return "";
    }

}
