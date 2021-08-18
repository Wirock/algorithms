package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 *
 *
 * 示例 1：
 *
 * 输入："hello"
 * 输出："holle"
 * 示例 2：
 *
 * 输入："leetcode"
 * 输出："leotcede"
 *
 *
 * 提示：
 *
 * 元音字母不包含字母 "y" 。
 * @author chenzw
 * @date 2021/8/19
 */
public class Solution345 {
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length-1;
        while(left<right){
            while(!set.contains(chars[left]))left++;
            while(!set.contains(chars[right]))right--;
            if(left<right){
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }
}
