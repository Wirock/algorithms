package leetcode;

import java.util.Arrays;

/**
 * 1723. 完成所有工作的最短时间
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 *
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 *
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：jobs = [3,2,3], k = 3
 * 输出：3
 * 解释：给每位工人分配一项工作，最大工作时间是 3 。
 * 示例 2：
 *
 * 输入：jobs = [1,2,4,7,8], k = 2
 * 输出：11
 * 解释：按下述方式分配工作：
 * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
 * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
 * 最大工作时间是 11 。
 *
 *
 * 提示：
 *
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 10^7
 * @author chenzw
 * @date 2021/5/8
 */
public class Solution1723 {
    private static int min=0;
    public static int minimumTimeRequired(int[] jobs, int k) {
        min = 0;
        int[] worker = new int[k];
        dfs(worker,jobs,0);
        return min;
    }

    private static void dfs(int[] worker,int[] jobs,int index){
        if(index==jobs.length) {
            int max = Arrays.stream(worker).max().getAsInt();
            min = min>0?Math.min(min,max):max;
            return;
        }
        for(int j=0;j<worker.length;j++){
            if(j>0&&worker[j-1]==0)break;//剪枝，只有上一个人已经分配了工作，才给下一个分配。防止重复。如果分配的组合相同,只是这些组合分配给不同的人，则这些方案对于求解来说是重复的
            if(min>0&&worker[j]+jobs[index]>=min)continue;//剪枝，值已经超过已有的最小值，不必往下进行
            worker[j] += jobs[index];
            dfs(worker,jobs,index+1);
            worker[j] -= jobs[index];
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(minimumTimeRequired(new int[]{3,2,3},3));
        System.out.println(minimumTimeRequired(new int[]{1,2,4,7,8},2));
        System.out.println(minimumTimeRequired(new int[]{10000000,10000000,10000000,10000000,10000000,10000000,10000000,10000000,10000000,10000000,10000000,10000000,10000000,10000000,10000000,10000000},16));
        System.out.println(System.currentTimeMillis()-start);
    }
}
