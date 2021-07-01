package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 * @author chenzw
 * @date 2021/6/23
 */
public class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>(numRows);
        if(numRows<1)return  ans;
        List<Integer> lastRow = new ArrayList<>(1);
        lastRow.add(1);
        ans.add(lastRow);
        for(int i=2;i<=numRows;i++){
            List<Integer> curRow = new ArrayList<>(i);
            curRow.add(1);
            for(int j=1;j<i-1;j++){
                curRow.add(lastRow.get(j-1)+lastRow.get(j));
            }
            curRow.add(1);
            ans.add(curRow);
            lastRow = curRow;
        }
        return ans;
    }
}
