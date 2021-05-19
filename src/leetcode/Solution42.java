package leetcode;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @author chenzw
 * @date 2021/4/2
 */
public class Solution42 {
    public static int trap(int[] height) {
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
    }


    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trap(new int[]{0,0,0,0,8,0,8,0,0,0,0,0}));
    }
}
