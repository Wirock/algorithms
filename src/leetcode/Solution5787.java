package leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 5787. 最佳运动员的比拼回合 显示英文描述
 * 通过的用户数0
 * 尝试过的用户数0
 * 用户总通过次数0
 * 用户总提交次数0
 * 题目难度Hard
 * n 名运动员参与一场锦标赛，所有运动员站成一排，并根据 最开始的 站位从 1 到 n 编号（运动员 1 是这一排中的第一个运动员，运动员 2 是第二个运动员，依此类推）。
 *
 * 锦标赛由多个回合组成（从回合 1 开始）。每一回合中，这一排从前往后数的第 i 名运动员需要与从后往前数的第 i 名运动员比拼，获胜者将会进入下一回合。如果当前回合中运动员数目为奇数，那么中间那位运动员将轮空晋级下一回合。
 *
 * 例如，当前回合中，运动员 1, 2, 4, 6, 7 站成一排
 * 运动员 1 需要和运动员 7 比拼
 * 运动员 2 需要和运动员 6 比拼
 * 运动员 4 轮空晋级下一回合
 * 每回合结束后，获胜者将会基于最开始分配给他们的原始顺序（升序）重新排成一排。
 *
 * 编号为 firstPlayer 和 secondPlayer 的运动员是本场锦标赛中的最佳运动员。在他们开始比拼之前，完全可以战胜任何其他运动员。而任意两个其他运动员进行比拼时，其中任意一个都有获胜的可能，因此你可以 裁定 谁是这一回合的获胜者。
 *
 * 给你三个整数 n、firstPlayer 和 secondPlayer 。返回一个由两个值组成的整数数组，分别表示两位最佳运动员在本场锦标赛中比拼的 最早 回合数和 最晚 回合数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 11, firstPlayer = 2, secondPlayer = 4
 * 输出：[3,4]
 * 解释：
 * 一种能够产生最早回合数的情景是：
 * 回合 1：1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * 回合 2：2, 3, 4, 5, 6, 11
 * 回合 3：2, 3, 4
 * 一种能够产生最晚回合数的情景是：
 * 回合 1：1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * 回合 2：1, 2, 3, 4, 5, 6
 * 回合 3：1, 2, 4
 * 回合 4：2, 4
 * 示例 2：
 *
 * 输入：n = 5, firstPlayer = 1, secondPlayer = 5
 * 输出：[1,1]
 * 解释：两名最佳运动员 1 和 5 将会在回合 1 进行比拼。
 * 不存在使他们在其他回合进行比拼的可能。
 *
 *
 * 提示：
 *
 * 2 <= n <= 28
 * 1 <= firstPlayer < secondPlayer <= n
 * @author chenzw
 * @date 2021/6/13
 */
public class Solution5787 {
    static int max=0;
    static int min=Integer.MAX_VALUE;
    public static int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        int[] players = new int[n];
        for(int i=1;i<=n;i++){
            players[i-1]=i;
        }
        max=0;
        min=Integer.MAX_VALUE;
        dfs(players,firstPlayer,secondPlayer,1);
        return new int[]{min,max};
    }

    private static void dfs(int[] players,int firstPlayer, int secondPlayer,int count){
        int n = players.length/2;
        for(int i=0;i<n;i++){
            if(players[i]==firstPlayer&&players[players.length-1-i]==secondPlayer){
                min = Math.min(min,count);
                max = Math.max(max,count);
                return;
            }
        }

        int[] p = new int[(players.length+1)/2];
        boolean[] del = new boolean[players.length];
        for(int i=0;i<1<<(n+1);i++){
            Arrays.fill(del,false);

            boolean pass=false;
            for(int j=0;j<n;j++){
                if((i>>j&1)==0){
                    if(firstPlayer==players[j]||secondPlayer==players[j])pass=true;
                    else del[j]=true;
                }else{
                    if(firstPlayer==players[players.length-j-1]||secondPlayer==players[players.length-j-1])pass=true;
                    else del[players.length-j-1]=true;
                }
                if(pass)break;
            }
            if(pass)continue;
            int k=0;
            for(int j=0;j<players.length;j++){
                if(del[j])continue;
                p[k++]=players[j];
            }
            dfs(p,firstPlayer,secondPlayer,count+1);
        }

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(earliestAndLatest(11,2,4)));
        System.out.println(Arrays.toString(earliestAndLatest(5,1,5)));
    }
}
