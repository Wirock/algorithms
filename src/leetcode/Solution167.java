package leetcode;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 *
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 *
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 *
 * 示例 1：
 *
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * 示例 2：
 *
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 示例 3：
 *
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 递增顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 * @author chenzw
 * @date 2021/8/18
 */
public class Solution167 {
    //使用与leetcode 1 相同的解法 hash
    //这一题比leetcode 1 多了一个有序的条件，这种解法没有利用有序的条件
    /*public int[] twoSum(int[] numbers, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<numbers.length;i++){
            int key = target-numbers[i];
            if(map.containsKey(key))return new int[]{map.get(key)+1,i+1};
            map.put(numbers[i],i);
        }
        return new int[0];
    }*/

    //有序的话，可以使用双指针的解法，
    // 一开始两个指针分别位于数组最左端和最右端
    // 若指针对应两数之和大于target,则右指针左移一位
    // 指针对应两数之和小于target,则左指针右移一位
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        while(left<right){
            int sum = numbers[left]+numbers[right];
            if(sum==target)return  new int[]{left+1,right+1};
            if(sum<target)left++;
            else right--;
        }
        return new int[0];
    }
}
