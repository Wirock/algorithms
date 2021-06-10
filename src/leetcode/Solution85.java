package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 *
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 *
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 *
 * 输入：matrix = [["0","0"]]
 * 输出：0
 *
 *
 * 提示：
 *
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 * @author chenzw
 * @date 2021/6/11
 */
public class Solution85 {
    public static int maximalRectangle(char[][] matrix) {
        if(matrix.length==0)return 0;
        int[] heights = new int[matrix[0].length+1];//多一位0，减少maxArea方法种的判断
        int ans = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                heights[j] = matrix[i][j]=='0'?0:heights[j]+1;//若为0，则备断开了，高重新累加
            }
            ans = Math.max(ans,maxArea(heights));
        }
        return ans;
    }
    private static int maxArea(int[] heights){
        Deque<Integer> stack = new LinkedList<>();
        int area = 0;
        for(int right=0;right<heights.length;right++){
            while(!stack.isEmpty()&&heights[stack.peek()]>=heights[right]){
                int cur = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();
                area = Math.max(area,heights[cur]*(right-left-1));
            }
            stack.push(right);
        }
        return area;
    }

    public static void main(String[] args) {
        System.out.println(maximalRectangle(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
        System.out.println(maximalRectangle(new char[][]{}));
        System.out.println(maximalRectangle(new char[][]{{'1'}}));
        System.out.println(maximalRectangle(new char[][]{{'0','0'}}));
    }
}
