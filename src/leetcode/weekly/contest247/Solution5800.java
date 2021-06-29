package leetcode.weekly.contest247;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 5800. 统计为蚁群构筑房间的不同顺序
 * 你是一只蚂蚁，负责为蚁群构筑 n 间编号从 0 到 n-1 的新房间。给你一个 下标从 0 开始 且长度为 n 的整数数组 prevRoom 作为扩建计划。其中，prevRoom[i] 表示在构筑房间 i 之前，你必须先构筑房间 prevRoom[i] ，并且这两个房间必须 直接 相连。房间 0 已经构筑完成，所以 prevRoom[0] = -1 。扩建计划中还有一条硬性要求，在完成所有房间的构筑之后，从房间 0 可以访问到每个房间。
 *
 * 你一次只能构筑 一个 房间。你可以在 已经构筑好的 房间之间自由穿行，只要这些房间是 相连的 。如果房间 prevRoom[i] 已经构筑完成，那么你就可以构筑房间 i。
 *
 * 返回你构筑所有房间的 不同顺序的数目 。由于答案可能很大，请返回对 109 + 7 取余 的结果。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：prevRoom = [-1,0,1]
 * 输出：1
 * 解释：仅有一种方案可以完成所有房间的构筑：0 → 1 → 2
 * 示例 2：
 *
 *
 * 输入：prevRoom = [-1,0,0,1,2]
 * 输出：6
 * 解释：
 * 有 6 种不同顺序：
 * 0 → 1 → 3 → 2 → 4
 * 0 → 2 → 4 → 1 → 3
 * 0 → 1 → 2 → 3 → 4
 * 0 → 1 → 2 → 4 → 3
 * 0 → 2 → 1 → 3 → 4
 * 0 → 2 → 1 → 4 → 3
 *
 *
 * 提示：
 *
 * n == prevRoom.length
 * 2 <= n <= 10^5
 * prevRoom[0] == -1
 * 对于所有的 1 <= i < n ，都有 0 <= prevRoom[i] < n
 * 题目保证所有房间都构筑完成后，从房间 0 可以访问到每个房间
 * @author chenzw
 * @date 2021/6/28
 */
public class Solution5800 {
    static final int mod = (int)1e9+7;
    //树状dp，拓扑排序，乘法逆元
    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        int[] fac = new int[n];
        int[] inv = new int[n];
        fac[0] = inv[0] = 1;
        for(int i=1;i<n;i++){
            fac[i] = (int)((long)fac[i-1]*i%mod);//阶乘
            inv[i] = quickMul(fac[i],mod-2); //阶乘的乘法逆元
        }

        //建图
        Map<Integer,List<Integer>> edges = new HashMap<>();
        for(int i=1;i<n;i++){
            if(!edges.containsKey(prevRoom[i])) edges.put(prevRoom[i],new ArrayList<>());
            edges.get(prevRoom[i]).add(i);
        }
        int[] f = new int[n];
        int[] count = new int[n];
        dfs(0,edges,f,count,fac,inv);
        return f[0];
    }

    //树状dp,求以u为根节点的树的排列数
    //f[u]表示以u为根节点的树的排列总数，v为u的子节点，count[u]表示以u为根的树的节点数量
    //f[u]=Πf[v]*(count[u]-1)!/Π(count[v]!)
    private void dfs(int u,Map<Integer,List<Integer>> edges,int[] f,int[] count,int[] fac,int[] inv){
        f[u] = 1;
        for(int v:edges.getOrDefault(u,new ArrayList<>())){
            dfs(v,edges,f,count,fac,inv);//求每个子树的排列数，结果在f[v]中
            f[u] = (int)((long)f[u]*f[v]%mod*inv[count[v]]%mod);//Πf[v]/Π(count[v]!)
            count[u] += count[v];
        }
        f[u] = (int)((long)f[u]*fac[count[u]]%mod);//(count[u]-1)!*Πf[v]/Π(count[v]!)
        count[u]++;
    }

    //快速幂计算x^y
    private int quickMul(int x,int y){
        int ret = 1;
        int cur = x;
        while(y>0){
            if((y&1)>0){
                ret = (int)((long)ret*cur%mod);
            }
            cur = (int)((long)cur*cur%mod);
            y>>=1;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution5800().waysToBuildRooms(new int[]{1,0,1}));
        System.out.println(new Solution5800().waysToBuildRooms(new int[]{-1,0,0,1,2}));
    }
}
