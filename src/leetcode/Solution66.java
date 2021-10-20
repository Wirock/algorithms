package leetcode;

import java.util.Arrays;

/**
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * @author chenzw
 * @date 2021/6/1
 */
public class Solution66 {
    public static int[] plusOne(int[] digits) {
        int i=digits.length-1;
        while(i>=0&&digits[i]==9){
            i--;
        }
        if(i<0){
            int[] ans = new int[digits.length+1];
            ans[0]=1;
            return ans;
        }else{
            for(int j=digits.length-1;j>i;j--){
                digits[j]=0;
            }
            digits[i]++;
            return digits;
        }
    }

    /*public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 1;
        while(n>0&&carry==1){
            digits[--n]+=1;
            carry = digits[n]/10;
            digits[n] = digits[n]%10;
        }
        if(carry==1){
            int[] ans = new int[digits.length+1];
            ans[0] = 1;
            for(int i=1;i<ans.length;i++)ans[i] = digits[i-1];
            return ans;
        }
        return digits;
    }*/

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{1,2,3})));
        System.out.println(Arrays.toString(plusOne(new int[]{4,3,2,1})));
        System.out.println(Arrays.toString(plusOne(new int[]{9,9,9,9})));
    }
}
