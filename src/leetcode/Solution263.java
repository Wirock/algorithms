package leetcode;

/**
 * 263. 丑数
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 *
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * @author chenzw
 * @date 2021/4/10
 */
public class Solution263 {
    public static boolean isUgly(int n) {
        if(n<=5&&n!=4){
            return n>0;
        }
        if(n%2==0){
            return isUgly(n/2);
        }
        if(n%3==0){
            return isUgly(n/3);
        }
        if(n%5==0){
            return isUgly(n/5);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isUgly(8));
        System.out.println(isUgly(6));
        System.out.println(isUgly(14));
    }
}
