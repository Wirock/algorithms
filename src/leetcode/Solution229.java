package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. 求众数 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：[3,2,3]
 * 输出：[3]
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 * @author chenzw
 * @date 2021/10/22
 */
public class Solution229 {
    //Boyer-Moore 投票算法
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        int x=0;
        int y=0;
        int xCount=0;
        int yCount=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==x){
                xCount++;
            }else if(nums[i]==y){
                yCount++;
            }else {
                if(xCount>0&&yCount>0){
                    xCount--;
                    yCount--;
                }else if(xCount==0){
                    x=nums[i];
                    xCount++;
                }else{
                    y=nums[i];
                    yCount++;
                }
            }
        }
        xCount=0;
        yCount=0;
        for(int i=0;i<nums.length;i++){
            if(x==nums[i])xCount++;
            if(y==nums[i])yCount++;
        }
        if(xCount>n/3)ans.add(x);
        if(x!=y&&yCount>n/3)ans.add(y);
        return ans;
    }
}
