package leetcode;

/**
 * LCP 29. 乐团站位
 * 某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，每个位置站有一位成员。乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。
 *
 * 为保证声乐混合效果，成员站位规则为：自 grid 左上角开始顺时针螺旋形向内循环以 1，2，...，9 循环重复排列。例如当 num = 5 时，站位如图所示
 *
 * image.png
 *
 * 请返回位于场地坐标 [Xpos,Ypos] 的成员所持乐器编号。
 *
 * 示例 1：
 *
 * 输入：num = 3, Xpos = 0, Ypos = 2
 *
 * 输出：3
 *
 * 解释：
 * image.png
 *
 * 示例 2：
 *
 * 输入：num = 4, Xpos = 1, Ypos = 2
 *
 * 输出：5
 *
 * 解释：
 * image.png
 *
 * 提示：
 *
 * 1 <= num <= 10^9
 * 0 <= Xpos, Ypos < num
 * @author chenzw
 * @date 2021/4/5
 */
public class LCP29 {
    public int orchestraLayout(int num, int xPos, int yPos) {
        long n = 1;
        long margin = Math.min(xPos,yPos);
        long nums = num;
        margin = Math.min(margin,nums-1-xPos);
        margin = Math.min(margin,nums-1-yPos);
        n+=(((nums+nums-2*margin)%9)*(2*margin)%9)%9;
        nums = nums-2*margin;
        xPos-=margin;
        yPos-=margin;
        if(xPos==0){
            n=(n+yPos)%9;
        }else if(yPos==0){
            n=(n+4*(nums-1)-xPos)%9;
        }else if(xPos==nums-1){
            n=(n+3*(nums-1)-yPos)%9;
        }else if(yPos==nums-1){
            n=(n+nums+xPos-1)%9;
        }
        return (int)(n==0?9:n);
    }
}
