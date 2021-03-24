package leetcode;

/**
 * 1802. 有界数组中指定下标处的最大值
 * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
 *
 * nums.length == n
 * nums[i] 是 正整数 ，其中 0 <= i < n
 * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
 * nums 中所有元素之和不超过 maxSum
 * nums[index] 的值被 最大化
 * 返回你所构造的数组中的 nums[index] 。
 *
 * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4, index = 2,  maxSum = 6
 * 输出：2
 * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
 * 示例 2：
 *
 * 输入：n = 6, index = 1,  maxSum = 10
 * 输出：3
 * @author chenzw
 * @date 2021/3/24
 */
public class Solution1802 {
    public static int maxValue(int n, int index, int maxSum) {
        if(n==maxSum) return 1;
        int min = Math.min(index,n-1-index);
        int max = Math.max(index,n-1-index);

        maxSum = maxSum-n-1;
        int count = 2;
        int layer = 1;
        while(maxSum>0){
            if(count-2<min){
                layer += 2;
            }else if(count-2<max){
                layer += 1;
            }else{
                return count+maxSum/n;
            }
            maxSum-=layer;
            if(maxSum>=0){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(maxValue(1,0,780055968));
    }
}
