package leetcode;

/**
 * 421. 数组中两个数的最大异或值
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 *
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [2,4]
 * 输出：6
 * 示例 4：
 *
 * 输入：nums = [8,10,2]
 * 输出：10
 * 示例 5：
 *
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 0 <= nums[i] <= 231 - 1
 * @author chenzw
 * @date 2021/5/16
 */
public class Solution421 {
    static class Node {
        Node left;
        Node right;
    }
    static Node root = new Node();
    private static void add(int x) {
        Node p = root;
        for (int i = 31; i >= 0; i--) {
            int u = (x >> i) & 1;
            if(u==0){
                if (p.left == null) p.left = new Node();
                p = p.left;
            }else{
                if (p.right == null) p.right = new Node();
                p = p.right;
            }
        }
    }
    private static int getVal(int x) {
        int ans = 0;
        Node p = root;
        for (int i = 31; i >= 0; i--) {
            int a = (x >> i) & 1;
            if (a==0){
                if(p.right!=null){
                    ans |= (1 << i);
                    p = p.right;
                }else{
                    p = p.left;
                }
            }else{
                if(p.left!=null){
                    p = p.left;
                }else{
                    ans |= (1 << i);
                    p = p.right;
                }
            }
        }
        return ans;
    }
    public static int findMaximumXOR(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            add(i);
        }
        for (int i : nums) {
            int j = getVal(i);
            ans = Math.max(ans, i ^ j);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3,10,5,25,2,8}));
    }
}
