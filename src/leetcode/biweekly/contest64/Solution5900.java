package leetcode.biweekly.contest64;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 5900. 蜡烛之间的盘子 显示英文描述
 * 通过的用户数0
 * 尝试过的用户数0
 * 用户总通过次数0
 * 用户总提交次数0
 * 题目难度Medium
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
 *
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
 *
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 *
 *
 *
 * 示例 1:
 *
 * ex-1
 *
 * 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
 * 输出：[2,3]
 * 解释：
 * - queries[0] 有两个盘子在蜡烛之间。
 * - queries[1] 有三个盘子在蜡烛之间。
 * 示例 2:
 *
 * ex-2
 *
 * 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * 输出：[9,0,0,0,0]
 * 解释：
 * - queries[0] 有 9 个盘子在蜡烛之间。
 * - 另一个查询没有盘子在蜡烛之间。
 *
 *
 * 提示：
 *
 * 3 <= s.length <= 105
 * s 只包含字符 '*' 和 '|' 。
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 * @author chenzw
 * @date 2021/10/31
 */
public class Solution5900 {
    //二分查找左右边界
    public int[] platesBetweenCandles(String s, int[][] queries) {
        List<Integer> list = new LinkedList<>();
        char[] cs = s.toCharArray();
        for(int i=0;i<cs.length;i++){
            if(cs[i]=='|'){
                list.add(i);
            }
        }
        int[] index = new int[list.size()];
        int i=0;
        for(int num:list){
            index[i++]=num;
        }
        int k=0;
        int[] ans = new int[queries.length];
        for(int[] q:queries){
            int left = ceil(index, q[0]);
            int right = floor(index,q[1]);
            if(left>-1&&right>-1&&index[left]<=q[1]&&index[right]>=q[0]&&left<right){
                ans[k] = index[right]-index[left]-right+left;
            }
            k++;
        }
        return ans;
    }

    private int ceil(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        while(left<right){
            int mid = (left+right)>>1;
            if(arr[mid]<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return left<arr.length?left:-1;
    }

    private int floor(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
            int mid = (left+right)>>1;
            if(arr[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution5900().platesBetweenCandles("**|*******************|**********************************************|************|*********|*****|*********************************************************************************************|***",
                new int[][]{{100,164}})));
    }
}
