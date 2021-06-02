package leetcode;

/**
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * @author chenzw
 * @date 2021/6/1
 */
public class Solution67 {
    public static String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int x;
        int y;
        int c = 0;
        int count = 0;
        int finalCount = Math.max(a.length(),b.length());
        while(count<finalCount){
            x = a.length()>count?a.charAt(a.length()-1-count)-'0':0;
            y = b.length()>count?b.charAt(b.length()-1-count)-'0':0;
            int z = x + y + c;
            ans.append(z%2);
            c = z/2;
            count++;
        }
        if(c>0)ans.append(c);
        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11","1"));
        System.out.println(addBinary("1010","1011"));
    }
}
