package leetcode;

/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * @author chenzw
 * @date 2021/6/3
 */
public class Solution69 {
    /*public static int mySqrt(int x) {
        long left = 0;
        long right = x;
        long mid=0;
        while(left<=right){
            mid = (left+right)/2;
            long p = mid*mid;
            if(p==x)return (int)mid;
            if(p>x){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        if(mid*mid>x)return (int)mid-1;
        return (int)mid;
    }*/
    public static int mySqrt(int x) {
        long left = 0;
        long right = x;
        long mid=0;
        int ans = 0;
        while(left<=right){
            mid = (left+right)/2;
            long p = mid*mid;
            if(p<=x){
                ans = (int)mid;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(51343424));
    }
}
