package leetcode;

import java.util.*;

/**
 * 5748. 包含每个查询的最小区间 显示英文描述
 * 通过的用户数179
 * 尝试过的用户数698
 * 用户总通过次数195
 * 用户总提交次数1446
 * 题目难度Hard
 * 给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示第 i 个区间开始于 lefti 、结束于 righti（包含两侧取值，闭区间）。区间的 长度 定义为区间中包含的整数数目，更正式地表达是 righti - lefti + 1 。
 *
 * 再给你一个整数数组 queries 。第 j 个查询的答案是满足 lefti <= queries[j] <= righti 的 长度最小区间 i 的长度 。如果不存在这样的区间，那么答案是 -1 。
 *
 * 以数组形式返回对应查询的所有答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * 输出：[3,3,1,4]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,4] 是包含 2 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 3 ：区间 [2,4] 是包含 3 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 4 ：区间 [4,4] 是包含 4 的最小区间，答案为 4 - 4 + 1 = 1 。
 * - Query = 5 ：区间 [3,6] 是包含 5 的最小区间，答案为 6 - 3 + 1 = 4 。
 * 示例 2：
 *
 * 输入：intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * 输出：[2,-1,4,6]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,3] 是包含 2 的最小区间，答案为 3 - 2 + 1 = 2 。
 * - Query = 19：不存在包含 19 的区间，答案为 -1 。
 * - Query = 5 ：区间 [2,5] 是包含 5 的最小区间，答案为 5 - 2 + 1 = 4 。
 * - Query = 22：区间 [20,25] 是包含 22 的最小区间，答案为 25 - 20 + 1 = 6 。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 10~5
 * 1 <= queries.length <= 10~5
 * queries[i].length == 2
 * 1 <= lefti <= righti <= 10~7
 * 1 <= queries[j] <= 10~7
 *
 * 思路：先筛选出包含目标的区间集合，再从集合中查找最小的区间
 *
 * @author chenzw
 * @date 2021/5/2
 */
public class Solution5748 {
    public static int[] minInterval(int[][] intervals, int[] queries) {
        int[] ans = new int[queries.length];
        int[][] q = new int[queries.length][2];
        for(int i=0;i<queries.length;i++){
            q[i][0] = queries[i];
            q[i][1] = i;
        }
        Arrays.sort(q, Comparator.comparingInt(x -> x[0]));
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        int j = 0;
        for(int i=0;i<q.length;i++){
            while(j<intervals.length&&intervals[j][0]<=q[i][0]){
                queue.add(new int[]{intervals[j][1]-intervals[j][0]+1,intervals[j][1]});
                j++;
            }
            while(!queue.isEmpty()&&queue.peek()[1]<q[i][0]){
                queue.poll();
            }
            if(queue.isEmpty()){
                ans[q[i][1]]=-1;
            }else{
                ans[q[i][1]]=queue.peek()[0];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(minInterval(new int[][]{{1,4},{2,4},{3,6},{4,4}},new int[]{2,3,4,5})));
    }
}
