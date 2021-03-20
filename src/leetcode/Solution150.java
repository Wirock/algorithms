package leetcode;

import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * 根据 逆波兰表示法，求表达式的值。
 *
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * @author chenzw
 * @date 2021/3/20
 */
public class Solution150 {
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<tokens.length;i++){
            if("+".equals(tokens[i])||"-".equals(tokens[i])||"*".equals(tokens[i])||"/".equals(tokens[i])){
                int num1 = stack.pop();
                int num2 = stack.pop();
                if("+".equals(tokens[i])){
                    num1 = num2+num1;
                }else if("-".equals(tokens[i])){
                    num1 = num2-num1;
                }else if("*".equals(tokens[i])){
                    num1 = num2*num1;
                }else if("/".equals(tokens[i])){
                    num1 = num2/num1;
                }
                stack.push(num1);
            }else{
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{ "2","1","+","3","*"}));
    }
}
