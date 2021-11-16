package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 391. 完美矩形
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
 *
 * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 *
 *
 * 示例 1：
 *
 *
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * 输出：true
 * 解释：5 个矩形一起可以精确地覆盖一个矩形区域。
 * 示例 2：
 *
 *
 * 输入：rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 * 输出：false
 * 解释：两个矩形之间有间隔，无法覆盖成一个矩形。
 * 示例 3：
 *
 *
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[3,2,4,4]]
 * 输出：false
 * 解释：图形顶端留有空缺，无法覆盖成一个矩形。
 * 示例 4：
 *
 *
 * 输入：rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * 输出：false
 * 解释：因为中间有相交区域，虽然形成了矩形，但不是精确覆盖。
 *
 *
 * 提示：
 *
 * 1 <= rectangles.length <= 2 * 104
 * rectangles[i].length == 4
 * -105 <= xi, yi, ai, bi <= 105
 * @author chenzw
 * @date 2021/11/16
 */
public class Solution391 {
    //如果能精确覆盖，满足下面两个条件
    //1.所有小矩形的面积总和等于组成大矩形的面积
    //2.小矩形之间没有重叠，即除了大矩形的四个顶点只出现一次，其他顶点只能是2次或四次
    public boolean isRectangleCover(int[][] rectangles) {
        int top = Integer.MIN_VALUE;
        int bottom = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        long sum = 0;
        Map<Long,Integer> count = new HashMap<>();
        for(int[] r:rectangles){
            top = Math.max(top,r[3]);
            bottom = Math.min(bottom,r[1]);
            left = Math.min(left,r[0]);
            right = Math.max(right,r[2]);
            sum += ((long)r[2]-r[0])*((long)r[3]-r[1]);
        }
        //验证1
        if(((long)top-bottom)*((long)right-left)!=sum)return false;

        //验证2
        for(int[] r:rectangles){
            long key1 = getKey(r[0], r[1]);
            long key2 = getKey(r[0], r[3]);
            long key3 = getKey(r[2], r[1]);
            long key4 = getKey(r[2], r[3]);
            count.put(key1,count.getOrDefault(key1,0)+1);
            count.put(key2,count.getOrDefault(key2,0)+1);
            count.put(key3,count.getOrDefault(key3,0)+1);
            count.put(key4,count.getOrDefault(key4,0)+1);
        }
        long vertex1 = getKey(left,bottom);
        long vertex2 = getKey(left,top);
        long vertex3 = getKey(right,bottom);
        long vertex4 = getKey(right,top);
        for(Map.Entry<Long,Integer> e:count.entrySet()){
            if(e.getKey()==vertex1||e.getKey()==vertex2||e.getKey()==vertex3||e.getKey()==vertex4){
                if(e.getValue()!=1)return false;
            }else if(e.getValue()!=2&&e.getValue()!=4)return false;
        }
        return true;
    }

    private long getKey(int x,int y){
        return (x+(long)1e4)*(long)1e5+y+(long)1e4;
    }
}
