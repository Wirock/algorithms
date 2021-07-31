package leetcode;

import datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 987. 二叉树的垂序遍历
 * 给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。
 *
 * 对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。
 *
 * 二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。
 *
 * 返回二叉树的 垂序遍历 序列。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[9],[3,15],[20],[7]]
 * 解释：
 * 列 -1 ：只有结点 9 在此列中。
 * 列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
 * 列  1 ：只有结点 20 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 列 -2 ：只有结点 4 在此列中。
 * 列 -1 ：只有结点 2 在此列中。
 * 列  0 ：结点 1 、5 和 6 都在此列中。
 *           1 在上面，所以它出现在前面。
 *           5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
 * 列  1 ：只有结点 3 在此列中。
 * 列  2 ：只有结点 7 在此列中。
 * 示例 3：
 *
 *
 * 输入：root = [1,2,3,4,6,5,7]
 * 输出：[[4],[2],[1,5,6],[3],[7]]
 * 解释：
 * 这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
 * 因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。
 *
 *
 * 提示：
 *
 * 树中结点数目总数在范围 [1, 1000] 内
 * 0 <= Node.val <= 1000
 * @author chenzw
 * @date 2021/7/31
 */
public class Solution987 {
    //桶计数 o(n^2)
    /*public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer,int[][]> map = new TreeMap<>((x,y)->x-y);
        addNode(root,0,0,map);
        List<List<Integer>> ans = new ArrayList<>(map.size());
        for(Integer key:map.keySet()) {
            List<Integer> list = new LinkedList<>();
            int[][] count = map.get(key);
            for(int i=0;i<count.length;i++){
                int[] levelCount = count[i];
                for(int j=0;j<levelCount.length;j++){
                    while(levelCount[j]-->0)list.add(j);
                }
            }
            ans.add(list);
        }
        return ans;
    }
    private void addNode(TreeNode node,int index,int level,TreeMap<Integer,int[][]> map){
        if(node==null)return;
        map.putIfAbsent(index,new int[1001][1001]);
        map.get(index)[level][node.val]++;
        addNode(node.left,index-1,level+1,map);
        addNode(node.right,index+1,level+1,map);
    }*/

    //dfs+排序
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> list = new ArrayList<>();
        dfs(list,root,0,0);
        Collections.sort(list,(x,y)->{
            if(x[2]!=y[2])return x[2]-y[2];
            if(x[1]!=y[1])return x[1]-y[1];
            return x[0]-y[0];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int lastCol = Integer.MIN_VALUE;
        List<Integer> colList = null;
        for(int[] n:list){
            if(n[2] != lastCol){
                colList = new LinkedList<>();
                ans.add(colList);
                lastCol = n[2];
            }
            colList.add(n[0]);
        }
        return ans;
    }
    private void dfs(List<int[]> list,TreeNode node,int row,int col){
        if(node==null)return;
        list.add(new int[]{node.val,row,col});
        dfs(list,node.left,row+1,col-1);
        dfs(list,node.right,row+1,col+1);
    }
}
