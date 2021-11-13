package leetcode.biweekly.contest65;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 5913. 你可以安排的最多任务数目 显示英文描述
 * 通过的用户数0
 * 尝试过的用户数0
 * 用户总通过次数0
 * 用户总提交次数0
 * 题目难度Hard
 * 给你 n 个任务和 m 个工人。每个任务需要一定的力量值才能完成，需要的力量值保存在下标从 0 开始的整数数组 tasks 中，第 i 个任务需要 tasks[i] 的力量才能完成。每个工人的力量值保存在下标从 0 开始的整数数组 workers 中，第 j 个工人的力量值为 workers[j] 。每个工人只能完成 一个 任务，且力量值需要 大于等于 该任务的力量要求值（即 workers[j] >= tasks[i] ）。
 *
 * 除此以外，你还有 pills 个神奇药丸，可以给 一个工人的力量值 增加 strength 。你可以决定给哪些工人使用药丸，但每个工人 最多 只能使用 一片 药丸。
 *
 * 给你下标从 0 开始的整数数组tasks 和 workers 以及两个整数 pills 和 strength ，请你返回 最多 有多少个任务可以被完成。
 *
 *
 *
 * 示例 1：
 *
 * 输入：tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
 * 输出：3
 * 解释：
 * 我们可以按照如下方案安排药丸：
 * - 给 0 号工人药丸。
 * - 0 号工人完成任务 2（0 + 1 >= 1）
 * - 1 号工人完成任务 1（3 >= 2）
 * - 2 号工人完成任务 0（3 >= 3）
 * 示例 2：
 *
 * 输入：tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
 * 输出：1
 * 解释：
 * 我们可以按照如下方案安排药丸：
 * - 给 0 号工人药丸。
 * - 0 号工人完成任务 0（0 + 5 >= 5）
 * 示例 3：
 *
 * 输入：tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
 * 输出：2
 * 解释：
 * 我们可以按照如下方案安排药丸：
 * - 给 0 号和 1 号工人药丸。
 * - 0 号工人完成任务 0（0 + 10 >= 10）
 * - 1 号工人完成任务 1（10 + 10 >= 15）
 * 示例 4：
 *
 * 输入：tasks = [5,9,8,5,9], workers = [1,6,4,2,6], pills = 1, strength = 5
 * 输出：3
 * 解释：
 * 我们可以按照如下方案安排药丸：
 * - 给 2 号工人药丸。
 * - 1 号工人完成任务 0（6 >= 5）
 * - 2 号工人完成任务 2（4 + 5 >= 8）
 * - 4 号工人完成任务 3（6 >= 5）
 *
 *
 * 提示：
 *
 * n == tasks.length
 * m == workers.length
 * 1 <= n, m <= 5 * 104
 * 0 <= pills <= m
 * 0 <= tasks[i], workers[j], strength <= 109
 * @author chenzw
 * @date 2021/11/14
 */
public class Solution5913 {
    //二分+贪心
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int w:workers){
            map.put(w,map.getOrDefault(w,0)+1);
        }
        int ans = 0;
        int left=1;
        int right=tasks.length;
        while(left<=right){
            int mid = (left+right)>>1;
            if(check(mid,tasks,map,pills,strength)){
                left=mid+1;
                ans = mid;
            }else{
                right = mid-1;
            }
        }
        return ans;
    }

    private boolean check(int k,int[] task,TreeMap<Integer,Integer> map,int pills, int strength){
        map = new TreeMap<>(map);
        if(k>task.length)return false;
        while(k>0){
            Integer j = map.ceilingKey(task[k-1]);
            if(j!=null){
                k--;
                Integer val = map.get(j);
                if(val==1)map.remove(j);
                else map.put(j,val-1);
            }else if(pills>0&&(j = map.ceilingKey(task[k-1]-strength))!=null){
                k--;
                pills--;
                Integer val = map.get(j);
                if(val==1)map.remove(j);
                else map.put(j,val-1);
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution5913().maxTaskAssign(new int[]{3,2,1},new int[]{0,3,3},1,1));
    }
}
