package leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 5733. 最近的房间
 * 一个酒店里有 n 个房间，这些房间用二维整数数组 rooms 表示，其中 rooms[i] = [roomIdi, sizei] 表示有一个房间号为 roomIdi 的房间且它的面积为 sizei 。每一个房间号 roomIdi 保证是 独一无二 的。
 *
 * 同时给你 k 个查询，用二维数组 queries 表示，其中 queries[j] = [preferredj, minSizej] 。第 j 个查询的答案是满足如下条件的房间 id ：
 *
 * 房间的面积 至少 为 minSizej ，且
 * abs(id - preferredj) 的值 最小 ，其中 abs(x) 是 x 的绝对值。
 * 如果差的绝对值有 相等 的，选择 最小 的 id 。如果 没有满足条件的房间 ，答案为 -1 。
 *
 * 请你返回长度为 k 的数组 answer ，其中 answer[j] 为第 j 个查询的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
 * 输出：[3,-1,3]
 * 解释：查询的答案如下：
 * 查询 [3,1] ：房间 3 的面积为 2 ，大于等于 1 ，且号码是最接近 3 的，为 abs(3 - 3) = 0 ，所以答案为 3 。
 * 查询 [3,3] ：没有房间的面积至少为 3 ，所以答案为 -1 。
 * 查询 [5,2] ：房间 3 的面积为 2 ，大于等于 2 ，且号码是最接近 5 的，为 abs(3 - 5) = 2 ，所以答案为 3 。
 * 示例 2：
 *
 * 输入：rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
 * 输出：[2,1,3]
 * 解释：查询的答案如下：
 * 查询 [2,3] ：房间 2 的面积为 3 ，大于等于 3 ，且号码是最接近的，为 abs(2 - 2) = 0 ，所以答案为 2 。
 * 查询 [2,4] ：房间 1 和 3 的面积都至少为 4 ，答案为 1 因为它房间编号更小。
 * 查询 [2,5] ：房间 3 是唯一面积大于等于 5 的，所以答案为 3 。
 *
 *
 * 提示：
 *
 * n == rooms.length
 * 1 <= n <= 10^5
 * k == queries.length
 * 1 <= k <= 10^4
 * 1 <= roomIdi, preferredj <= 10^7
 * 1 <= sizei, minSizej <= 10^7
 *
 *
 * 思路：简单思路是，对于每个查询，筛选出面积大于等于要求的房间，按id进行排序，然后进行二分查找左右两端的id,对比找出比较接近的房间
 * 按照上述实现，时间复杂度为 o(knlogn),会超出时间限制，故需对算法进行优化
 * 每次查询筛选出的数据，面积下线低的会包含上限低的部分，重复做排序动作没有必要。
 * 可以利用BST,让面积上限高的查询先进行,查询出的房间id加入BST中,再查询下限低的，依次加入BST。再进行二分查询
 * 时间复杂度可降至o(klogn)
 *
 *
 * @author chenzw
 * @date 2021/5/1
 */
public class Solution5733 {
    public static int[] closestRoom(int[][] rooms, int[][] queries) {
        //对queries进行排序，面积从高到低，为保证结果集按原来查询的顺序返回，需记录查询的坐标
        int[][] q = new int[queries.length][3];
        for(int i=0;i<queries.length;i++){
            q[i][0] = queries[i][0];
            q[i][1] = queries[i][1];
            q[i][2] = i;
        }
        Arrays.sort(q,(x,y)->y[1]-x[1]);
        //对房间进行排序，按面积从高到低
        Arrays.sort(rooms,(x,y)->y[1]-x[1]);

        TreeSet<Integer> set = new TreeSet<>();

        int[] ans = new int[q.length];
        int j= 0;
        for(int i=0;i<q.length;i++){
            while(j<rooms.length&&rooms[j][1]>=q[i][1]){
                set.add(rooms[j][0]);
                j++;
            }
            Integer left = set.floor(q[i][0]);
            Integer right = set.ceiling(q[i][0]);
            if(left==null&&right==null){
                ans[q[i][2]]=-1;
            }else if(right==null){
                ans[q[i][2]]=left;
            }else if(left==null){
                ans[q[i][2]]=right;
            }else{
                ans[q[i][2]]=right-q[i][0]>=q[i][0]-left?left:right;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(closestRoom(new int[][]{{2,2},{1,2},{3,2}},new int[][]{{3,1},{3,3},{5,2}})));
        System.out.println(Arrays.toString(closestRoom(new int[][]{{1,4},{2,3},{3,5},{4,1},{5,2}},new int[][]{{2,3},{2,4},{2,5}})));
    }
}
