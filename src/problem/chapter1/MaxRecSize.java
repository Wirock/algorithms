package problem.chapter1;

import java.util.Stack;

/**
 * 1.8求最大子矩阵的大小（1.7的应用）
 *
 * @author chenzw
 * @date 2021/3/1
 */
public class MaxRecSize {
    public int maxRecSize(int[][] map){
        if(map==null||map.length==0||map[0].length==0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for(int i=0;i<map.length;i++){//依次以每行为底，计算每行向上连续1的数量，存为高数组，以高数组来切割，计算最大的矩阵
            //1.统计每一行的高，存入height数组
            for(int j=0;j<map[0].length;j++){
                height[j]=map[i][j]==0?0:height[j]+1;
            }
            //2.计算每一行能取得的最大面积，取最大值
            maxArea = Math.max(maxRecFromBottom(height),maxArea);
        }
        return maxArea;
    }

    //向左找到最近的比它小的高，向右找到最近的比它小的高，它们之间的部分就是当前高能扩展的最大矩形
    //最快的方式是使用单调栈
    private int maxRecFromBottom(int[] height){
        if(height==null||height.length==0){
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<height.length;i++){
            while(stack.isEmpty()&&height[i]<=height[stack.peek()]){
                int j = stack.pop();
                int k = stack.isEmpty()? -1:stack.peek();
                int curArea = (i-k-1)*height[j];
                maxArea = Math.max(maxArea,curArea);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty()?-1:stack.peek();
            int curArea = (height.length-k-1)*height[j];
            maxArea = Math.max(maxArea,curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {

    }
}
