package leetcode;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 *
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 示例 2：
 *
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 *
 *
 * 进阶：如果行数远大于列数，该如何设计解决方案？
 * @author chenzw
 * @date 2021/4/22
 */
public class Solution363 {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
       int[][] sums = new int[matrix.length][matrix[0].length];
        sums[0][0]=matrix[0][0];
        for(int i=1;i<matrix.length;i++){
            sums[i][0] = sums[i-1][0]+matrix[i][0];
        }
        for(int i=1;i<matrix[0].length;i++){
            sums[0][i] = sums[0][i-1]+matrix[0][i];
        }
        for(int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[0].length;j++){
                sums[i][j] = sums[i][j-1]+sums[i-1][j]-sums[i-1][j-1]+matrix[i][j];
            }
        }
        int sum = Integer.MIN_VALUE;
        for(int row1=0;row1<matrix.length;row1++){
            for(int col1=0;col1<matrix[0].length;col1++){
                for(int row2=row1;row2<matrix.length;row2++){
                    for(int col2=col1;col2<matrix[0].length;col2++){
                        int s = sums[row2][col2]-(col1>0?sums[row2][col1-1]:0)-(row1>0?sums[row1-1][col2]:0)+(row1>0&&col1>0?sums[row1-1][col1-1]:0);
                        if(s<=k&&s>sum){
                            sum = s;
                        }
                    }
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,0,1},{0,-2,3}};
        int k = 2;
        System.out.println(maxSumSubmatrix(matrix,k));
    }
}
