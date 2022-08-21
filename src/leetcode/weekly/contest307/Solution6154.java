package leetcode.weekly.contest307;

import datastructure.tree.TreeNode;

import java.util.*;

/**
 * 6154. 感染二叉树需要的总时间
 * 给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。
 *
 * 每分钟，如果节点满足以下全部条件，就会被感染：
 *
 * 节点此前还没有感染。
 * 节点与一个已感染节点相邻。
 * 返回感染整棵树需要的分钟数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,5,3,null,4,10,6,9,2], start = 3
 * 输出：4
 * 解释：节点按以下过程被感染：
 * - 第 0 分钟：节点 3
 * - 第 1 分钟：节点 1、10、6
 * - 第 2 分钟：节点5
 * - 第 3 分钟：节点 4
 * - 第 4 分钟：节点 9 和 2
 * 感染整棵树需要 4 分钟，所以返回 4 。
 * 示例 2：
 *
 *
 * 输入：root = [1], start = 1
 * 输出：0
 * 解释：第 0 分钟，树中唯一一个节点处于感染状态，返回 0 。
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 105] 内
 * 1 <= Node.val <= 105
 * 每个节点的值 互不相同
 * 树中必定存在值为 start 的节点
 * @author chenzw
 * @date 2022/8/21
 */
public class Solution6154 {
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> edges = new HashMap<>();
        getEdges(root, edges);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int ans = 0;
        while(!queue.isEmpty()){
            ans ++;
            int size = queue.size();
            for(int i=0;i<size;i++){
                int k = queue.poll();
                visited.add(k);
                for(Integer next:edges.get(k)){
                    if(!visited.contains(next)){
                        queue.offer(next);
                    }
                }
            }
        }
        return ans-1;
    }

    private void getEdges(TreeNode root,Map<Integer,List<Integer>> edges){
        List<Integer> list = edges.get(root.val);
        if(list==null){
            list = new ArrayList<>();
            edges.put(root.val,list);
        }
        if(root.left!=null){
            list.add(root.left.val);
            List<Integer> li = edges.get(root.left.val);
            if(li==null){
                li = new ArrayList<>();
                edges.put(root.left.val,li);
            }
            li.add(root.val);
            getEdges(root.left,edges);
        }
        if(root.right!=null){
            list.add(root.right.val);
            List<Integer> li = edges.get(root.right.val);
            if(li==null){
                li = new ArrayList<>();
                edges.put(root.right.val,li);
            }
            li.add(root.val);
            getEdges(root.right,edges);
        }
    }
}
