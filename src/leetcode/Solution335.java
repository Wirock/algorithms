package leetcode;

/**
 * 335. 路径交叉
 * 给你一个整数数组 distance 。
 *
 * 从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，向南移动 distance[2] 米，向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
 *
 * 判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：distance = [2,1,1,2]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：distance = [1,2,3,4]
 * 输出：false
 * 示例 3：
 *
 *
 * 输入：distance = [1,1,1,1]
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= distance.length <= 105
 * 1 <= distance[i] <= 105
 * @author chenzw
 * @date 2021/10/29
 */
public class Solution335 {
    //hash超时
    /*public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        Set<Long> set = new HashSet<>();
        long[] pos = new long[]{0L,0L};
        set.add(hash(pos));
        for(int i=0;i<n;i++){
            switch(i%4){
                case 0:
                    for(int j=0;j<distance[i];j++){
                        pos[1]++;
                        long cur = hash(pos);
                        if(set.contains(cur))return true;
                        set.add(cur);
                    }
                    break;
                case 1:
                    for(int j=0;j<distance[i];j++){
                        pos[0]--;
                        long cur = hash(pos);
                        if(set.contains(cur))return true;
                        set.add(cur);
                    }
                    break;
                case 2:
                    for(int j=0;j<distance[i];j++){
                        pos[1]--;
                        long cur = hash(pos);
                        if(set.contains(cur))return true;
                        set.add(cur);
                    }
                    break;
                case 3:
                    for(int j=0;j<distance[i];j++){
                        pos[0]++;
                        long cur = hash(pos);
                        if(set.contains(cur))return true;
                        set.add(cur);
                    }
                    break;
            }

        }
        return false;
    }

    private long hash(long[] pos){
        return (pos[0]+(long)1e5)*(long)1e6+pos[1]+(long)1e5;
    }*/

    //归纳,产生相交的线互相垂直，或在同一直线上
    //1.第i边和第i-3边相交 distance[i] >= distance[i-2]&&distance[i-1]<=distance[i-3]
    //2.第i边和第i-4边相交 distance[i]+distance[i-4]>=distance[i-2]&&distance[i-1]==distance[i-3]
    //3.第i边和第i-5边相交 distance[i]+distance[i-4]>=distance[i-2]&&distance[i-1]+distance[i-5]>=distance[i-3]
    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        if(n<4) return false;
        for(int i=3;i<n;i++){
            if(distance[i] >= distance[i-2]&&distance[i-1]<=distance[i-3])return true;
            if(i>=4&&distance[i-1]==distance[i-3]&&distance[i]+distance[i-4]>=distance[i-2])return true;
            if(i>=5&&distance[i]+distance[i-4]>=distance[i-2]&&distance[i-2]>distance[i-4]&&distance[i-1]+distance[i-5]>=distance[i-3]&&distance[i-1]<distance[i-3])return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution335().isSelfCrossing(new int[]{2,1,1,2}));
        System.out.println(new Solution335().isSelfCrossing(new int[]{1,2,3,4}));
        System.out.println(new Solution335().isSelfCrossing(new int[]{1,1,1,1}));
        System.out.println(new Solution335().isSelfCrossing(new int[]{1,1,2,1,1}));
        System.out.println(new Solution335().isSelfCrossing(new int[]{3,3,3,2,1,1}));
        System.out.println(new Solution335().isSelfCrossing(new int[]{1,1,2,2,3,3,4,4,10,4,4,3,3,2,2,1,1}));
    }
}
