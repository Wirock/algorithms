package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 847. 访问所有节点的最短路径
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
 *
 * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
 *
 * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：graph = [[1,2,3],[0],[0],[0]]
 * 输出：4
 * 解释：一种可能的路径为 [1,0,2,0,3]
 * 示例 2：
 *
 *
 *
 * 输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * 输出：4
 * 解释：一种可能的路径为 [0,1,4,2,3]
 *
 *
 * 提示：
 *
 * n == graph.length
 * 1 <= n <= 12
 * 0 <= graph[i].length < n
 * graph[i] 不包含 i
 * 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
 * 输入的图总是连通图
 * @author chenzw
 * @date 2021/8/6
 */
public class Solution847 {

    //bfs+状态压缩
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;

        // 1.初始化队列及标记数组，存入起点
        Queue<int[]> queue = new LinkedList<int[]>(); // 三个属性分别为 idx, mask, dist
        boolean[][] vis = new boolean[n][1 << n]; // 节点编号及当前状态
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i, 0}); // 存入起点，起始距离0，标记
            vis[i][1 << i] = true;
        }

        // 开始搜索
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll(); // 弹出队头元素
            int idx = tuple[0], mask = tuple[1], dist = tuple[2];

            // 找到答案，返回结果
            if (mask == (1 << n) - 1) return dist;

            // 扩展
            for (int x : graph[idx]) {
                int next_mask = mask | (1 << x);
                if (!vis[x][next_mask]) {
                    queue.offer(new int[]{x, next_mask, dist + 1});
                    vis[x][next_mask] = true;
                }
            }
        }
        return 0;
    }
}
