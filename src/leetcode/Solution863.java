package leetcode;

import datastructure.tree.TreeNode;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 *
 *
 *
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 *
 *
 * 提示：
 *
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 * @author chenzw
 * @date 2021/7/28
 */
public class Solution863 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if(k==0){
            ans.add(target.val);
            return ans;
        }
        Map<Integer,List<Integer>> edges = new HashMap<>();
        putEdges(edges,root);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(target.val);
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        while(!queue.isEmpty()&&k>0){
            int size = queue.size();
            k--;
            for(int i=0;i<size;i++){
                int num = queue.poll();
                if(k==0){
                    for(int val:edges.getOrDefault(num,new ArrayList<>(0))){
                        if(!visited.contains(val))ans.add(val);
                    }
                }else{
                    for(int val:edges.getOrDefault(num,new ArrayList<>(0))){
                        if(!visited.contains(val)){
                            queue.offer(val);
                            visited.add(val);
                        }
                    }
                }
            }
        }
        return ans;
    }

    private void putEdges(Map<Integer, List<Integer>> edges, TreeNode root){
        if(root==null)return;
        if(root.left!=null){
            edges.putIfAbsent(root.val,new ArrayList<>());
            edges.putIfAbsent(root.left.val,new ArrayList<>());
            edges.get(root.val).add(root.left.val);
            edges.get(root.left.val).add(root.val);
            putEdges(edges,root.left);
        }
        if(root.right!=null){
            edges.putIfAbsent(root.val,new ArrayList<>());
            edges.putIfAbsent(root.right.val,new ArrayList<>());
            edges.get(root.val).add(root.right.val);
            edges.get(root.right.val).add(root.val);
            putEdges(edges,root.right);
        }
    }
}
