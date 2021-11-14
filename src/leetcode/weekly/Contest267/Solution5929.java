package leetcode.weekly.Contest267;

import java.util.HashSet;
import java.util.Set;

/**
 * 5929. 处理含限制条件的好友请求
 * 给你一个整数 n ，表示网络上的用户数目。每个用户按从 0 到 n - 1 进行编号。
 *
 * 给你一个下标从 0 开始的二维整数数组 restrictions ，其中 restrictions[i] = [xi, yi] 意味着用户 xi 和用户 yi 不能 成为 朋友 ，不管是 直接 还是通过其他用户 间接 。
 *
 * 最初，用户里没有人是其他用户的朋友。给你一个下标从 0 开始的二维整数数组 requests 表示好友请求的列表，其中 requests[j] = [uj, vj] 是用户 uj 和用户 vj 之间的一条好友请求。
 *
 * 如果 uj 和 vj 可以成为 朋友 ，那么好友请求将会 成功 。每个好友请求都会按列表中给出的顺序进行处理（即，requests[j] 会在 requests[j + 1] 前）。一旦请求成功，那么对所有未来的好友请求而言， uj 和 vj 将会 成为直接朋友 。
 *
 * 返回一个 布尔数组 result ，其中元素遵循此规则：如果第 j 个好友请求 成功 ，那么 result[j] 就是 true ；否则，为 false 。
 *
 * 注意：如果 uj 和 vj 已经是直接朋友，那么他们之间的请求将仍然 成功 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3, restrictions = [[0,1]], requests = [[0,2],[2,1]]
 * 输出：[true,false]
 * 解释：
 * 请求 0 ：用户 0 和 用户 2 可以成为朋友，所以他们成为直接朋友。
 * 请求 1 ：用户 2 和 用户 1 不能成为朋友，因为这会使 用户 0 和 用户 1 成为间接朋友 (1--2--0) 。
 * 示例 2：
 *
 * 输入：n = 3, restrictions = [[0,1]], requests = [[1,2],[0,2]]
 * 输出：[true,false]
 * 解释：
 * 请求 0 ：用户 1 和 用户 2 可以成为朋友，所以他们成为直接朋友。
 * 请求 1 ：用户 0 和 用户 2 不能成为朋友，因为这会使 用户 0 和 用户 1 成为间接朋友 (0--2--1) 。
 * 示例 3：
 *
 * 输入：n = 5, restrictions = [[0,1],[1,2],[2,3]], requests = [[0,4],[1,2],[3,1],[3,4]]
 * 输出：[true,false,true,false]
 * 解释：
 * 请求 0 ：用户 0 和 用户 4 可以成为朋友，所以他们成为直接朋友。
 * 请求 1 ：用户 1 和 用户 2 不能成为朋友，因为他们之间存在限制。
 * 请求 2 ：用户 3 和 用户 1 可以成为朋友，所以他们成为直接朋友。
 * 请求 3 ：用户 3 和 用户 4 不能成为朋友，因为这会使 用户 0 和 用户 1 成为间接朋友 (0--4--3--1) 。
 *
 *
 * 提示：
 *
 * 2 <= n <= 1000
 * 0 <= restrictions.length <= 1000
 * restrictions[i].length == 2
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * 1 <= requests.length <= 1000
 * requests[j].length == 2
 * 0 <= uj, vj <= n - 1
 * uj != vj
 * @author chenzw
 * @date 2021/11/14
 */
public class Solution5929 {

    //并查集
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        Set<Integer>[] able = new Set[n];
        Set<Integer>[] unable = new Set[n];
        for(int i=0;i<n;i++){
            able[i]=new HashSet<>();
            unable[i]=new HashSet<>();
        }
        for(int[] r:restrictions){
           unable[r[0]].add(r[1]);
           unable[r[1]].add(r[0]);
        }

        int m=requests.length;
        int[] parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        boolean[] ans = new boolean[m];
        for(int i=0;i<m;i++){
            if(unable[requests[i][0]].contains(requests[i][1])) {
                ans[i] = false;
            }else {
                int parent1= findParent(parent,requests[i][0]);
                if(unable[parent1].contains(requests[i][1]))continue;

                int parent2= findParent(parent,requests[i][1]);
                if(parent1==parent2){
                    ans[i] = true;
                    continue;
                }

                if(contains(unable[parent1],parent,parent2))continue;

                union(parent, requests[i][0], requests[i][1]);
                unable[parent1].addAll(unable[parent2]);
                ans[i]=true;
            }
        }
        return ans;
    }

    private int findParent(int[] parent,int i){
        if(parent[i]==i)return i;
        return findParent(parent,parent[i]);
    }

    private void union(int[] parent,int i,int j){
        parent[findParent(parent,j)] = i;
    }

    private boolean contains(Set<Integer> set,int[] parent,int i){
        for(int j=0;j<parent.length;j++){
            if(findParent(parent,j)==i&&set.contains(j))return true;
        }
        return false;
    }
}
