package leetcode;

/**
 * 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "Hello World"
 * 输出：5
 * 示例 2：
 *
 * 输入：s = " "
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 *
 * @author chenzw
 * @date 2021/4/20
 */
public class Solution58 {
    public static int lengthOfLastWord(String s) {
        int len = 0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)==' '){
                if(len>0)break;
                continue;
            }
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord( "Hello World"));
        System.out.println(lengthOfLastWord( " "));
    }
}
