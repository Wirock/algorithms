package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * @author chenzw
 * @date 2021/6/9
 */
public class Solution77 {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans,new ArrayList<>(),n,k,1);
        return ans;
    }
    /*private static void dfs(List<List<Integer>> ans,List<Integer> list,int n,int k,int cur){
        if(list.size()==k){
            ans.add(new ArrayList<>(list));
            return;
        }
        //for(int i=cur;i<=n;i++){
        for(int i=cur;i<=n+list.size()-k+1;i++){//优化剪枝,n替换成n+list.size()-k+1
            list.add(i);
            dfs(ans,list,n,k,i+1);
            list.remove(list.size()-1);
        }
    }*/

    private static void dfs(List<List<Integer>> ans,List<Integer> list,int n,int k,int cur){
        if(k-list.size()>n-cur+1)return;
        if(list.size()==k){
            ans.add(new ArrayList<>(list));
            return;
        }
        list.add(cur);
        dfs(ans,list,n,k,cur+1);
        list.remove(list.size()-1);
        dfs(ans,list,n,k,cur+1);
    }

    public static void main(String[] args) {
        List<List<Integer>> ans = combine(4, 2);
        for(List<Integer> l:ans){
            System.out.println(Arrays.toString(l.toArray()));
        }
    }
}
