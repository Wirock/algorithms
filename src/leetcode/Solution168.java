package leetcode;

/**
 * 168. Excel表列名称
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 *
 * 例如，
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 * 示例 1:
 *
 * 输入: 1
 * 输出: "A"
 * 示例 2:
 *
 * 输入: 28
 * 输出: "AB"
 * 示例 3:
 *
 * 输入: 701
 * 输出: "ZY"
 * @author chenzw
 * @date 2021/6/29
 */
public class Solution168 {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while(columnNumber>0){
            int r = (columnNumber-1)%26;
            sb.append((char)('A'+r));
            columnNumber = (columnNumber-1)/26;
        }
        sb.reverse();
        return sb.toString();
    }
}
