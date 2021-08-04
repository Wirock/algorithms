package leetcode;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 *
 * 示例 1:
 *
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 注意:
 *
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 * @author chenzw
 * @date 2021/8/4
 */
public class Solution611 {
    //三角形的三条边a,b,c满足 a>0,b>0,c>0, a+b>c,a+c>b,b+c>a
    //排序后a<=b<=c,只要a>0,就有a+c>b,b+c>a
    //这时只要再满足a>0,a+b>c即可
    //排序，选定a,b,二分查找c
    /*public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) continue;
            for (int j = i + 1; j < n; ++j) {
                int left = j + 1, right = n - 1, k = j;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                ans += k - j;
            }
        }
        return ans;
    }*/
    //排序+双指针
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n-2; i++) {
            if (nums[i] == 0) continue;
            int k = i + 2;
            for(int j = i + 1;j<n-1;j++) {
                while (k < n && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                ans += Math.max(k - j - 1,0);
            }
        }
        return ans;
    }
}
