package leetcode;

import datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. 删点成林
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 *
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 *
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 * 示例 2：
 *
 * 输入：root = [1,2,4,null,3], to_delete = [3]
 * 输出：[[1,2,4]]
 *
 *
 * 提示：
 *
 * 树中的节点数最大为 1000。
 * 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete 包含一些从 1 到 1000、各不相同的值。
 *
 */
public class Solution1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for(int i:to_delete){
            set.add(i);
        }
        List<TreeNode> list = new ArrayList<>();
        dfs(null,root,true,list,set);
        return list;
    }

    public void dfs(TreeNode parent, TreeNode node, boolean left, List<TreeNode> list, Set<Integer> set){
        if(node==null){
            return;
        }

        if(set.contains(node.val)){
            if(parent!=null){
                if(left){
                    parent.left=null;
                }else{
                    parent.right=null;
                }
            }
            dfs(null, node.left, true, list, set);
            dfs(null, node.right, false, list, set);
        }else{
            if(parent==null){
                list.add(node);
            }
            dfs(node, node.left, true, list, set);
            dfs(node, node.right, false, list, set);
        }
    }
}
