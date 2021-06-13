package leetcode;

/**
 * 5767. 检查是否区域内所有整数都被覆盖
 * 给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
 *
 * 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
 *
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 * 示例 2：
 *
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * 输出：false
 * 解释：21 没有被任何一个区间覆盖。
 *
 *
 * 提示：
 *
 * 1 <= ranges.length <= 50
 * 1 <= starti <= endi <= 50
 * 1 <= left <= right <= 50
 * @author chenzw
 * @date 2021/6/14
 */
public class Solution5767 {
    /*public boolean isCovered(int[][] ranges, int left, int right) {
        for(int i=left;i<=right;i++){
            boolean flag=false;
            for(int j=0;j<ranges.length;j++){
                if(i>=ranges[j][0]&&i<=ranges[j][1])flag = true;
            }
            if(!flag)return false;
        }
        return true;
    }*/
    //利用差分数组进行优化,设一个函数f(i)，当i从小到大变化的时候，每经过一个ranges的左边界f(i)加1,离开右边界时f(i)-1,则只要f(i)>0就有i在ranges中
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for(int[] r:ranges){
            diff[r[0]]++;
            diff[r[1]+1]--;
        }
        int f = 0;
        for(int i=1;i<=right;i++){
            f+=diff[i];
            if(f<=0&&i>=left){
                return false;
            }
        }
        return true;
    }
}
