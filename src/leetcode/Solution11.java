package leetcode;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 *
 * @author chenzw
 * @date 2021/3/14
 */
public class Solution11 {

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        int maxHeight;
        while (left < right) {
            maxHeight = height[left] > height[right] ? height[right] : height[left];
            int newArea = maxHeight * (right - left);
            maxArea = newArea > maxArea ? newArea : maxArea;
            while (left < height.length && height[left] <= maxHeight) left++;
            while (right > 0 && height[right] <= maxHeight) right--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea(new int[]{4, 3, 2, 1, 4}));
        System.out.println(maxArea(new int[]{1, 2, 1}));
        System.out.println(maxArea(new int[]{1, 1}));
    }
}
