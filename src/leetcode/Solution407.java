package leetcode;

import java.util.PriorityQueue;

/**
 * 407. 接雨水 II
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 * 示例 2:
 *
 *
 *
 * 输入: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * 输出: 10
 *
 *
 * 提示:
 *
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 *
 * @author chenzw
 * @date 2021/11/3
 */
public class Solution407 {

    //优先队列，木桶原理
    //从外圈最短的位置往内向相邻的位置扩散，比外圈低的填充雨水，比外圈位置高的不变，然后让这个位置代替外圈位置组成新的外圈
    //重复上述操作，直到所有点位都被访问
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)->x[2]-y[2]);
        boolean[][] visited = new boolean[m][n];
        for(int i=0;i<m;i++){
            visited[i][0] = true;
            pq.offer(new int[]{i,0,heightMap[i][0]});
            visited[i][n-1] = true;
            pq.offer(new int[]{i,n-1,heightMap[i][n-1]});
        }
        for(int i=0;i<n;i++){
            visited[0][i] = true;
            pq.offer(new int[]{0,i,heightMap[0][i]});
            visited[m-1][i] = true;
            pq.offer(new int[]{m-1,i,heightMap[m-1][i]});
        }

        int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        int ans = 0;
        while(!pq.isEmpty()){
            int[] pos = pq.poll();
            for(int[] d:dir){
                int x = pos[0]+d[0];
                int y = pos[1]+d[1];
                if(x<0||x>=m-1||y<=0||y>=n-1||visited[x][y])continue;
                visited[x][y] = true;
                if(pos[2]>heightMap[x][y])ans+=pos[2]-heightMap[x][y];
                pq.offer(new int[]{x,y,Math.max(pos[2],heightMap[x][y])});
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution407().trapRainWater(new int[][]{{4,5,6,7,8,9},{5,1,2,3,4,5},{6,3,3,2,2,1}}));
    }
}
