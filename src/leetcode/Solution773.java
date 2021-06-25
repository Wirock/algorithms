package leetcode;

import java.util.*;

/**
 * 773. 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 *
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 *
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 *
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 *
 * 示例：
 *
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 * 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 * 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * 输入：board = [[3,2,4],[1,5,0]]
 * 输出：14
 * 提示：
 *
 * board 是一个如上所述的 2 x 3 的数组.
 * board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
 * @author chenzw
 * @date 2021/6/26
 */
public class Solution773 {
    //bfs
    public int slidingPuzzle(int[][] board) {
        int[][] target = new int[][]{{1,2,3},{4,5,0}};
        if(equals(board,target))return 0;
        Set<Integer> set = new HashSet<>();
        Queue<int[][]> queue = new LinkedList<>();
        queue.add(board);
        set.add(toInt(board));
        int level = 1;
        int size = 1;
        int nextSize = 0;
        while(!queue.isEmpty()){
            int[][] cur = queue.poll();
            List<int[][]> nextList = getNext(cur);
            for(int[][] next:nextList){
                int nextInt = toInt(next);
                if(!set.contains(nextInt)){
                    if(equals(next,target))return level;
                    set.add(nextInt);
                    queue.add(next);
                    nextSize++;
                }
            }
            if(--size==0){
                size = nextSize;
                nextSize = 0;
                level++;
            }
        }
        return -1;
    }

    private List<int[][]> getNext(int[][] board) {
        List<int[][]> list = new ArrayList<>();
        int[][] next = new int[2][3];
        int x = 0;
        int y = 0;
        for (int m = 0; m < 2; m++) {
            for (int n = 0; n < 3; n++) {
                next[m][n] = board[m][n];
                if(next[m][n]==0) {
                    x = m;
                    y = n;
                }
            }
        }
        swap(next,x,y,(x+1)%2,y);
        list.add(next);
        if(y>0){
            next = copy(board);
            swap(next,x,y,x,y-1);
            list.add(next);
        }
        if(y<2){
            next = copy(board);
            swap(next,x,y,x,y+1);
            list.add(next);
        }
        return list;
    }
    private void swap(int[][] board,int x1,int y1,int x2,int y2){
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }

    private int[][] copy(int[][] board){
        int[][] next = new int[2][3];
        for (int m = 0; m < 2; m++) {
            for (int n = 0; n < 3; n++) {
                next[m][n] = board[m][n];
            }
        }
        return next;
    }

    private boolean equals(int[][] b1,int[][] b2){
        for (int m = 0; m < 2; m++) {
            for (int n = 0; n < 3; n++) {
                if(b1[m][n]!=b2[m][n])return false;
            }
        }
        return true;
    }

    private int toInt(int[][] b){
        int i = 0;
        for (int m = 0; m < 2; m++) {
            for (int n = 0; n < 3; n++) {
                i = i*10+b[m][n];
            }
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(new Solution773().slidingPuzzle(new int[][]{{1,2,3},{4,0,5}}));
        System.out.println(new Solution773().slidingPuzzle(new int[][]{{1,2,3},{4,0,5}}));
        System.out.println(new Solution773().slidingPuzzle(new int[][]{{4,1,2},{5,0,3}}));
        System.out.println(new Solution773().slidingPuzzle(new int[][]{{3,2,4},{1,5,0}}));
    }
}
