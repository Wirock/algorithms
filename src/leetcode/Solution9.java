package leetcode;

/**
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * @author chenzw
 * @date 2021/3/12
 */
public class Solution9 {
    public static boolean isPalindrome(int x) {
        if(x>=0&&x<=9) return true;
        if(x<0||x%10==0)return false;
        int reverse = 0;
        while(x>reverse){
            reverse = reverse*10+x%10;
            x/=10;
        }
        return x==reverse||x==reverse/10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(-1323));
        System.out.println(isPalindrome(323));
        System.out.println(isPalindrome(2));
        System.out.println(isPalindrome(13213213));
    }
}
