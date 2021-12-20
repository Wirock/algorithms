package leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 475. 供暖器
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 *
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 *
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 *
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 *
 *
 *
 * 示例 1:
 *
 * 输入: houses = [1,2,3], heaters = [2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 * 示例 2:
 *
 * 输入: houses = [1,2,3,4], heaters = [1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 * 示例 3：
 *
 * 输入：houses = [1,5], heaters = [2]
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= houses.length, heaters.length <= 3 * 104
 * 1 <= houses[i], heaters[i] <= 109
 * @author chenzw
 * @date 2021/12/20
 */
public class Solution475 {
    //1.treeSet二分查找左右边界
    public int findRadius1(int[] houses, int[] heaters) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for(int i:heaters){
            treeSet.add(i);
        }
        int ans = 0;
        for(int i:houses){
            Integer left = treeSet.floor(i);
            Integer right = treeSet.ceiling(i);
            if(left!=null&&right!=null)Math.max(ans,Math.min(i-left,right-i));
            else if(left!=null)ans = Math.max(ans,i-left);
            else if(right!=null) ans = Math.max(ans,right-i);
        }
        return ans;
    }

    //2.二分+双指针
    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int l = 0, r = (int) 1e9;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(houses, heaters, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }
    boolean check(int[] houses, int[] heaters, int x) {
        int n = houses.length, m = heaters.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && houses[i] > heaters[j] + x) j++;
            if (j < m && heaters[j] - x <= houses[i] && houses[i] <= heaters[j] + x) continue;
            return false;
        }
        return true;
    }

}
