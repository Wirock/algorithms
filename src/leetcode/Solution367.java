package leetcode;

/**
 * 367. 有效的完全平方数
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 *
 * 输入：num = 14
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= num <= 2^31 - 1
 * @author chenzw
 * @date 2021/11/4
 */
public class Solution367 {
    //二分查找
    public boolean isPerfectSquare(int num) {
        long left=1;
        long right=num;
        while(left<=right){
            long mid = (left+right)>>1;
            long product = mid*mid;
            if(product==num)return true;
            if(product>num)right = mid-1;
            else left = mid + 1;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution367().isPerfectSquare(2147483647));
    }
}
