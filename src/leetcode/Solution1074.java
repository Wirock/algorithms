package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1074. 元素和为目标值的子矩阵数量
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 *
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 *
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * 输出：4
 * 解释：四个只含 0 的 1x1 子矩阵。
 * 示例 2：
 *
 * 输入：matrix = [[1,-1],[-1,1]], target = 0
 * 输出：5
 * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 * 示例 3：
 *
 * 输入：matrix = [[904]], target = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= matrix.length <= 100
 * 1 <= matrix[0].length <= 100
 * -1000 <= matrix[i] <= 1000
 * -10^8 <= target <= 10^8
 * @author chenzw
 * @date 2021/5/29
 */
public class Solution1074 {
    //前缀和暴力解法
    /*public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int[][] sum = new int[matrix.length+1][matrix[0].length+1];
        sum[1][1] = matrix[0][0];
        int count = 0;
        for(int i=1;i<=matrix.length;i++){
            for(int j=1;j<=matrix[0].length;j++){
                sum[i][j] = sum[i][j-1]+sum[i-1][j]-sum[i-1][j-1]+matrix[i-1][j-1];
            }
        }
        for(int i=0;i<=matrix.length-1;i++){
            for(int j=0;j<=matrix[0].length-1;j++){
                for(int m=i+1;m<=matrix.length;m++){
                    for(int n=j+1;n<=matrix[0].length;n++){
                        if(sum[m][n]-sum[i][n]-sum[m][j]+sum[i][j]==target)count++;
                    }
                }
            }
        }
        return count;
    }*/

    //前缀和，使用hash优化
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int count = 0;
        for(int i=0;i<matrix.length;i++){//枚举上界
            int[] sum = new int[matrix[0].length];
            for(int j=i;j<matrix.length;j++){//枚举下界
                for(int k=0;k<sum.length;k++){
                    sum[k]+=matrix[j][k];
                }
                Map<Integer,Integer> countMap = new HashMap<>();
                int pre = 0;
                for(int k=0;k<sum.length;k++){
                    countMap.put(pre,countMap.getOrDefault(pre,0)+1);
                    pre+=sum[k];
                    count+=countMap.getOrDefault(pre-target,0);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numSubmatrixSumTarget(new int[][]{{0,1,0},{1,1,1},{0,1,0}},0));
        System.out.println(numSubmatrixSumTarget(new int[][]{{1,-1},{-1,1}},0));
        System.out.println(numSubmatrixSumTarget(new int[][]{{904}},0));
    }
}
