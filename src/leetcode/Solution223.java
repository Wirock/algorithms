package leetcode;

/**
 * 223. 矩形面积
 * 给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
 *
 * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：
 *
 * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
 * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
 *
 *
 * 示例 1：
 *
 * Rectangle Area
 * 输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * 输出：45
 * 示例 2：
 *
 * 输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * 输出：16
 *
 *
 * 提示：
 *
 * -104 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 104
 * @author chenzw
 * @date 2021/9/30
 */
public class Solution223 {
    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int x1,y1,x2,y2;
        x1 = Math.max(ax1,bx1);
        x2 = Math.min(ax2,bx2);
        y1 = Math.max(ay1,by1);
        y2 = Math.min(ay2,by2);
        int total = (ax2-ax1)*(ay2-ay1)+(bx2-bx1)*(by2-by1);
        if(x2<=x1||y2<=y1)return total;
        return total - (x2-x1)*(y2-y1);
    }

    public static void main(String[] args) {
        System.out.println(computeArea(-3,0,3,4,0,-1,9,2));
        System.out.println(computeArea(-2,-2,2,2,-2,-2,2,2));
    }
}
