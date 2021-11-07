package leetcode.weekly.contest266;

import java.util.LinkedList;
import java.util.List;

/**
 * 5921. 最大化一张图中的路径价值 显示英文描述
 * 通过的用户数0
 * 尝试过的用户数0
 * 用户总通过次数0
 * 用户总提交次数0
 * 题目难度Hard
 * 给你一张 无向 图，图中有 n 个节点，节点编号从 0 到 n - 1 （都包括）。同时给你一个下标从 0 开始的整数数组 values ，其中 values[i] 是第 i 个节点的 价值 。同时给你一个下标从 0 开始的二维整数数组 edges ，其中 edges[j] = [uj, vj, timej] 表示节点 uj 和 vj 之间有一条需要 timej 秒才能通过的无向边。最后，给你一个整数 maxTime 。
 *
 * 合法路径 指的是图中任意一条从节点 0 开始，最终回到节点 0 ，且花费的总时间 不超过 maxTime 秒的一条路径。你可以访问一个节点任意次。一条合法路径的 价值 定义为路径中 不同节点 的价值 之和 （每个节点的价值 至多 算入价值总和中一次）。
 *
 * 请你返回一条合法路径的 最大 价值。
 *
 * 注意：每个节点 至多 有 四条 边与之相连。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
 * 输出：75
 * 解释：
 * 一条可能的路径为：0 -> 1 -> 0 -> 3 -> 0 。总花费时间为 10 + 10 + 10 + 10 = 40 <= 49 。
 * 访问过的节点为 0 ，1 和 3 ，最大路径价值为 0 + 32 + 43 = 75 。
 * 示例 2：
 *
 *
 *
 * 输入：values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
 * 输出：25
 * 解释：
 * 一条可能的路径为：0 -> 3 -> 0 。总花费时间为 10 + 10 = 20 <= 30 。
 * 访问过的节点为 0 和 3 ，最大路径价值为 5 + 20 = 25 。
 * 示例 3：
 *
 *
 *
 * 输入：values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
 * 输出：7
 * 解释：
 * 一条可能的路径为：0 -> 1 -> 3 -> 1 -> 0 。总花费时间为 10 + 13 + 13 + 10 = 46 <= 50 。
 * 访问过的节点为 0 ，1 和 3 ，最大路径价值为 1 + 2 + 4 = 7 。
 * 示例 4：
 *
 *
 *
 * 输入：values = [0,1,2], edges = [[1,2,10]], maxTime = 10
 * 输出：0
 * 解释：
 * 唯一一条路径为 0 。总花费时间为 0 。
 * 唯一访问过的节点为 0 ，最大路径价值为 0 。
 *
 *
 * 提示：
 *
 * n == values.length
 * 1 <= n <= 1000
 * 0 <= values[i] <= 108
 * 0 <= edges.length <= 2000
 * edges[j].length == 3
 * 0 <= uj < vj <= n - 1
 * 10 <= timej, maxTime <= 100
 * [uj, vj] 所有节点对 互不相同 。
 * 每个节点 至多有四条 边。
 * 图可能不连通。
 * @author chenzw
 * @date 2021/11/7
 */
public class Solution5921 {
    //bfs,空间超出限制
    /*public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n=values.length;
        List<int[]>[] edgesList = new List[n];
        for(int i=0;i<n;i++){
            edgesList[i] = new LinkedList();
        }
        for(int[] e:edges){
            edgesList[e[0]].add(new int[]{e[1],e[2]});
            edgesList[e[1]].add(new int[]{e[0],e[2]});
        }
        int maxValue = 0;
        Queue<Node> queue = new LinkedList();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        queue.add(new Node(0,0,values[0],visited));
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.time>maxTime)continue;
            if(node.index==0)maxValue = Math.max(node.value,maxValue);
            for(int[] next:edgesList[node.index]){
                boolean[] nextVisited = new boolean[n];
                for(int i=0;i<n;i++){
                    nextVisited[i]=node.visited[i];
                }
                int nextValue = node.value;
                if(!nextVisited[next[0]]) {
                    nextValue += values[next[0]];
                    nextVisited[next[0]]=true;
                }
                queue.add(new Node(next[0],node.time+next[1],nextValue,nextVisited));
            }
        }
        return maxValue;
    }
    class Node{
        int index;
        int time;
        int value;
        boolean[] visited;

        public Node(int index, int time, int value, boolean[] visited) {
            this.index = index;
            this.time = time;
            this.value = value;
            this.visited = visited;
        }
    }*/

    //dfs回溯
    int[] count;
    int max = 0;
    List<int[]>[] edgesList;
    int[] values;
    int maxTime;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n=values.length;
        count = new int[n];
        this.values=values;
        this.maxTime = maxTime;
        edgesList = new List[n];
        for(int i=0;i<n;i++){
            edgesList[i] = new LinkedList();
        }
        for(int[] e:edges){
            edgesList[e[0]].add(new int[]{e[1],e[2]});
            edgesList[e[1]].add(new int[]{e[0],e[2]});
        }
        count[0]++;
        dfs(0,0,values[0]);
        return max;
    }

    private void dfs(int i,int time,int value){
        if(time>maxTime)return;
        if(i==0)max = Math.max(value,max);
        for(int[] next:edgesList[i]){
            int nextValue = value;
            if(count[next[0]]==0)nextValue+=values[next[0]];
            count[next[0]]++;
            dfs(next[0],time+next[1],nextValue);
            count[next[0]]--;
        }
    }
}
