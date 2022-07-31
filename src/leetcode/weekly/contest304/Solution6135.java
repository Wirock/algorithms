package leetcode.weekly.contest304;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 6135. 图中的最长环
 * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，其中每个节点 至多 有一条出边。
 *
 * 图用一个大小为 n 下标从 0 开始的数组 edges 表示，节点 i 到节点 edges[i] 之间有一条有向边。如果节点 i 没有出边，那么 edges[i] == -1 。
 *
 * 请你返回图中的 最长 环，如果没有任何环，请返回 -1 。
 *
 * 一个环指的是起点和终点是 同一个 节点的路径。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：edges = [3,3,4,2,3]
 * 输出去：3
 * 解释：图中的最长环是：2 -> 4 -> 3 -> 2 。
 * 这个环的长度为 3 ，所以返回 3 。
 * 示例 2：
 *
 *
 *
 * 输入：edges = [2,-1,3,1]
 * 输出：-1
 * 解释：图中没有任何环。
 *
 *
 * 提示：
 *
 * n == edges.length
 * 2 <= n <= 105
 * -1 <= edges[i] < n
 * edges[i] != i
 * @author chenzw
 * @date 2022/7/31
 */
public class Solution6135 {
    //统计入度，bfs
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] in = new int[n];
        for(int i=0;i<n;i++){
            if(edges[i]>-1){
                in[edges[i]]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(in[i]==0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int k = queue.poll();
            if(edges[k]==-1){
                continue;
            }
            in[edges[k]]--;
            if(in[edges[k]]==0){
                queue.offer(edges[k]);
            }
        }
        int max = -1;
        int[] visited = new int[n];
        for(int i = 0;i<n;i++){
            if(in[i]>0&&visited[i]==0){
                int count = 1;
                visited[i] = 1;
                int next = edges[i];
                while(next>-1&&visited[next]==0){
                    count++;
                    visited[next] = 1;
                    next = edges[next];
                }
                max = Math.max(max,count);
            }
        }
        return max;
    }
}
