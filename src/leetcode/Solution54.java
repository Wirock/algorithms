package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素
 *
 * @author chenzw
 * @date 2021/3/15
 */
public class Solution54 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;
            if (left <= right) {
                for (int i = top; i <= bottom; i++) {
                    list.add(matrix[i][right]);
                }
                right--;
            }
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                left++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(spiralOrder(new int[][]{new int[]{1, 2, 3}, new int[]{6, 5, 4}, new int[]{7, 8, 9}}).toArray()));
        System.out.println(Arrays.toString(spiralOrder(new int[][]{new int[]{1, 2, 3}, new int[]{6, 5, 4}}).toArray()));
        System.out.println(Arrays.toString(spiralOrder(new int[][]{new int[]{1, 2, 3}}).toArray()));
        System.out.println(Arrays.toString(spiralOrder(new int[][]{new int[]{1}, new int[]{2}, new int[]{3}}).toArray()));
        System.out.println(Arrays.toString(spiralOrder(new int[][]{new int[]{}, new int[]{}, new int[]{}}).toArray()));
    }
}
