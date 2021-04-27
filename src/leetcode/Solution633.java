package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 *
 * 输入：c = 3
 * 输出：false
 * 示例 3：
 *
 * 输入：c = 4
 * 输出：true
 * 示例 4：
 *
 * 输入：c = 2
 * 输出：true
 * 示例 5：
 *
 * 输入：c = 1
 * 输出：true
 *
 *
 * 提示：
 *
 * 0 <= c <= 2^31 - 1
 * @author chenzw
 * @date 2021/4/28
 */
public class Solution633 {
    //hash一遍循环超时
   /* public static boolean judgeSquareSum(int c) {
        Set<Integer> set = new HashSet<>();
        int i = 0;
        int s;
        while((s=i*i)<=c){
            set.add(s);
            i++;
            if(set.contains(c-s)){
                return true;
            }
        }
        return false;
    }*/

   //双指针
    /*public static boolean judgeSquareSum(int c) {
        int i = 0;
        int j = (int)Math.sqrt(c);
        while(i<=j){
            int sum = i*i+j*j;
            if(c==sum){
                return true;
            }
            if(c>sum){
                i++;
            }else{
                j--;
            }
        }
        return false;
    }*/

    //数学
    public static boolean judgeSquareSum(int c) {
        long i = 0;
        long s;
        while((s=c-i*i)>=0){
            double sqrt = Math.sqrt(s);
            if(sqrt==(int)sqrt){
                return true;
            }
            i++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(5));
        System.out.println(judgeSquareSum(3));
        System.out.println(judgeSquareSum(4));
        System.out.println(judgeSquareSum(2));
        System.out.println(judgeSquareSum(1));
        System.out.println(judgeSquareSum(2147482647));
        System.out.println(judgeSquareSum(8));
    }
}
