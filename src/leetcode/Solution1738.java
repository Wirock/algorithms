package leetcode;

import java.util.Arrays;

/**
 * 1738. 找出第 K 大的异或坐标值
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 *
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 *
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * 示例 2：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * 示例 3：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * 示例 4：
 *
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 106
 * 1 <= k <= m * n
 * @author chenzw
 * @date 2021/5/19
 */
public class Solution1738 {
    public static int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] ans = new int[m*n];
        int index = 0;
        ans[index++] = matrix[0][0];
        for(int i=1;i<m;i++){
            matrix[i][0]^=matrix[i-1][0];
            ans[index++] = matrix[i][0];
        }
        for(int j=1;j<n;j++){
            matrix[0][j]^=matrix[0][j-1];
            ans[index++] =  matrix[0][j];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                matrix[i][j]^=matrix[i-1][j-1];
                matrix[i][j]^=matrix[i-1][j];
                matrix[i][j]^=matrix[i][j-1];
                ans[index++]=matrix[i][j];
            }
        }
        Arrays.sort(ans);
        return ans[ans.length-k];
    }

    public static void main(String[] args) {
        System.out.println(kthLargestValue(new int[][]{{5,2},{1,6}},1));
    }
}
