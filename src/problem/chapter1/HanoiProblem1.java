package problem.chapter1;

/**
 * 1.5汉诺塔问题,左右不能直接移动给对方，必须经过中间。打印过程，返回步数。
 * 递归实现
 * @author chenzw
 * @date 2021/1/25
 */
public class HanoiProblem1 {
    //这个方法没有考虑中间作为起点和终点的情况
    public int process1(int num ,String from,String to,String mid){
        if(num<1){
            throw new RuntimeException("illegal num");
        }
        if(num == 1){
            System.out.println("Move 1 from "+from+" to "+mid);
            System.out.println("Move 1 from "+mid+" to "+to);
            return 2;
        }else{
            int process1 = process1(num - 1, from, to, mid);
            System.out.println("Move "+num+" from "+from+" to "+mid);
            int process2 = process1(num-1,to,from,mid);
            System.out.println("Move "+num+" from "+mid+" to "+to);
            int process3 = process1(num-1,from,to,mid);
            return process1+process2+process3+2;
        }
    }

    //考虑中间作为起点和终点的情况
    public int process2(int num ,String left,String mid,String right,String from,String to){
        if(num<1){
            throw new RuntimeException("illegal num");
        }
        if(num == 1){
            if(from.equals(mid)||to.equals(mid)){
                System.out.println("Move 1 from "+from+" to "+to);
                return 1;
            }else{
                System.out.println("Move 1 from "+from+" to "+mid);
                System.out.println("Move 1 from "+mid+" to "+to);
                return 2;
            }
        }else{
            if(from.equals(mid)||to.equals(mid)){
                String target = from.equals(left)?right:(from.equals(right)?left:(to.equals(left)?right:left));
                int count1 = process2(num - 1, left, mid, right, from, target);
                System.out.println("Move "+num+" from "+from+" to "+to);
                int count2 = process2(num - 1, left, mid, right, target, from);
                return count1+count2+1;
            }else{
                int count1 = process2(num - 1, left, mid, right, from, to);
                System.out.println("Move "+num+" from "+from+" to "+mid);
                int count2 = process2(num - 1, left, mid, right, to,from);
                System.out.println("Move "+num+" from "+mid+" to "+to);
                int count3 = process2(num - 1, left, mid, right, from, to);
                return count1+count2+count3+2;
            }
        }
    }
    public static void main(String[] args) {
//        System.out.println(new HanoiProblem1().process1(3,"A","C","B"));
        System.out.println(new HanoiProblem1().process2(3,"A","B","C","A","C"));
    }
}
