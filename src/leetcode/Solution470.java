package leetcode;

import java.util.Random;

/**
 * 470. 用 Rand7() 实现 Rand10()
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 *
 * 不要使用系统的 Math.random() 方法。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: [7]
 * 示例 2:
 *
 * 输入: 2
 * 输出: [8,4]
 * 示例 3:
 *
 * 输入: 3
 * 输出: [8,1,10]
 *
 *
 * 提示:
 *
 * rand7 已定义。
 * 传入参数: n 表示 rand10 的调用次数。
 *
 *
 * 进阶:
 *
 * rand7()调用次数的 期望值 是多少 ?
 * 你能否尽量少调用 rand7() ?
 * @author chenzw
 * @date 2021/9/5
 */
public class Solution470 {
    public int rand10() {
        //使first奇偶的概率都是1/2
        //second取[1,5]的概率相同
        int first, second;
        while ((first = rand7()) > 6);//[1,6]
        while ((second = rand7()) > 5);//[1,5]
        return (first&1) == 1 ? second : 5+second;
    }
    private int rand7(){
        return new Random(System.currentTimeMillis()).nextInt(7)+1;
    }
}
