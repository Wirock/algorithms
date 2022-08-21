package leetcode.weekly.contest307;

import java.util.Arrays;

/**
 * 6166. 最大回文数字
 * 给你一个仅由数字（0 - 9）组成的字符串 num 。
 *
 * 请你找出能够使用 num 中数字形成的 最大回文 整数，并以字符串形式返回。该整数不含 前导零 。
 *
 * 注意：
 *
 * 你 无需 使用 num 中的所有数字，但你必须使用 至少 一个数字。
 * 数字可以重新排序。
 *
 *
 * 示例 1：
 *
 * 输入：num = "444947137"
 * 输出："7449447"
 * 解释：
 * 从 "444947137" 中选用数字 "4449477"，可以形成回文整数 "7449447" 。
 * 可以证明 "7449447" 是能够形成的最大回文整数。
 * 示例 2：
 *
 * 输入：num = "00009"
 * 输出："9"
 * 解释：
 * 可以证明 "9" 能够形成的最大回文整数。
 * 注意返回的整数不应含前导零。
 *
 *
 * 提示：
 *
 * 1 <= num.length <= 105
 * num 由数字（0 - 9）组成
 * @author chenzw
 * @date 2022/8/21
 */
public class Solution6166 {
    public String largestPalindromic(String num) {
        int n = num.length();
        int[] count = new int[10];
        char[] cs = num.toCharArray();
        for(int i=0;i<n;i++){
            count[cs[i]-'0']++;
        }
        String single = "";
        StringBuilder couple = new StringBuilder();
        for(int i=1;i<10;i++){
            if(count[i]%2==1){
                if("".equals(single)||single.charAt(0)-'0'<i){
                    single=i+"";
                }
            }
            for(int j=0;j<count[i]/2;j++){
                couple.append(i);
            }
        }
        if(count[0]>0){
            if(couple.length()>0){
                for(int j=0;j<count[0]/2;j++){
                    couple.append(0);
                }
            }
            if("".equals(single)&&count[0]%2==1){
                single = "0";
            }
        }
        if(couple.length()==0&&"".equals(single)&&count[0]>0){
            return "0";
        }
        char[] cc = couple.toString().toCharArray();
        Arrays.sort(cc);
        StringBuilder post = new StringBuilder(new String(cc));
        return post.reverse().toString()+single+post.reverse().toString();
    }
}
