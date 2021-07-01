package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 * @author chenzw
 * @date 2021/6/23
 */
public class Solution119 {
    //规律：用C(n,m)表示从m个元素中选出n个的组合数，第k行的数据为：C(0,k-1),C(1,k-1),...C(k-1,k-1)
    //C(n,m)=m!/((m-n)!*n!),C(n-1,m)=m!/((m-n+1)!*(n-1)!)
    //有 C(n,m)=C(n-1,m)*(m-n+1)/n
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex);
        if(rowIndex<0)return row;
        row.add(1);
        for(int i=1;i<=rowIndex;i++){
            row.add((int)((long)row.get(i-1)*(rowIndex-i+1)/i));
        }
        return row;
    }
}
