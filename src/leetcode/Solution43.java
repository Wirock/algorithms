package leetcode;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * @author chenzw
 * @date 2021/5/19
 */
public class Solution43 {
    public static String multiply(String num1, String num2) {
        int[] nums = new int[num1.length()+num2.length()];
        int len1 = num1.length();
        int len2 = num2.length();
        for(int i=0;i<len1;i++){
            int a = num1.charAt(len1-1-i)-'0';
            int c = 0;
            for(int j=0;j<len2;j++){
                int b = num2.charAt(len2-1-j)-'0';
                int p = a*b+c+nums[i+j];
                nums[i+j]=p%10;
                c = p/10;
            }
            if(c>0)nums[i+len2]+=c;
        }
        int index=nums.length-1;
        while(index>0&&nums[index]==0)index--;
        StringBuilder sb = new StringBuilder(index+1);
        while(index>=0){
            sb.append(nums[index--]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("13212310","0"));
        System.out.println(multiply("2","3"));
        System.out.println(multiply("999999","999999"));
    }
}
