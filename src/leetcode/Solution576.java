package leetcode;

/**
 * 576. 出界的路径数
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 *
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * 输出：6
 * 示例 2：
 *
 *
 * 输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * 输出：12
 *
 *
 * 提示：
 *
 * 1 <= m, n <= 50
 * 0 <= maxMove <= 50
 * 0 <= startRow < m
 * 0 <= startColumn < n
 * @author chenzw
 * @date 2021/8/15
 */
public class Solution576 {
    //dp+bfs
    //dp[i][j]表示在maxMove步以内到达坐标(i,j)的路径数量
    /*public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if(maxMove==0)return 0;
        int mod = (int)1e9+7;
        int ans = 0;
        int[][][] dp = new int[m][n][maxMove];
        dp[startRow][startColumn][0] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow,startColumn});
        int move = 0;
        Set<Integer> visited = new HashSet<>();
        while(!queue.isEmpty()&& move<maxMove){
            int size = queue.size();
            visited.clear();
            for(int i=0;i<size;i++){
                int[] rc = queue.poll();
                int r = rc[0];
                int c = rc[1];
                //坐标可重复走
                if(r>0) {
                    if(move>0)dp[r][c][move] = (int)(((long)dp[r][c][move] + dp[r - 1][c][move-1])%mod);
                    int key = (r-1)*51+c;
                    if(!visited.contains(key)) {
                        queue.add(new int[]{r - 1, c});
                        visited.add(key);
                    }
                }
                if(r<m-1) {
                    if(move>0)dp[r][c][move] = (int)(((long)dp[r][c][move]+dp[r + 1][c][move-1])%mod);
                    int key = (r+1)*51+c;
                    if(!visited.contains(key)) {
                        queue.add(new int[]{r + 1, c});
                        visited.add(key);
                    }
                }
                if(c>0) {
                    if(move>0)dp[r][c][move] = (int)(((long)dp[r][c][move] + dp[r][c - 1][move-1])%mod);
                    int key = r*51+c-1;
                    if(!visited.contains(key)) {
                        queue.add(new int[]{r, c - 1});
                        visited.add(key);
                    }
                }
                if(c<n-1) {
                    if(move>0)dp[r][c][move] = (int)(((long)dp[r][c][move] + dp[r][c+1][move-1])%mod);
                    int key = r*51+c+1;
                    if(!visited.contains(key)) {
                        queue.add(new int[]{r, c + 1});
                        visited.add(key);
                    }
                }
                if(r==0)ans = (int)(((long)ans+dp[r][c][move])%mod);
                if(r==m-1)ans = (int)(((long)ans+dp[r][c][move])%mod);
                if(c==0)ans = (int)(((long)ans+dp[r][c][move])%mod);
                if(c==n-1)ans = (int)(((long)ans+dp[r][c][move])%mod);
            }
            move++;
        }
        return ans;
     }*/

    //dp,滚动数组优化
    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if(maxMove==0)return 0;
        int mod = (int)1e9+7;
        int ans = 0;
        int[][][] dp = new int[2][m][n];
        int[][] direction = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        dp[0][startRow][startColumn] = 1;
        for(int k=0;k<maxMove;k++){
            int cur = k%2;
            int next = (k+1)%2;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(dp[cur][i][j]>0){
                        for(int d=0;d<direction.length;d++){
                            int r = i+direction[d][0];
                            int c = j+direction[d][1];
                            if(r>=0&&r<m&&c>=0&&c<n)dp[next][r][c] = (dp[next][r][c]+dp[cur][i][j])%mod;
                            else ans = (ans + dp[cur][i][j])%mod;
                        }
                    }
                }
            }
            dp[cur] = new int[m][n];
        }
        return ans;
    }

    public static void main(String[] args) {
        //System.out.println(findPaths(2,2,2,0,0));
        //System.out.println(findPaths(3,3,4,1,1));
        System.out.println(findPaths(10,10,0,5,5));
    }
}
