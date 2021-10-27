package leetcode;

import java.util.*;

/**
 * 301. 删除无效的括号
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 *
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "()())()"
 * 输出：["(())()","()()()"]
 * 示例 2：
 *
 * 输入：s = "(a)())()"
 * 输出：["(a())()","(a)()()"]
 * 示例 3：
 *
 * 输入：s = ")("
 * 输出：[""]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 25
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 * @author chenzw
 * @date 2021/10/27
 */
public class Solution301 {
    /*List<String> ans;
    Set<String> set;
    int minDelNum;
    public List<String> removeInvalidParentheses(String s) {
        ans = new ArrayList<>();
        set = new HashSet<>();
        minDelNum = 0;
        Deque<Character> stack = new LinkedList<>();
        char[] cs = s.toCharArray();
        for(int i=0;i<cs.length;i++){
            if(cs[i]=='(') {
                stack.push('(');
            }else if(cs[i]==')'){
                if(stack.isEmpty())minDelNum++;
                else stack.pop();
            }
        }
        minDelNum+=stack.size();
        stack.clear();
        dfs(new StringBuilder(),stack,cs,0);
        return ans;
    }

    private void dfs(StringBuilder sb, Deque<Character> stack, char[] cs,int index){
        if(index>sb.length()+minDelNum)return;
        if(index==cs.length){
            String str = sb.toString();
            if(stack.isEmpty()&&!set.contains(str)) {
                ans.add(str);
                set.add(str);
            }
            return;
        }
        dfs(sb,stack,cs,index+1);
        if(cs[index]=='(') {
            stack.push('(');
        }else if(cs[index]==')'){
            if(stack.isEmpty()||stack.peek()!='(')return;
            stack.pop();
        }
        sb.append(cs[index]);
        dfs(sb,stack,cs,index+1);
        sb.deleteCharAt(sb.length()-1);
        if(!stack.isEmpty()&&cs[index]=='(')stack.pop();
        else if(cs[index]==')')stack.push('(');
    }*/

    //优化:
    // 1.我们只需要知道栈中的左括号数量，不必使用栈，用一个数记录栈中的左括号数量即可
    // 2.记录左右括号的数量，并根据数量进行剪枝
    Set<String> set;
    int minDelNum;//记录删除的数量
    int pairNum;//记录最后应剩下的括号对数
    public List<String> removeInvalidParentheses(String s) {
        set = new HashSet<>();
        minDelNum = 0;
        pairNum = 0;
        int left = 0;
        char[] cs = s.toCharArray();
        for(int i=0;i<cs.length;i++){
            if(cs[i]=='(') {
                left++;
            }else if(cs[i]==')'){
                if(left==0) {
                    minDelNum++;
                }else {
                    left--;
                    pairNum++;
                }
            }
        }
        minDelNum+=left;
        dfs(new StringBuilder(),0,0,cs,0);
        return new ArrayList<>(set);
    }

    private void dfs(StringBuilder sb, int left,int right, char[] cs,int index){
        if(left>pairNum||right>pairNum||index>sb.length()+minDelNum)return;
        if(index==cs.length){
            if(left==right) set.add(sb.toString());
            return;
        }

        if(cs[index]=='(') {
            dfs(sb,left,right,cs,index+1);
            left++;
        }else if(cs[index]==')'){
            dfs(sb,left,right,cs,index+1);
            if(left==right)return;
            right++;
        }
        sb.append(cs[index]);
        dfs(sb,left,right,cs,index+1);
        sb.deleteCharAt(sb.length()-1);
        if(cs[index]=='(')left--;
        else if(cs[index]==')')right--;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution301().removeInvalidParentheses("()())()").toArray()));
        System.out.println(Arrays.toString(new Solution301().removeInvalidParentheses("(a)())()").toArray()));
        System.out.println(Arrays.toString(new Solution301().removeInvalidParentheses(")(").toArray()));
    }
}
