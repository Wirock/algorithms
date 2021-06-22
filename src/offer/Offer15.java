package offer;

/**
 * 剑指 Offer 15. 二进制中1的个数
 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 *
 *
 *
 * 示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 *
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 *
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *
 *
 * 提示：
 *
 * 输入必须是长度为 32 的 二进制串 。
 * @author chenzw
 * @date 2021/6/23
 */
public class Offer15 {
    // you need to treat n as an unsigned value
    //位运算二分
    public int hammingWeight(int n) {
        /*0x55555555 = 0101 0101 0101 0101 0101 0101 0101 0101
         计算结果每两位上的数表示这两位上1的数量
         比如：n的二进制为 11100100，则（n>>>1）&0x55555555 = 1010000
         有 k = n - ((n >>> 1) & 0x55555555) = 11000100
         n和k每两位对比
         n: 11  10  01  00
         k: 11  01  01  00

         则有这四组分别为n(i),k(i),i为坐标1到4，则有n(i)中有k(i)个1
        */
        n = n - ((n >>> 1) & 0x55555555);

        /*(n >>> 2) & 0x33333333=0011 0011 0011 0011 0011 0011 0011 0011
        每四位一组，即在上一步的的分组基础上每两组相加，得到每四位中1的数量
        这里的n是最开始的输入，用k来表示计算后的n,
        设上一步的k位k',则这一次的k(1)=k'(1)+k'(2)=11+01=100,k(2)=k'(3)+k'(4)=01+00=01
        n: 1110  0100
        k: 0100  0001
        仍然是n(i)中有k(i)个1
         */
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);//0011 0011 0011 0011 0011 0011 0011 0011
        //依此类推，8位一组
        n = (n + (n >>> 4)) & 0x0f0f0f0f;//0000 1111 0000 1111 0000 1111 0000 1111
        //16位一组，这里没有与上掩码是因为最终结果最多为32，即每组只有低6位有效，而这里每组都是两个8位2进制相加，高八位不会对计算造成影响，在最终结果做一次处理即可
        n = n + (n >>> 8);
        //32位一组，没有与上掩码与上一步同理
        n = n + (n >>> 16);
        return n & 0x3f;
    }
}
