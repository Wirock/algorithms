package leetcode.biweekly.contest56;

/**
 * 5794. 求和游戏
 * Alice 和 Bob 玩一个游戏，两人轮流行动，Alice 先手 。
 *
 * 给你一个 偶数长度 的字符串 num ，每一个字符为数字字符或者 '?' 。每一次操作中，如果 num 中至少有一个 '?' ，那么玩家可以执行以下操作：
 *
 * 选择一个下标 i 满足 num[i] == '?' 。
 * 将 num[i] 用 '0' 到 '9' 之间的一个数字字符替代。
 * 当 num 中没有 '?' 时，游戏结束。
 *
 * Bob 获胜的条件是 num 中前一半数字的和 等于 后一半数字的和。Alice 获胜的条件是前一半的和与后一半的和 不相等 。
 *
 * 比方说，游戏结束时 num = "243801" ，那么 Bob 获胜，因为 2+4+3 = 8+0+1 。如果游戏结束时 num = "243803" ，那么 Alice 获胜，因为 2+4+3 != 8+0+3 。
 * 在 Alice 和 Bob 都采取 最优 策略的前提下，如果 Alice 获胜，请返回 true ，如果 Bob 获胜，请返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = "5023"
 * 输出：false
 * 解释：num 中没有 '?' ，没法进行任何操作。
 * 前一半的和等于后一半的和：5 + 0 = 2 + 3 。
 * 示例 2：
 *
 * 输入：num = "25??"
 * 输出：true
 * 解释：Alice 可以将两个 '?' 中的一个替换为 '9' ，Bob 无论如何都无法使前一半的和等于后一半的和。
 * 示例 3：
 *
 * 输入：num = "?3295???"
 * 输出：false
 * 解释：Bob 总是能赢。一种可能的结果是：
 * - Alice 将第一个 '?' 用 '9' 替换。num = "93295???" 。
 * - Bob 将后面一半中的一个 '?' 替换为 '9' 。num = "932959??" 。
 * - Alice 将后面一半中的一个 '?' 替换为 '2' 。num = "9329592?" 。
 * - Bob 将后面一半中最后一个 '?' 替换为 '7' 。num = "93295927" 。
 * Bob 获胜，因为 9 + 3 + 2 + 9 = 5 + 9 + 2 + 7 。
 *
 *
 * 提示：
 *
 * 2 <= num.length <= 105
 * num.length 是 偶数 。
 * num 只包含数字字符和 '?' 。
 * @author chenzw
 * @date 2021/7/11
 */
public class Solution5794 {
    //设lsum为左半部分数字的和，rsum为右半部分数字的和，left为左半部分问号数，r为右半部分的问号数
    //最后一轮最多只有一种选择使左右两边的和相等
    //若为奇数个问号（(left+right)%2==1），则最后一轮是先手方操作，必能避开这个使左右和相等的选择。
    //若为偶数个问号，则最后一轮后手方操作。每一轮先手方操作时会尽量扩大左右差距，会在和较大的一边取9，或者和较小的一边取0。
    //后手方没有办法缩小差距，最好的选择是在和较大的一边取0，或者和较小的一边取9。如果左右两部分的问号数相同，那么最终结果差距不变。只要最终lsum=rsums后手方
    //如果左右两部分问号数不同，那么只有他们相差的那部分abs(left-right)个问号会差生差别。由于left+right是偶数，所以left-right也是偶数，差距的部分在同一侧，
    //所以先手方和后手方会选择相反两个边界值9和0，所以最终和是否相等即为 lsum-rsum与(right-left)/2*9是否相等
    public boolean sumGame(String num) {
        int lsum = 0,rsum = 0;
        int left = 0;
        int right = 0;
        int half = num.length()/2;
        for(int i=0;i<num.length();i++){
            if(num.charAt(i)=='?') {
                if(i<half)left++;
                else right++;
            }else {
                if(i<half)lsum += num.charAt(i) - '0';
                else rsum += num.charAt(i) - '0';
            }
        }
        if((left+right)%2==1)return true;//问号奇数个，最后是先手操作，必能使左右和不同，先手必胜
        return lsum-rsum-(right-left)/2*9!=0;//问号偶数个，左右问号逐个相互抵消，最后多出的部分每两个可以得到9
    }
}
