package leetcode;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 *
 *
 *
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 *
 *
 *
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 *
 *
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * @author chenzw
 * @date 2021/6/10
 */
public class Solution84 {
    /*public static int largestRectangleArea(int[] heights) {
        int max = 0;
        int h = 1;
        int maxHeight = 0;
        for(int i=0;i<heights.length;i++){
            maxHeight=Math.max(maxHeight,heights[i]);
        }
        while(h<=maxHeight){
            int area=0;
            for(int i=0;i<heights.length;i++){
                if(heights[i]>=h){
                    area+=h;
                }else if(area>0){
                    max = Math.max(max,area);
                    area=0;
                }
            }
            if(area>0){
                max = Math.max(max,area);
            }
            h++;
        }
        return max;
    }*/

    public static int largestRectangleArea(int[] heights) {
        if(heights.length<1)return 0;
        Stack<Integer> stack = new Stack<>();//单调栈，栈顶元素不小于它下面的元素
        int ans = 0;
        for(int i=0;i<heights.length;i++){
            while(!stack.isEmpty()&&heights[stack.peek()]>=heights[i]){
                int j = stack.pop();
                int k = stack.isEmpty()?-1:stack.peek();
                ans = Math.max(ans,heights[j]*(i-1-k));
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty()?-1:stack.peek();
            ans = Math.max(ans,heights[j]*(heights.length-1-k));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
        System.out.println(largestRectangleArea(new int[]{2,4}));
        System.out.println(largestRectangleArea(new int[]{4,4,0,9,4,4}));
    }
}
