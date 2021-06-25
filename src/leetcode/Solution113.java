package leetcode;

import datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * @author chenzw
 * @date 2021/6/21
 */
public class Solution113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root,targetSum,ans,new ArrayList<>());
        return ans;
    }

    private void dfs(TreeNode root, int targetSum,List<List<Integer>> ans, List<Integer> path){
        if(root==null)return;
        targetSum-=root.val;
        path.add(root.val);
        if(targetSum==0&&root.left==null&&root.right==null){
            ans.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            return;
        }
        dfs(root.left,targetSum,ans,path);
        dfs(root.right,targetSum,ans,path);
        path.remove(path.size()-1);
    }


}
