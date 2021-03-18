package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 * @author chenzw
 * @date 2021/3/18
 */
public class Solution22 {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if(n<1){
            return result;
        }
        findCombinations(result,new StringBuilder("("),2*n-1,n);
        return result;
    }

    private static void findCombinations(List<String> result,StringBuilder sb,int m,int n){
        int count = 0;
        for(int i=0;i<sb.length();i++){
            if(sb.charAt(i)=='(') count++;
            else count--;
            if(count<0){
                return;
            }else if(count>n){
                return;
            }
        }
        if(m==0){
            if(count!=0){
                return;
            }
            result.add(sb.toString());
            return;
        }
        sb.append("(");
        findCombinations(result,sb,m-1,n);
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        findCombinations(result,sb,m-1,n);
        sb.deleteCharAt(sb.length()-1);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateParenthesis(8).toArray()));
        System.out.println(Arrays.toString(generateParenthesis(3).toArray()));
        System.out.println(Arrays.toString(generateParenthesis(1).toArray()));
    }
}
