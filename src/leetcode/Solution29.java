package leetcode;

/**
 * 29. 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * @author chenzw
 * @date 2021/3/23
 */
public class Solution29 {
    public static int divide(int dividend,int divisor){
        if(divisor==1){
            return dividend;
        }else if(divisor==-1){
            return -(dividend==Integer.MIN_VALUE?dividend+1:dividend);//Integer.MIN_VALUE翻转溢出
        }
        if(dividend==divisor){//dividend, divisor都为Integer.MIN_VALUE
            return 1;
        }
        boolean signed = false;
        if(dividend>0){
            signed=!signed;
            dividend = -dividend;
        }
        if(divisor>0){
            signed = !signed;
            divisor = -divisor;
        }
        if(divisor<dividend){
            return 0;
        }
        if(divisor<Integer.MIN_VALUE-divisor){
            return signed?-1:1;
        }
        int count = -1;
        int d = divisor;
        while(d+d>dividend){
            count+=count;
            d += d;
            if(d<Integer.MIN_VALUE-d)break;
        }
        if(signed){
            return count - divide(dividend - d,divisor);
        }else{
            return -count + divide(dividend - d,divisor);
        }

    }

    public static void main(String[] args) {
        /*System.out.println(divide(100,3));
        System.out.println(divide(-100,3));
        System.out.println(divide(-100,-3));
        System.out.println(divide(Integer.MIN_VALUE,-1));
        System.out.println(divide(Integer.MIN_VALUE,Integer.MAX_VALUE));
        System.out.println(divide(Integer.MIN_VALUE,Integer.MIN_VALUE));
        System.out.println(divide(Integer.MAX_VALUE,Integer.MIN_VALUE));
        System.out.println(divide(Integer.MAX_VALUE,Integer.MAX_VALUE));*/
        System.out.println(divide(Integer.MAX_VALUE,3));
    }
}
