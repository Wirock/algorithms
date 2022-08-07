package leetcode.weekly.contest305;

import java.util.*;

/**
 * 6139. 受限条件下可到达节点的数目
 * 现有一棵由 n 个节点组成的无向树，节点编号从 0 到 n - 1 ，共有 n - 1 条边。
 *
 * 给你一个二维整数数组 edges ，长度为 n - 1 ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。另给你一个整数数组 restricted 表示 受限 节点。
 *
 * 在不访问受限节点的前提下，返回你可以从节点 0 到达的 最多 节点数目。
 *
 * 注意，节点 0 不 会标记为受限节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
 * 输出：4
 * 解释：上图所示正是这棵树。
 * 在不访问受限节点的前提下，只有节点 [0,1,2,3] 可以从节点 0 到达。
 * 示例 2：
 *
 *
 * 输入：n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
 * 输出：3
 * 解释：上图所示正是这棵树。
 * 在不访问受限节点的前提下，只有节点 [0,5,6] 可以从节点 0 到达。
 *
 *
 * 提示：
 *
 * 2 <= n <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges 表示一棵有效的树
 * 1 <= restricted.length < n
 * 1 <= restricted[i] < n
 * restricted 中的所有值 互不相同
 * @author chenzw
 * @date 2022/8/7
 */
public class Solution6139 {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] ed = new List[n];
        for(int i=0;i<n;i++){
            ed[i] = new ArrayList<>();
        }
        for(int[] e:edges){
            ed[e[0]].add(e[1]);
            ed[e[1]].add(e[0]);
        }
        Set<Integer> re = new HashSet<>(restricted.length);
        for(int r:restricted){
            re.add(r);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[n];
        int ans = 0;
        while(!queue.isEmpty()){
            int k = queue.poll();
            if(visited[k]||re.contains(k)){
                continue;
            }
            ans ++;
            visited[k] = true;
            for(int i:ed[k]){
                queue.offer(i);
            }
        }
        return ans;
    }
}
