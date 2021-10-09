package leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * 352. 将数据流变为多个不相交区间
 *  给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。
 *
 * 实现 SummaryRanges 类：
 *
 * SummaryRanges() 使用一个空数据流初始化对象。
 * void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 *
 *
 * 示例：
 *
 * 输入：
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * 输出：
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 *
 * 解释：
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // 返回 [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
 *
 *
 * 提示：
 *
 * 0 <= val <= 104
 * 最多调用 addNum 和 getIntervals 方法 3 * 104 次
 *
 *
 * 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
 * @author chenzw
 * @date 2021/10/9
 */
public class Solution352 {
    class SummaryRanges {

        //一个键值对表示一个区间
        TreeMap<Integer,Integer> intervals;
        public SummaryRanges() {
            intervals = new TreeMap<>();
        }

        //新加入一个元素，对区间的改变有5中情况
        //1.val在一个已有区间中，不造成任何改变
        //2.val紧贴一个区间的左边界，扩展这个区间的左边界为val
        //3.val紧贴一个区间的右边界，扩展这个区间的右边界为val
        //4.val同时紧贴一个区间的右边界和另一个区间的左边界，则这两个区间和val一起组成一个新的区间
        //5.val不在区间中，左右相邻也不是区间边界，val自身组成一个区间
        public void addNum(int val) {
            Map.Entry<Integer, Integer> floor = intervals.floorEntry(val);
            Map.Entry<Integer, Integer> ceil = intervals.ceilingEntry(val);
            if(floor!=null&&floor.getValue() >= val)return;//1
            if(floor!=null&&floor.getValue().equals(val-1)&&(ceil==null||ceil.getKey()>val+1)) {//2
                intervals.put(floor.getKey(),val);
                return;
            }
            if((floor==null||floor.getValue()<val-1)&&ceil!=null&&ceil.getKey().equals(val+1)) {//3
                intervals.remove(ceil.getKey());
                intervals.put(val,ceil.getValue());
                return;
            }
            if(floor!=null&&floor.getValue().equals(val-1)&&ceil!=null&&ceil.getKey().equals(val+1)) {//4
                intervals.put(floor.getKey(),ceil.getValue());
                intervals.remove(ceil.getKey());
                return;
            }
            if((floor==null||floor.getValue()<val-1)&&(ceil==null||ceil.getKey()>val+1)) {//5
                intervals.put(val,val);
                return;
            }
        }

        public int[][] getIntervals() {
            int[][] ans = new int[intervals.size()][2];
            int i = 0;
            for(Map.Entry<Integer,Integer> e:intervals.entrySet()){
                ans[i][0] = e.getKey();
                ans[i][1] = e.getValue();
                i++;
            }
            return ans;
        }
    }
}
