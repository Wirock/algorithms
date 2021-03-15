package leetcode;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * @author chenzw
 * @date 2021/3/16
 */
public class Solution59 {
    public static int[][] generateMatrix(int n) {
        int[][] matric = new int[n][n];
        int num = 1;
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            if (start == end) {
                matric[start][start] = num;
                break;
            }
            for (int i = start; i < end; i++) matric[start][i] = num++;
            for (int i = start; i < end; i++) matric[i][end] = num++;
            for (int i = end; i > start; i--) matric[end][i] = num++;
            for (int i = end; i > start; i--) matric[i][start] = num++;
            start++;
            end--;
        }
        return matric;
    }

    public static void main(String[] args) {
        int[][] matric = generateMatrix(0);
        for (int i = 0; i < matric.length; i++) {
            System.out.println(Arrays.toString(matric[i]));
        }
    }
}
