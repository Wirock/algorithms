package leetcode;

/**
 * 640. 求解方程
 * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 *
 * 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
 *
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * 示例 2:
 *
 * 输入: equation = "x=x"
 * 输出: "Infinite solutions"
 * 示例 3:
 *
 * 输入: equation = "2x=x"
 * 输出: "x=0"
 *
 *
 * 提示:
 *
 * 3 <= equation.length <= 1000
 * equation 只有一个 '='.
 * equation 方程由整数组成，其绝对值在 [0, 100] 范围内，不含前导零和变量 'x' 。
 * Created by Chenzw on 2022/8/10 23:01
 */
public class Solution640 {
    //模拟
    public String solveEquation(String equation) {
        int k = 0;
        int n = 0;
        char[] cs = equation.toCharArray();
        int left = 1;
        int signed = 1;
        int j = 0;
        for(int i=0;i<cs.length;i++){
            if(cs[i]>='0'&&cs[i]<='9'){
                j = j*10 + cs[i] - '0';
            }else{
                if(cs[i] == 'x'){
                    if(j!=0){
                        k += j * signed * left;
                    }else if(i==0||cs[i-1]!='0'){
                        k+=signed*left;
                    }
                }else{
                    n -= j*signed * left;
                    if(cs[i]=='-'){
                        signed = -1;
                    }else{
                        signed = 1;
                    }
                    if(cs[i]=='='){
                        left = -1;
                    }
                }
                j = 0;
            }
        }
        n -=  j*signed * left;
        if(k==0){
            return n==0?"Infinite solutions":"No solution";
        }
        return "x="+n/k;
    }
}
