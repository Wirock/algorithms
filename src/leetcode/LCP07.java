package leetcode;

import java.util.Arrays;

/**
 * LCP 07. 传递信息
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 *
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 *
 * 示例 1：
 *
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 *
 * 输出：3
 *
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 *
 * 示例 2：
 *
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 *
 * 输出：0
 *
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 *
 * 限制：
 *
 * 2 <= n <= 10
 * 1 <= k <= 5
 * 1 <= relation.length <= 90, 且 relation[i].length == 2
 * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 * @author chenzw
 * @date 2021/7/1
 */
public class LCP07 {
    //方法一：bfs,还可以进一步优化，建立邻接表，提高搜索速度
    /*public int numWays(int n, int[][] relation, int k) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int step = 0;
        while(step++<k&&!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int num = queue.poll();
                for(int[] r:relation){
                    if(r[0]==num)queue.offer(r[1]);
                }
            }
        }
        int ans = 0;
        while(!queue.isEmpty()){
            if(queue.poll()==n-1)ans++;
        }
        return ans;
    }*/

    //方法二:dfs
    /*public int numWays(int n, int[][] relation, int k) {
        List<Integer> path = new ArrayList<>();
        path.add(0);
        return dfs(path,relation,k,n,0);
    }

    private int dfs(List<Integer> path,int[][] relation,int k,int n,int ways){
        int cur = path.get(path.size()-1);
        if(path.size()==k+1){
            if(cur==n-1)ways++;
            return ways;
        }
        for(int[] r: relation){
            if(r[0]==cur) {
                path.add(r[1]);
                ways = dfs(path, relation, k, n, ways);
                path.remove(path.size()-1);
            }
        }
        return ways;
    }*/

    //dp
    //f(i,j)表示传递i次到j手里的方案数
    //f(i,j) = Σf(i-1,q)，q为relations中右侧为j的元素的左侧
    /*public int numWays(int n, int[][] relation, int k) {
        int[][] dp = new int[k+1][n];
        dp[0][0] = 1;
        for(int i=1;i<=k;i++){
            for(int[] r:relation){
                dp[i][r[1]]+=dp[i-1][r[0]];
            }
        }
        return dp[k][n-1];
    }*/

    //滚动数组可进行空间优化，但由于relations元素是无序的，故i无需，只能压缩到两行的二维数组
    public int numWays(int n, int[][] relation, int k) {
        int[][] dp = new int[2][n];
        dp[0][0] = 1;
        for(int i=1;i<=k;i++){
            Arrays.fill(dp[i%2],0);
            for(int[] r:relation){
                dp[i%2][r[1]]+=dp[(i-1)%2][r[0]];
            }
        }
        return dp[k%2][n-1];
    }
}
