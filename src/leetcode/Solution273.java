package leetcode;

/**
 * 273. 整数转换英文表示
 * 将非负整数 num 转换为其对应的英文表示。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 * 示例 2：
 *
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 * 示例 3：
 *
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4：
 *
 * 输入：num = 1234567891
 * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 *
 * 提示：
 *
 * 0 <= num <= 231 - 1
 * @author chenzw
 * @date 2021/10/11
 */
public class Solution273 {
    public String numberToWords(int num) {
        String ans = "";
        final int BILLION = (int)1e9;
        final int MILLION = (int)1e6;
        final int THOUSAND = (int)1e3;
        final int HUNDRED = (int)1e2;
        String[] ones = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine","Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = new String[]{"","Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        if(num/BILLION>0)ans = numberToWords(num/BILLION)+" Billion"+(num%BILLION>0?(" "+numberToWords(num%BILLION)):"");
        else if(num/MILLION>0)ans = numberToWords(num/MILLION)+" Million"+(num%MILLION>0?(" "+numberToWords(num%MILLION)):"");
        else if(num/THOUSAND>0)ans = numberToWords(num/THOUSAND)+" Thousand"+(num%THOUSAND>0?(" "+numberToWords(num%THOUSAND)):"");
        else if(num/HUNDRED>0)ans = numberToWords(num/HUNDRED)+" Hundred"+(num%HUNDRED>0?(" "+numberToWords(num%HUNDRED)):"");
        else if(num<20)ans = ones[num];
        else ans = tens[num/10]+(num%10>0?(" "+ones[num%10]):"");
        return ans.trim();
    }
}
