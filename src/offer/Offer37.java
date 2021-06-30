package offer;

import common.CommonUtil;
import datastructure.tree.TreeNode;

/**
 * 剑指 Offer 37. 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 *
 *
 * 示例：
 *
 *
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 *
 *
 * 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * @author chenzw
 * @date 2021/6/30
 */
public class Offer37 {
    public static class Codec {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            if(root==null)return ".";
            sb.append(root.val);
            sb.append(",");
            sb.append(serialize(root.left));
            sb.append(",");
            sb.append(serialize(root.right));
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data.length()==0)return null;
            String[] s = data.split(",");
            if(".".equals(s[0]))return null;
            int index = 0;
            TreeNode root = new TreeNode(Integer.parseInt(s[index++]));
            index = deserialize(s,1,root,true);
            deserialize(s,index,root,false);
            return root;
        }
        private int deserialize(String[] s,int index,TreeNode root,boolean leftChild) {
            if(".".equals(s[index]))return index+1;
            TreeNode node = new TreeNode(Integer.parseInt(s[index++]));
            index = deserialize(s,index,node,true);
            index = deserialize(s,index,node,false);
            if(leftChild)root.left = node;
            if(!leftChild)root.right = node;
            return index;
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        CommonUtil.printTree(codec.deserialize(codec.serialize(CommonUtil.arrays2BSTByLevel(new Integer[]{1,2,3,null,null,4,5}))));
    }
}
