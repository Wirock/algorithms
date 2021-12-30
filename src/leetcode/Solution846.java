package leetcode;

import java.util.TreeMap;

/**
 * 846. 一手顺子
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 *
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * 输出：true
 * 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 *
 * 输入：hand = [1,2,3,4,5], groupSize = 4
 * 输出：false
 * 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
 *
 *
 * 提示：
 *
 * 1 <= hand.length <= 104
 * 0 <= hand[i] <= 109
 * 1 <= groupSize <= hand.length
 * @author chenzw
 * @date 2021/12/30
 */
public class Solution846 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for(int i:hand)treeMap.put(i,treeMap.getOrDefault(i,0)+1);
        while(!treeMap.isEmpty()){
            int first = treeMap.firstKey();
            for(int i=0;i<groupSize;i++){
                int key = first+i;
                Integer v = treeMap.get(key);
                if(v==null)return false;
                if(v==1)treeMap.remove(key);
                else treeMap.put(key,v-1);
            }
        }
        return true;
    }
}
