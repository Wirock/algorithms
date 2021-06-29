package leetcode;

/**
 * 1819. 序列中不同最大公约数的数目
 * 给你一个由正整数组成的数组 nums 。
 *
 * 数字序列的 最大公约数 定义为序列中所有整数的共有约数中的最大整数。
 *
 * 例如，序列 [4,6,16] 的最大公约数是 2 。
 * 数组的一个 子序列 本质是一个序列，可以通过删除数组中的某些元素（或者不删除）得到。
 *
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 计算并返回 nums 的所有 非空 子序列中 不同 最大公约数的 数目 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：nums = [6,10,3]
 * 输出：5
 * 解释：上图显示了所有的非空子序列与各自的最大公约数。
 * 不同的最大公约数为 6 、10 、3 、2 和 1 。
 * 示例 2：
 *
 * 输入：nums = [5,15,40,5,6]
 * 输出：7
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 2 * 10^5
 * @author chenzw
 * @date 2021/5/29
 */
public class Solution1819 {
    //逆向思维，枚举
    //如果一个数x是一个子序列的最大公约数，则这个子序列里的元素都是x的倍数
    //故只要找出nums的最大值max，枚举[1,max]每个数x的倍数kx(1=<k<=max/x)中存在于nums中的部分，验证他们的最大公约数是否是x
    public static int countDifferentSubsequenceGCDs(int[] nums) {
        int max = 0;
        int[] count = new int[(int)2e5+1];
        for(int num:nums){
            max = Math.max(max,num);
            count[num]++;
        }
        int ans = 0;
        for(int i=1;i<=max;i++){
            int t=0;
            for(int j=i;j<=max;j+=i){
                if(count[j]>0) {
                    if(t==0)t=j;
                    else t = gcd(t, j);
                    if(t==i) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    private static int gcd(int x,int y){
        int max = x>y?x:y;
        int min = x>y?y:x;
        int r = max%min;
        if(r==0)return min;
        return gcd(min,r);
    }

    public static void main(String[] args) {
        System.out.println(countDifferentSubsequenceGCDs(new int[]{6,10,3}));
        System.out.println(countDifferentSubsequenceGCDs(new int[]{5,15,40,5,6}));
    }
}
