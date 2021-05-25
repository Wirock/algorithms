package leetcode;

/**
 * @author chenzw
 * @date 2021/5/26
 */
public class Solution50 {
    public static double myPow(double x, int n) {
        if(n==0)return 1;
        if(x==0)return 0;
        if(n==1)return x;
        if(n==-1)return 1/x;
        double a = myPow(x, n / 2);
        return a*a*myPow(x,n%2);
    }

    public static void main(String[] args) {
        System.out.println(myPow(2,-2));
        System.out.println(myPow(0.00001,2147483647));
    }
}
