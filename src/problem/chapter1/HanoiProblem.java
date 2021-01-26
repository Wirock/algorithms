package problem.chapter1;

/**
 * 1.5汉诺塔问题,打印过程，返回步数
 * @author chenzw
 * @date 2021/1/25
 */
public class HanoiProblem {

    public int process(int num ,String from,String to,String mid){
        if(num<1){
            throw new RuntimeException("illegal num");
        }
        if(num == 1){
            System.out.println("Move from "+from+" to "+to);
            return 1;
        }else{
            int before = process(num - 1, from, mid, to);
            System.out.println("Move from "+from+" to "+to);
            int after = process(num - 1, mid, to, from);
            return before+after+1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new HanoiProblem().process(3,"A","C","B"));
    }
}
