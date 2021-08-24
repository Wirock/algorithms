package leetcode;

/**
 * 787. K 站中转内最便宜的航班
 * 有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 *
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 *
 *
 *
 * 示例 1：
 *
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * 输出: 200
 * 解释:
 * 城市航班图如下
 *
 *
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
 * 示例 2：
 *
 * 输入:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * 输出: 500
 * 解释:
 * 城市航班图如下
 *
 *
 * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。
 *
 *
 * 提示：
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * 航班没有重复，且不存在自环
 * 0 <= src, dst, k < n
 * src != dst
 * @author chenzw
 * @date 2021/8/24
 */
public class Solution787 {
    //bfs
    /*public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] edges = new List[n];
        for(int i=0;i<n;i++)edges[i]=new ArrayList<>();
        for(int[] f:flights){
            edges[f[0]].add(new int[]{f[1],f[2]});
        }
        int[] fee = new int[n];
        Arrays.fill(fee,Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src,0});
        while(!queue.isEmpty()&&k+1>=0){
            int size = queue.size();
            while(size-->0){
                int[] df = queue.poll();
                fee[df[0]] = Math.min(fee[df[0]],df[1]);
                if(df[0]==dst)continue;//剪枝1，已到达终点，无需继续
                for(int[] e:edges[df[0]]){
                    int sum = df[1]+e[1];
                    if(sum<fee[e[0]])queue.offer(new int[]{e[0],sum});//剪枝1，到达该点的路线比已有最优路线费用多的不考虑
                }
            }
            k--;
        }
        return fee[dst]==Integer.MAX_VALUE?-1:fee[dst];
    }*/

    //dp,dp[i][j]表示经过i趟航班，到达j的最小费用
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int MAX = (int)1e6+2;
        int[][] dp = new int[k+2][n];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<n;j++){
                dp[i][j] = MAX;
            }
        }
        dp[0][src] = 0;

        for(int i=1;i<dp.length;i++){
            for(int[] f:flights){
                dp[i][f[1]] = Math.min(dp[i][f[1]],dp[i-1][f[0]]+dp[i][f[1]]);
            }
        }
        int ans = MAX;
        for(int i=1;i<dp.length;i++){
            ans = Math.min(ans,dp[i][dst]);
        }
        return ans==MAX?-1:ans;
    }
}
