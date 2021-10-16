package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 282. 给表达式添加运算符
 * 给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的表达式。
 *
 *
 *
 * 示例 1:
 *
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * 示例 2:
 *
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * 示例 3:
 *
 * 输入: num = "105", target = 5
 * 输出: ["1*0+5","10-5"]
 * 示例 4:
 *
 * 输入: num = "00", target = 0
 * 输出: ["0+0", "0-0", "0*0"]
 * 示例 5:
 *
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 *
 *
 * 提示：
 *
 * 1 <= num.length <= 10
 * num 仅含数字
 * -231 <= target <= 231 - 1
 * @author chenzw
 * @date 2021/10/16
 */
public class Solution282 {
    //dfs回溯找出所有表达式，计算结果是否符合
    /*public List<String> addOperators(String num, int target) {
        int n = num.length();
        int[] nums = new int[n];
        char[] chars = num.toCharArray();
        for(int i=0;i<n;i++){
            nums[i] = chars[i]-'0';
        }
        char[] op = new char[n-1];
        List<String> ans = new LinkedList<>();
        dfs(nums,op,0,ans,target);
        return ans;
    }

    private long caculate(int[] nums,char[] op){
        if(nums.length==1)return nums[0];
        Deque<Long> numStack = new LinkedList<>();
        char lastOp = ' ';
        numStack.push((long)nums[0]);
        for(int i=0;i<op.length;i++){
            if(op[i]==' '){
                numStack.push(numStack.pop()*10+nums[i+1]);
            }else{
                if(lastOp=='*'){
                    numStack.push(numStack.pop()*numStack.pop());
                }else if(lastOp=='-'){
                    numStack.push(-numStack.pop());
                }
                numStack.push((long)nums[i + 1]);
                lastOp=op[i];
            }
        }
        if(lastOp=='*'){
            numStack.push(numStack.pop()*numStack.pop());
        }else if(lastOp=='-'){
            numStack.push(-numStack.pop());
        }
        return numStack.stream().reduce(Long::sum).get();
    }

    private void dfs(int[] nums,char[] op,int cur,List<String> ans,int target){
        if(cur==op.length){
            long r = caculate(nums, op);
            if(r==target){
                StringBuilder sb = new StringBuilder(nums[0]+"");
                for(int i=0;i<op.length;i++){
                    if(op[i]!=' ')sb.append(op[i]);
                    sb.append(nums[i+1]);
                }
                ans.add(sb.toString());
            }
            return;
        }
        op[cur]='*';
        dfs(nums,op,cur+1,ans,target);
        op[cur]='+';
        dfs(nums,op,cur+1,ans,target);
        op[cur]='-';
        dfs(nums,op,cur+1,ans,target);
        if(nums[cur]==0&&(cur==0||op[cur-1]!= ' '))return;
        op[cur]=' ';
        dfs(nums,op,cur+1,ans,target);
    }*/

    //优化，直接在回溯的过程中计算总和
    int n;
    String num;
    int target;
    List<String> ans;

    public List<String> addOperators(String num, int target) {
        this.n = num.length();
        this.num = num;
        this.target = target;
        this.ans = new LinkedList<>();
        StringBuffer expr = new StringBuffer();
        backtrack(expr, 0, 0, 0);
        return ans;
    }

    public void backtrack(StringBuffer expr, int i, long res, long mul) {
        if (i == n) {
            if (res == target) {
                ans.add(expr.toString());
            }
            return;
        }
        int signIndex = expr.length();
        if (i > 0) {
            expr.append(0); // 占位，下面填充符号
        }
        long val = 0;
        // 枚举截取的数字长度（取多少位），注意数字可以是单个 0 但不能有前导零
        for (int j = i; j < n && (j == i || num.charAt(i) != '0'); ++j) {
            val = val * 10 + num.charAt(j) - '0';
            expr.append(num.charAt(j));
            if (i == 0) { // 表达式开头不能添加符号
                backtrack(expr, j + 1, val, val);
            } else { // 枚举符号
                expr.setCharAt(signIndex, '*');
                backtrack(expr, j + 1, res - mul + mul * val, mul * val);
                expr.setCharAt(signIndex, '+');
                backtrack(expr, j + 1, res + val, val);
                expr.setCharAt(signIndex, '-');
                backtrack(expr, j + 1, res - val, -val);
            }
        }
        expr.setLength(signIndex);
    }

    public static void main(String[] args) {
        System.out.println(new Solution282().addOperators("123",6));
    }
}
