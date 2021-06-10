package leetcode;

/**
 *
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *
 *
 * 提示：
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * @author chenzw
 * @date 2021/6/10
 */
public class Solution79 {
    public static boolean exist(char[][] board, String word) {
        boolean[][] visited  = new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]!=word.charAt(0))continue;
                visited[i][j]=true;
                if(dfs(board,word,i,j,0,visited)){
                    return true;
                }
                visited[i][j]=false;
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board,String word,int x,int y,int i,boolean[][] visited){
        //if(board[x][y]!=word.charAt(i))return false;
        if(i==word.length()-1)return true;
        int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        for(int[] d:directions){
            int nextX = x+d[0];
            if(nextX<0||nextX>=board.length)continue;
            int nextY = y+d[1];
            if(nextY<0||nextY>=board[nextX].length)continue;
            if(visited[nextX][nextY])continue;
            if(board[nextX][nextY]!=word.charAt(i+1))continue;
            visited[nextX][nextY]=true;
            if(dfs(board, word, nextX, nextY, i + 1, visited))return true;
            visited[nextX][nextY]=false;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED"));
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCEF"));
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCB"));
    }
}
