package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. 直线上最多的点数
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * 示例 2：
 *
 *
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 *
 *
 * 提示：
 *
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * points 中的所有点 互不相同
 * @author chenzw
 * @date 2021/6/24
 */
public class Solution149 {

    /*public int maxPoints(int[][] points) {
        if(points.length==1)return 1;
        Map<String,Integer> map = new HashMap<>();
        int max = 0;
        for(int i=0;i<points.length;i++){
            map.clear();
            int x1 = points[i][0];
            int y1 = points[i][1];
            for(int j=i+1;j<points.length;j++){
                int x2 = points[j][0];
                int y2 = points[j][1];
                int m = x2 - x1;
                int n = y2 - y1;
                String key = null;
                if(m==0) {
                    key = "0/";
                }else if(n==0){
                    key = "/0";
                }else {
                    int g = gcd(Math.abs(m), Math.abs(n));
                    key = m / n > 0 ? (Math.abs(m) / g + "/" + Math.abs(n) / g) : ("-" + Math.abs(m) / g + "/" + Math.abs(n) / g);
                }
                int count = map.getOrDefault(key, 0);
                map.put(key, ++count);
                max = Math.max(max, count + 1);
            }
        }
        return max;
    }*/

    //优化用整数做key,提高速度。根据m,n的取值范围，可以用加权和的方式避免重复
    public int maxPoints(int[][] points) {
        if(points.length==1)return 1;
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        for(int i=0;i<points.length;i++){
            map.clear();
            int x1 = points[i][0];
            int y1 = points[i][1];
            for(int j=i+1;j<points.length;j++){
                int x2 = points[j][0];
                int y2 = points[j][1];
                int m = x2 - x1;
                int n = y2 - y1;
                if(m==0)n=1;
                if(n==0)m=1;
                int g = gcd(Math.abs(m), Math.abs(n));
                if(n<0){
                    m = -m;
                    n = -n;
                }
                Integer key = m/g*20001+n/g;
                int count = map.getOrDefault(key, 0);
                map.put(key, ++count);
                max = Math.max(max, count + 1);
            }
        }
        return max;
    }

    private int gcd(int a,int b){
        int max = a>b?a:b;
        int min = a>b?b:a;
        if(min==0)return 1;
        int r = max%min;
        return r==0?min:gcd(min,r);
    }
}
