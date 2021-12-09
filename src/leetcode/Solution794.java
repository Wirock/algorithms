package leetcode;

/**
 * 794. 有效的井字游戏
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 *
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = ["O  ","   ","   "]
 * 输出：false
 * 解释：玩家 1 总是放字符 "X" 。
 * 示例 2：
 *
 *
 * 输入：board = ["XOX"," X ","   "]
 * 输出：false
 * 解释：玩家应该轮流放字符。
 * 示例 3：
 *
 *
 * 输入：board = ["XXX","   ","OOO"]
 * 输出：false
 * Example 4:
 *
 *
 * 输入：board = ["XOX","O O","XOX"]
 * 输出：true
 *
 *
 * 提示：
 *
 * board.length == 3
 * board[i].length == 3
 * board[i][j] 为 'X'、'O' 或 ' '
 * @author chenzw
 * @date 2021/12/9
 */
public class Solution794 {

    //分类讨论
    public boolean validTicTacToe(String[] board) {
        int ocount = 0;
        int xcount = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i].charAt(j)=='X')xcount++;
                else if(board[i].charAt(j)=='O')ocount++;
            }
        }
        if(ocount>xcount)return false;
        if(xcount-ocount>1)return false;
        int xWinR = -1;
        int xWinC = -1;
        int oWinR = -1;
        int oWinC = -1;
        int xs = -1;
        int os = -1;
        int xbs = -1;
        int obs = -1;
        for(int i=0;i<3;i++){
            if(board[i].charAt(0)==board[i].charAt(1)&&board[i].charAt(0)==board[i].charAt(2)){
                if(board[i].charAt(0)=='X'){
                    if(xWinR>-1)return false;
                    xWinR = i;
                }else if(board[i].charAt(0)=='O'){
                    if(oWinR>-1)return false;
                    oWinR = i;
                }
            }
        }
        if(oWinR>-1&&xWinR>-1)return false;

        for(int i=0;i<3;i++){
            if(board[0].charAt(i)==board[1].charAt(i)&&board[0].charAt(i)==board[2].charAt(i)){
                if(board[0].charAt(i)=='X'){
                    if(xWinC>-1)return false;
                    xWinC = i;
                }else if(board[0].charAt(i)=='O'){
                    if(oWinC>-1)return false;
                    oWinC = i;
                }
            }
        }
        if(oWinC>-1&&xWinC>-1)return false;

        if(board[0].charAt(0)==board[1].charAt(1)&&board[0].charAt(0)==board[2].charAt(2)){
            if(board[0].charAt(0)=='X')xs = 1;
            else if(board[0].charAt(0)=='O')os = 1;
        }
        if(board[0].charAt(2)==board[1].charAt(1)&&board[0].charAt(2)==board[2].charAt(0)){
            if(board[0].charAt(2)=='X')xbs = 1;
            else if(board[0].charAt(2)=='O')obs = 1;
        }
        if(xWinR+xWinC+xs+xbs>-4&&oWinR+oWinC+os+obs>-4)return false;
        if(xWinR+xWinC+xs+xbs>-4&&xcount==ocount)return false;
        if(xcount>ocount&&oWinR+oWinC+os+obs>-4)return false;
        return true;
    }
}
