package leetcode;

/**
 * 400. 第 N 位数字
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 *
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 *
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 * @author chenzw
 * @date 2021/11/30
 */
public class Solution400 {
    //1位 9个
    //2位 90个
    //3位 900个
    public int findNthDigit(int n) {
        int sum = 0;//总位数
        int num = 0;//数字数
        int i=1;
        int add = 9;
        while(i<10&&sum+(long)add*i<n){
            sum+=add*i;
            num+=add;
            add = (int)Math.pow(10,i++)*9;
        }
        num += (n-sum)/i;//得到目标数字或目标数字前一个数字
        if((n-sum)%i==0)return num%10;//若刚好除尽则为目标数字，取最后一位
        return ((num+1)/((int)Math.pow(10,i-(n-sum)%i)))%10;//除不尽则为目标数字前一个数，取第i位
    }

    public static void main(String[] args) {
        System.out.println(new Solution400().findNthDigit(11));
        System.out.println(new Solution400().findNthDigit(1234567));
        System.out.println(new Solution400().findNthDigit(2147483647));
    }
}
