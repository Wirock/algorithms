package leetcode;

import common.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 示例 3：
 *
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * 示例 4：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * 示例 5：
 *
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 *
 *
 * 提示：
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 105
 * @author chenzw
 * @date 2021/5/27
 */
public class Solution57 {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int index = 0;
        //左边界比插入区间小的部分在左侧
        while(index<intervals.length&&intervals[index][0]<=newInterval[0]){
            ans.add(intervals[index++]);
        }
        //插入区间，并进行合并
        if(ans.size()==0){
            ans.add(newInterval);
        }else{
            int[] last = ans.get(ans.size()-1);
            if(last[0]<=newInterval[0]&&last[1]>=newInterval[0]){
                if(last[1]<newInterval[1]){
                    last[1]=newInterval[1];
                }
            }else{
                ans.add(newInterval);
            }
        }
        //剩余部分在右侧，也需要判断进行合并
        int[] last = ans.get(ans.size()-1);
        while(index<intervals.length&&last[0]<=intervals[index][0]&&last[1]>=intervals[index][0]){
            if(last[0]<=intervals[index][0]&&last[1]>=intervals[index][0]){
                if(last[1]<intervals[index][1]){
                    last[1]=intervals[index][1];
                }
            }
            index++;
        }
        while(index<intervals.length){
            ans.add(intervals[index++]);
        }
        return ans.toArray(new int[ans.size()][2]);
    }

    public static void main(String[] args) {
        CommonUtil.printArray(insert(new int[][]{{1,3},{6,9}},new int[]{2,5}));
        System.out.println();
        CommonUtil.printArray(insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}},new int[]{4,8}));
        System.out.println();
        CommonUtil.printArray(insert(new int[][]{{1,5}},new int[]{6,8}));
    }
}
