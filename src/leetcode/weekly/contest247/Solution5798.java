package leetcode.weekly.contest247;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 5798. 循环轮转矩阵
 * 给你一个大小为 m x n 的整数矩阵 grid​​​ ，其中 m 和 n 都是 偶数 ；另给你一个整数 k 。
 *
 * 矩阵由若干层组成，如下图所示，每种颜色代表一层：
 *
 *
 *
 * 矩阵的循环轮转是通过分别循环轮转矩阵中的每一层完成的。在对某一层进行一次循环旋转操作时，层中的每一个元素将会取代其 逆时针 方向的相邻元素。轮转示例如下：
 *
 *
 * 返回执行 k 次循环轮转操作后的矩阵。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[40,10],[30,20]], k = 1
 * 输出：[[10,20],[40,30]]
 * 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
 * 示例 2：
 *
 *
 * 输入：grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
 * 输出：[[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
 * 解释：上图展示了矩阵在执行循环轮转操作时每一步的状态。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 50
 * m 和 n 都是 偶数
 * 1 <= grid[i][j] <= 5000
 * 1 <= k <= 109
 * @author chenzw
 * @date 2021/6/27
 */
public class Solution5798 {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int top = 0;
        int bottom = m-1;
        int left = 0;
        int right = n-1;
        Queue<Integer> queue = new LinkedList<>();
        while(top<bottom&&left<right){
            for(int i=left;i<=right;i++)queue.add(grid[top][i]);
            for(int i=top+1;i<=bottom;i++)queue.add(grid[i][right]);
            for(int i=right-1;i>=left;i--)queue.add(grid[bottom][i]);
            for(int i=bottom-1;i>=top+1;i--)queue.add(grid[i][left]);
            int count = k%(2*(m-1)+2*(n-1));
            while(count-->0)queue.add(queue.poll());
            for(int i=left;i<=right;i++)grid[top][i] = queue.poll();
            for(int i=top+1;i<=bottom;i++)grid[i][right] = queue.poll();
            for(int i=right-1;i>=left;i--)grid[bottom][i] = queue.poll();
            for(int i=bottom-1;i>=top+1;i--)grid[i][left] = queue.poll();
            top++;
            left++;
            bottom--;
            right--;
            m-=2;
            n-=2;
        }
        return grid;
    }
}
