package leetcode;

/**
 * 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 *
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 *
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 * @author chenzw
 * @date 2021/3/21
 */
public class Solution73 {
    public static void setZeroes(int[][] matrix) {
        //使用第0列来记录行是否为0，第0行来记录列是否为0，交叉点matrix[0][0]用于记录第0行，故还需要一个额外标记来标记第0列
        boolean firstColumn0 = false;
        for(int i=0;i<matrix.length;i++){
            if(matrix[i][0]==0) firstColumn0 = true;
            for(int j=1;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    matrix[i][0] = matrix[0][j]=0;
                }
            }
        }
        //其他行列需要根据第0行和第0列来判断，故这里要倒序，把第0行和第0列放在最后处理
        for(int i=matrix.length-1;i>=0;i--){
            for(int j=1;j<matrix[0].length;j++){
                if(matrix[i][0]==0||matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
            if(firstColumn0) matrix[i][0]=0;
        }
    }

    public static void main(String[] args) {

    }
}
