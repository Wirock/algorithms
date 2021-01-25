package problem.chapter1;

/**
 * 1.5汉诺塔问题
 * @author chenzw
 * @date 2021/1/25
 */
public class HanoiProblem {

    private void process(int num ,String from,String to,String mid){
        if(num<1){
            throw new RuntimeException("illegal num");
        }
        if(num == 1){
            System.out.println("Move from "+from+" to "+to);
        }else{
            process(num-1,from,mid,to);
            System.out.println("Move from "+from+" to "+to);
            process(num-1,mid,to,from);
        }
    }
}
