package leetcode;

import java.util.*;

/**
 * @author chenzw
 * @date 2021/3/20
 */
public class SolutionTest {

    /*public static boolean areSentencesSimilar(String sentence1, String sentence2) {
        String max = sentence1.length()>sentence2.length()?sentence1:sentence2;
        String min = sentence1.length()>sentence2.length()?sentence2:sentence1;
        String[] s1 = max.split(" ");
        String[] s2 = min.split(" ");
        int left1=0;
        int right1=s1.length-1;
        int left2=0;
        int right2=s2.length-1;
        int count=0;
        while(left1<=right1&&left2<=right2&&s2[left2].equals(s1[left1])){
            left1++;
            left2++;
        }
        while(left1<=right1&&left2<=right2&&s2[right2].equals(s1[right1])) {
            right1--;
            right2--;
        }
        if(left2>right2){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(areSentencesSimilar("My name is Haley","My Haley"));
        System.out.println(areSentencesSimilar("of", "A lot of words"));
        System.out.println(areSentencesSimilar("Eating right now","Eating"));
        System.out.println(areSentencesSimilar("Luky","Luccky"));
        System.out.println(areSentencesSimilar("CwFfRo regR","CwFfRo H regR"));
        System.out.println(areSentencesSimilar("S a C PMgzqylRCITUX A jF f LD","S A jF f LD"));
    }*/


    /*public static int countNicePairs(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int count=0;
        int mod=(int)1e9+7;
        for(int i=0;i<nums.length;i++){
            Integer diff = nums[i]-rev(nums[i]);
            int cur = map.getOrDefault(diff,0);
            count=(count+cur)%mod;
            map.put(diff,cur+1);
        }
        return count;
    }

    private static int rev(int num){
        int result=0;
        while(num>0){
            result=result*10+num%10;
            num/=10;
        }
        return result;
    }*/

    /*public String truncateSentence(String s, int k) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<k;i++){
            sb.append(strs[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }*/

    /*public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer,Integer> countMap = new HashMap<>();
        int[] answer = new int[k];
        for(int i=0;i<logs.length;i++){
            Set<Integer> set = map.get(logs[i][0]);
            if(set==null){
                set=new HashSet<>();
                map.put(logs[i][0],set);
            }
            if(!set.contains(logs[i][1])){
                set.add(logs[i][1]);
                countMap.put(logs[i][0],countMap.getOrDefault(logs[i][0],0)+1);
            }
        }
        for(Integer value:countMap.values()){
            if(value<k){
                answer[value-1]++;
            }
        }
        return answer;
    }*/

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int[] diffAbs = new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            diffAbs[i] = Math.abs(nums1[i]-nums2[i]);
        }
        return 0;
    }

    public static int countDifferentSubsequenceGCDs(int[] nums) {
        Map<String,Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums);
        dfs(true,nums,0,new StringBuilder(),set,map);
        return set.size();
    }

    private static void dfs(boolean choosePre,int[] nums,int index,StringBuilder sb,Set<Integer> set,Map<String,Integer> map){
        if(index==nums.length){
            return;
        }
        dfs(false,nums,index+1,sb,set,map);
        if(!choosePre&&nums[index]==nums[index-1]){
            return;
        }
        if("".equals(sb.toString())){
            sb.append(","+nums[index]);
            map.put(sb.toString(),nums[index]);
            set.add(nums[index]);
        }else{
            int gcd = gcd(map.get(sb.toString()), nums[index]);
            sb.append(","+nums[index]);
            map.put(sb.toString(),gcd);
            set.add(gcd);
        }
        dfs(true,nums,index+1,sb,set,map);
        sb.delete(sb.lastIndexOf(","),sb.length());
    }
    private static int gcd(int x,int y){
        int max = x>y?x:y;
        int min = x>y?y:x;
        int r = max%min;
        if(r==0)return min;
        return gcd(min,r);
    }

    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        int left = 0;
        int right = nums.length-1;
        while(left<right){
            if(nums[left]+nums[right]<=target){
                count+=right-left;
                left++;
            }else{
                right--;
            }
        }
        return count%1000000007;
    }

    public static int orchestraLayout(int num, int xPos, int yPos) {
        long n = 1;
        long margin = Math.min(xPos,yPos);
        long nums = num;
        margin = Math.min(margin,nums-1-xPos);
        margin = Math.min(margin,nums-1-yPos);
        /*for(int i=0;i<margin;i++){
            n = (n+4*(nums-1))%9;
            nums -= 2;
        }*/
        n+=(((nums+nums-2*margin)%9)*(2*margin)%9)%9;
        nums = nums-2*margin;
        xPos-=margin;
        yPos-=margin;
        if(xPos==0){
            n=(n+yPos)%9;
        }else if(yPos==0){
            n=(n+4*(nums-1)-xPos)%9;
        }else if(xPos==nums-1){
            n=(n+3*(nums-1)-yPos)%9;
        }else if(yPos==nums-1){
            n=(n+nums+xPos-1)%9;
        }
        return (int)(n==0?9:n);
    }
    public static void main(String[] args) {
     /*   System.out.println(countDifferentSubsequenceGCDs(new int[]{6,10,3}));
        System.out.println(countDifferentSubsequenceGCDs(new int[]{5,15,40,5,6}));*/
        System.out.println(orchestraLayout(4,1,1));
        System.out.println(orchestraLayout(4,1,2));
        System.out.println(orchestraLayout(4,1,3));
        System.out.println(orchestraLayout(4,2,1));
        System.out.println(orchestraLayout(4,2,2));
        System.out.println(orchestraLayout(4,2,3));
        System.out.println(orchestraLayout(4,3,1));
        System.out.println(orchestraLayout(4,3,2));
        System.out.println(orchestraLayout(4,3,3));
        System.out.println(orchestraLayout(5,1,1));
        System.out.println(orchestraLayout(5,1,2));
        System.out.println(orchestraLayout(5,1,3));
        System.out.println(orchestraLayout(5,2,1));
        System.out.println(orchestraLayout(5,2,2));
        System.out.println(orchestraLayout(5,2,3));
        System.out.println(orchestraLayout(5,3,1));
        System.out.println(orchestraLayout(5,3,2));
        System.out.println(orchestraLayout(5,3,3));
        long start = System.currentTimeMillis();
        System.out.println(orchestraLayout((int)1e9,(int)1e5,(int)1e5));
        System.out.println(orchestraLayout((int)1e9,(int)1e5,(int)1e7));
        System.out.println(orchestraLayout((int)1e9,(int)1e3,(int)1e5));
        System.out.println(orchestraLayout((int)1e9,(int)1e5,(int)1e6));
        System.out.println(System.currentTimeMillis()-start);
    }
}
