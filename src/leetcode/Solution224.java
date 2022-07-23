package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 *
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * 通过次数61,294提交次数147,345
 * @author chenzw
 * @date 2021/6/13
 */
public class Solution224 {
    public int calculate(String s) {
        Deque<Integer> nums = new LinkedList<>();
        Deque<Character> ops = new LinkedList<>();
        int num = 0;
        int ans = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='(') {
                nums.push(num);
                num = 0;
                ops.push(s.charAt(i));
            }else if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
                num = num*10+s.charAt(i)-'0';
            }else if(s.charAt(i)==')'){
                while(ops.peek()!='('){
                    Character op = ops.pop();
                    if(op=='+') {
                        nums.push(num += nums.pop());
                    }else {
                        nums.push(num -= nums.pop());
                    }
                }
                ops.poll();
                nums.push(num);
                num = 0;
            }
        }
        //如果是)结尾的，则最后一个数在nums中
        if(nums.size()>ops.size()){
            num = nums.pop();
        }
        while(!ops.isEmpty()){
            Character op = ops.pop();
            if(op=='+') {
                num += nums.pop();
            }else {
                nums.push(num = nums.pop());
            }
        }
        return ans;
    }
}
