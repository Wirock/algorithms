package leetcode;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 * @author chenzw
 * @date 2021/10/25
 */
public class Solution240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int floor = floor(matrix[m-1], target);
        int ceil = ceil(matrix[0], target);
        if(floor<0||ceil<0)return false;
        for(int i=floor;i<=ceil;i++){
            int left = 0;
            int right = m-1;
            while(left<right){
                int mid = (left+right)>>1;
                if(matrix[mid][i]==target)return true;
                if(matrix[mid][i]<target)left=mid+1;
                else right=mid-1;
            }
            if(matrix[left][i]==target)return true;
        }
        return false;
    }

    private int ceil(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
            int mid = (left+right)>>1;
            if(arr[mid]<=target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return right;
    }

    private int floor(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
            int mid = (left+right)>>1;
            if(arr[mid]>=target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return left<arr.length?left:-1;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{4,8,12},{5,10,15},{7,12,16},{9,13,17},{15,16,18}};
        System.out.println(new Solution240().searchMatrix(matrix,4));
        System.out.println(new Solution240().searchMatrix(matrix,8));
        System.out.println(new Solution240().searchMatrix(matrix,15));
        System.out.println(new Solution240().searchMatrix(matrix,10));
        System.out.println(new Solution240().searchMatrix(matrix,16));
        System.out.println(new Solution240().searchMatrix(matrix,21));
    }
}
