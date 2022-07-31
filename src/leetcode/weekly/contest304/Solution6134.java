package leetcode.weekly.contest304;

import java.util.Arrays;

/**
 * 6134. 找到离给定两个节点最近的节点
 * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，每个节点 至多 有一条出边。
 *
 * 有向图用大小为 n 下标从 0 开始的数组 edges 表示，表示节点 i 有一条有向边指向 edges[i] 。如果节点 i 没有出边，那么 edges[i] == -1 。
 *
 * 同时给你两个节点 node1 和 node2 。
 *
 * 请你返回一个从 node1 和 node2 都能到达节点的编号，使节点 node1 和节点 node2 到这个节点的距离 较大值最小化。如果有多个答案，请返回 最小 的节点编号。如果答案不存在，返回 -1 。
 *
 * 注意 edges 可能包含环。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：edges = [2,2,3,-1], node1 = 0, node2 = 1
 * 输出：2
 * 解释：从节点 0 到节点 2 的距离为 1 ，从节点 1 到节点 2 的距离为 1 。
 * 两个距离的较大值为 1 。我们无法得到一个比 1 更小的较大值，所以我们返回节点 2 。
 * 示例 2：
 *
 *
 *
 * 输入：edges = [1,2,-1], node1 = 0, node2 = 2
 * 输出：2
 * 解释：节点 0 到节点 2 的距离为 2 ，节点 2 到它自己的距离为 0 。
 * 两个距离的较大值为 2 。我们无法得到一个比 2 更小的较大值，所以我们返回节点 2 。
 *
 *
 * 提示：
 *
 * n == edges.length
 * 2 <= n <= 105
 * -1 <= edges[i] < n
 * edges[i] != i
 * 0 <= node1, node2 < n
 * @author chenzw
 * @date 2022/7/31
 */
public class Solution6134 {
    //统计距离，比较
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        if(node1==node2){
            return node1;
        }
        int n = edges.length;
        int[] d1 = getDistance(edges, node1);
        int[] d2 = getDistance(edges, node2);

        int min = Integer.MAX_VALUE;
        int index = -1;
        for(int i=0;i<n;i++){
            if(d1[i]>-1&&d2[i]>-1){
                int m = Math.max(d1[i],d2[i]);
                if(m<min){
                    min = m;
                    index = i;
                }
            }
        }
        return index;
    }

    private int[] getDistance(int[] edges, int node){
        int[] d = new int[edges.length];
        Arrays.fill(d,-1);
        d[node] = 0;
        int cur = node;
        int next = edges[cur];
        while(next>-1&&d[cur]>-1){
            if(d[next]>-1){
                break;
            }
            d[next] = d[cur]+1;
            cur=next;
            next=edges[next];
        }
        return d;
    }
}
