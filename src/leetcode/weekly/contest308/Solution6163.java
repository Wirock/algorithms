package leetcode.weekly.contest308;

import java.util.*;

/**
 * @author chenzw
 * @date 2022/8/28
 */
public class Solution6163 {
    /*
    //拓扑排序
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] ans = new int[k][k];

        List<Integer>[] e1 = new List[k+1];
        for(int i=0;i<=k;i++){
            e1[i] = new ArrayList();
        }
        for(int[] r:rowConditions){
            e1[r[1]].add(r[0]);
        }
        List<Integer>[] e2 = new List[k+1];
        for(int i=0;i<=k;i++){
            e2[i] = new ArrayList();
        }
        for(int[] c:colConditions){
            e2[c[1]].add(c[0]);
        }
        int[] row = new int[k+1];
        Set<Integer> set1 = new HashSet<>();
        int i = 0;
        while(i<k){
            int before = set1.size();
            for(int j=1;j<=k;j++){
                if(set1.contains(j)){
                    continue;
                }
                if(e1[j].size()==0){
                    row[j] = i++;
                    set1.add(j);
                }else{
                    boolean flag = true;
                    for(int m:e1[j]){
                        if(!set1.contains(m)){
                            flag = false;
                        }
                    }
                    if(flag){
                        row[j] = i++;
                        set1.add(j);
                    }
                }
            }
            if(set1.size()==before){
                return new int[0][0];
            }
        }
        int[] col= new int[k+1];
        Set<Integer> set2 = new HashSet<>();
        i = 0;
        while(i<k){
            int before = set2.size();
            for(int j=1;j<k+1;j++){
                if(set2.contains(j)){
                    continue;
                }
                if(e2[j].size()==0){
                    col[j] = i++;
                    set2.add(j);
                }else{
                    boolean flag = true;
                    for(int m:e2[j]){
                        if(!set2.contains(m)){
                            flag = false;
                        }
                    }
                    if(flag){
                        col[j] = i++;
                        set2.add(j);
                    }
                }
            }
            if(set2.size()==before){
                return new int[0][0];
            }
        }

        for(i=1;i<=k;i++){
            ans[row[i]][col[i]] = i;
        }
        return ans;
    }*/

    //拓扑排序
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] row = topoSort(k, rowConditions), col = topoSort(k, colConditions);
        if (row.length < k || col.length < k) return new int[][]{};
        int[] pos = new int[k];
        //统计各个数字的列坐标
        for (int i = 0; i < k; ++i) {
            pos[col[i]] = i;
        }
        int[][] ans = new int[k][k];
        for (int i = 0; i < k; ++i) {
            ans[i][pos[row[i]]] = row[i] + 1;
        }
        return ans;
    }
    private int[] topoSort(int k, int[][] edges) {
        List<Integer>[] g = new ArrayList[k];
        Arrays.setAll(g, e -> new ArrayList<>());
        int[] inDeg = new int[k];
        for (int[] e : edges) {
            int x = e[0] - 1, y = e[1] - 1; // 顶点编号从 0 开始，方便计算
            g[x].add(y);
            ++inDeg[y];
        }

        List<Integer> order = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            if (inDeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            order.add(x);
            for (int y : g[x]) {
                if (--inDeg[y] == 0) {
                    q.offer(y);
                }
            }
        }
        return order.stream().mapToInt(x -> x).toArray();
    }

}
