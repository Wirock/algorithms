package leetcode.weekly.contest297;

/**
 * 2305. 公平分发饼干
 * 给你一个整数数组 cookies ，其中 cookies[i] 表示在第 i 个零食包中的饼干数量。另给你一个整数 k 表示等待分发零食包的孩子数量，所有 零食包都需要分发。在同一个零食包中的所有饼干都必须分发给同一个孩子，不能分开。
 *
 * 分发的 不公平程度 定义为单个孩子在分发过程中能够获得饼干的最大总数。
 *
 * 返回所有分发的最小不公平程度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：cookies = [8,15,10,20,8], k = 2
 * 输出：31
 * 解释：一种最优方案是 [8,15,8] 和 [10,20] 。
 * - 第 1 个孩子分到 [8,15,8] ，总计 8 + 15 + 8 = 31 块饼干。
 * - 第 2 个孩子分到 [10,20] ，总计 10 + 20 = 30 块饼干。
 * 分发的不公平程度为 max(31,30) = 31 。
 * 可以证明不存在不公平程度小于 31 的分发方案。
 * 示例 2：
 *
 * 输入：cookies = [6,1,3,2,2,4,1,2], k = 3
 * 输出：7
 * 解释：一种最优方案是 [6,1]、[3,2,2] 和 [4,1,2] 。
 * - 第 1 个孩子分到 [6,1] ，总计 6 + 1 = 7 块饼干。
 * - 第 2 个孩子分到 [3,2,2] ，总计 3 + 2 + 2 = 7 块饼干。
 * - 第 3 个孩子分到 [4,1,2] ，总计 4 + 1 + 2 = 7 块饼干。
 * 分发的不公平程度为 max(7,7,7) = 7 。
 * 可以证明不存在不公平程度小于 7 的分发方案。
 *
 *
 * 提示：
 *
 * 2 <= cookies.length <= 8
 * 1 <= cookies[i] <= 105
 * 2 <= k <= cookies.length
 * @author chenzw
 * @date 2022/7/10
 */
public class Solution2305 {
    /*
        令n = cookies.length
        2 <= n <= 8
        n数量较小，考虑枚举所有分法
        不是每个人都要有，所以每个包有k个分法
        共有 看k^n种分法，最大为8^8=2^24
        回溯+剪枝降低提升效率
     */
    int min = Integer.MAX_VALUE;
    int n;
    int k;
    int[] cookies;
    int[] children;
    public int distributeCookies(int[] cookies, int k) {
        this.n = cookies.length;
        this.k = k;
        this.cookies = cookies;
        this.children = new int[k];
        trackback(0, 0);
        return min;
    }

    /*private void trackback(int i){
        if(i==n){
            int max = 0;
            for(int v:children){
                max = Math.max(max,v);
            }
            min = Math.min(min,max);
            return;
        }
        for(int j=0;j<k;j++){
            children[j] += cookies[i];
            if(children[j]<min){//剪枝
                trackback(i+1);
            }
            children[j] -= cookies[i];
        }
    }*/
    //优化，max可以在回溯的过程种计算
    private void trackback(int i, int max){
        if(i==n){
            min = Math.min(min,max);
            return;
        }
        for(int j=0;j<k;j++){
            children[j] += cookies[i];
            if(children[j]<min) {//剪枝
                trackback(i + 1, Math.max(max, children[j]));
            }
            children[j] -= cookies[i];
        }
    }

}
