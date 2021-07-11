package leetcode.biweekly.contest56;

/**
 * 5792. 统计平方和三元组的数目
 * 一个 平方和三元组 (a,b,c) 指的是满足 a2 + b2 = c2 的 整数 三元组 a，b 和 c 。
 *
 * 给你一个整数 n ，请你返回满足 1 <= a, b, c <= n 的 平方和三元组 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 5
 * 输出：2
 * 解释：平方和三元组为 (3,4,5) 和 (4,3,5) 。
 * 示例 2：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：平方和三元组为 (3,4,5)，(4,3,5)，(6,8,10) 和 (8,6,10) 。
 *
 *
 * 提示：
 *
 * 1 <= n <= 250
 * @author chenzw
 * @date 2021/7/11
 */
public class Solution5792 {
    public int countTriples(int n) {
        int count = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                for(int k=1;k<=n;k++){
                    if(i*i+j*j==k*k)count++;
                }
            }
        }
        return count;
    }
}
