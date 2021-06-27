package leetcode;

import java.util.*;

/**815. 公交路线
 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。

 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。

 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。



 示例 1：

 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 输出：2
 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 示例 2：

 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 输出：-1


 提示：

 1 <= routes.length <= 500.
 1 <= routes[i].length <= 105
 routes[i] 中的所有值 互不相同
 sum(routes[i].length) <= 105
 0 <= routes[i][j] < 106
 0 <= source, target < 106
 *
 * @author chenzw
 * @date 2021/6/28
 */
public class Solution815 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source==target)return 0;
        int n=routes.length;
        boolean[][] edge = new boolean[n][n];//edge[i][j]表示i,j两个路线是否换乘
        Map<Integer, List<Integer>> rec = new HashMap<>();//记录每个站点的所有可选路线
        for(int i=0;i<n;i++){
            for(int site:routes[i]){
                List<Integer> list = rec.getOrDefault(site, new ArrayList<>());
                for(int j:list){
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                rec.put(site,list);
            }
        }

        int[] dis = new int[n];//记录source到每个站点需要乘坐的公交车数量
        Arrays.fill(dis,-1);
        //bfs
        //乘做一辆公交车可以到达的所有节点入栈
        Queue<Integer> queue = new LinkedList<>();
        for(int site:rec.getOrDefault(source,new ArrayList<>())){
            dis[site] = 1;
            queue.offer(site);
        }
        //bfs搜索
        while(!queue.isEmpty()){
            int x = queue.poll();
            for(int y=0;y<n;y++){
                if(edge[x][y]&&dis[y]==-1){
                    dis[y] = dis[x]+1;
                    queue.offer(y);
                }
            }
        }
        //最后找出目标车站乘坐一辆公交可达的所有站点
        //遍历找出从起始点到上述站点的最小值乘车数，即为答案
        int ans = Integer.MAX_VALUE;
        for(int site:rec.getOrDefault(target,new ArrayList<>())){
            if(dis[site]!=-1){
                ans = Math.min(ans,dis[site]);
            }
        }
        return ans == Integer.MAX_VALUE?-1:ans;
    }
}
