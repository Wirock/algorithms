package leetcode;

import java.util.Arrays;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 *
 * 输入：nums = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 *
 * 进阶：
 *
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * @author chenzw
 * @date 2021/6/7
 */
public class Solution75 {
    public static void sortColors(int[] nums) {
        int cur=0;
        int left = -1;
        int right = nums.length;
        while(cur<right){
            if(nums[cur]==1){
                cur++;
            }else if(nums[cur]==0){
                left++;
                int temp = nums[cur];
                nums[cur] = nums[left];
                nums[left] = temp;
                cur++;
            }else{
                right--;
                int temp = nums[cur];
                nums[cur] = nums[right];
                nums[right] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,0,1,2,0,0,2};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
