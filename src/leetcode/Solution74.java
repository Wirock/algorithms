package leetcode;

/**
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * @author chenzw
 * @date 2021/3/30
 */
public class Solution74 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = findRow(matrix, target, 0, matrix.length - 1);
        return hasNum(matrix[row],target,0,matrix[row].length-1);
    }

    private static int findRow(int[][] matrix, int target,int top,int bottom) {
        if(top==bottom)return top;
        int mid = (top+bottom)/2;
        if(matrix[mid][0]>target&&mid-1>=top){
            return findRow(matrix,target,top,mid-1);
        }else if(matrix[mid+1][0]<=target&&mid+1<=bottom){
            return findRow(matrix,target,mid+1,bottom);
        }
        return mid;
    }
    private static boolean hasNum(int[] row, int target,int left,int right) {
        if(left==right)return row[left]==target;
        int mid = (left+right)/2;
        if(row[mid]==target)return true;
        if(row[mid]>target&&mid-1>=left){
            return hasNum(row,target,left,mid-1);
        }else if(row[mid+1]<=target&&mid+1<=right){
            return hasNum(row,target,mid+1,right);
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(matrix,61));
        matrix = new int[][]{{1,2,3}};
        System.out.println(searchMatrix(matrix,2));
    }
}
