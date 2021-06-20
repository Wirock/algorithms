package leetcode;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 *
 * 提示：
 *
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 * @author chenzw
 * @date 2021/4/2
 */
public class Solution42 {
    //单调栈
    /*public static int trap(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;
        for(int i=0;i<height.length;i++){
            while(!stack.isEmpty()&&height[i]>height[stack.peek()]){
                int cur = stack.pop();
                if(stack.isEmpty())break;
                ans += (i-1-stack.peek())*(Math.min(height[stack.peek()],height[i])-height[cur]);
            }
            stack.push(i);
        }
        return ans;
    }*/
    //双指针
    /*public static int trap(int[] height) {
        int empty = 0;
        int full = 0;
        for(int i=0;i<height.length;i++){
            empty+=height[i];
        }
        int left = 0;
        int right = height.length-1;
        int count = 0;
        while(true){
            while(left<=right&&height[left]<=count)left++;
            while(left<=right&&height[right]<=count)right--;
            if(left>right)break;
            int min = Math.min(height[left],height[right]);
            full += (min-count)*(right-left+1);
            count = min;
        }
        return full-empty;
    }*/
    //双指针优化,无需分开算空的和满的体积，直接在双指针遍历过程中累加差值即可
    public static int trap(int[] height) {
        int left = 0;
        int right = height.length-1;
        int leftMax = 0;
        int rightMax = 0;
        int ans = 0;
        while(left<right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);
            if(height[left]<height[right])ans += leftMax - height[left++];
            else ans += rightMax-height[right--];
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap(new int[]{0,0,0,0,8,0,8,0,0,0,0,0}));
    }
}
