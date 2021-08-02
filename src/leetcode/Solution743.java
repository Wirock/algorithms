package leetcode;

import java.util.*;

/**
 * 743. 网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 *
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * 示例 3：
 *
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 * @author chenzw
 * @date 2021/8/2
 */
public class Solution743 {
    //Dijkstra
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] edges = new List[n];
        for(int[] time:times){
            if(edges[time[0]-1]==null)edges[time[0]-1] = new ArrayList<>();
            edges[time[0]-1].add(new int[]{time[1],time[2]});
        }
        int[] distance = new int[n];
        Arrays.fill(distance,Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);
        distance[k-1] = 0;
        while(!queue.isEmpty()){
            int x = queue.poll();
            if(edges[x-1]==null)continue;
            for(int[] t:edges[x-1]){
                int dis = distance[x-1]+t[1];
                if(dis<distance[t[0]-1]){
                    distance[t[0]-1] = dis;
                    queue.offer(t[0]);
                }
            }
        }
        int ans = Arrays.stream(distance).max().getAsInt();
        return ans==Integer.MAX_VALUE?-1:ans;
    }
}
