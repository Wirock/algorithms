package leetcode;

import common.CommonUtil;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 *
 * Range Sum Query 2D
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 *
 *
 *
 * 示例：
 *
 * 给定 matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 *
 *
 * 提示：
 *
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
 * @author chenzw
 * @date 2021/4/22
 */
public class Solution304 {

    static class NumMatrix {
        int[][] sums;
        public NumMatrix(int[][] matrix) {
            sums = new int[matrix.length][matrix[0].length];
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
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
           return sums[row2][col2]-(col1>0?sums[row2][col1-1]:0)-(row1>0?sums[row1-1][col2]:0)+(row1>0&&col1>0?sums[row1-1][col1-1]:0);
        }
    }

    public static void main(String[] args) {
        /*int[][] matrix = {{3, 0, 1, 4, 2},
                            {5, 6, 3, 2, 1},
                            {1, 2, 0, 1, 5},
                            {4, 1, 0, 1, 7},
                            {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
        System.out.println(numMatrix.sumRegion(0, 0, 0, 0));
        System.out.println(numMatrix.sumRegion(4, 4, 4, 4));*/
        int[][] matrix = {{-4,-5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        CommonUtil.printArray(numMatrix.sums);
        System.out.println(numMatrix.sumRegion(0, 0, 0, 0));
        System.out.println(numMatrix.sumRegion(0, 0, 0, 1));
        System.out.println(numMatrix.sumRegion(0, 1, 0, 1));
    }
}
