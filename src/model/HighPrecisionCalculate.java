package model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author chenzw
 * @date 2022/8/7
 */
public class HighPrecisionCalculate {
    // c=a+b ,a>=0,b>=0
    public List<Integer> add(List<Integer> a, List<Integer> b){
        if(a.size()<b.size())return add(b, a);
        LinkedList<Integer> c = new LinkedList<>();
        int t = 0;
        for(int i=0;i<a.size();i++){
            t += a.get(i);
            if(i<b.size()){
                t += b.get(i);
            }
            c.push(t%10);
            t /= 10;
        }
        if(t>0){
            c.push(t);
        }
        return c;
    }

    // C = A - B, 满足A >= B, A >= 0, B >= 0
    public List<Integer> sub(List<Integer> a, List<Integer> b){
        LinkedList<Integer> c = new LinkedList<>();
        for(int i=0,t=0; i<a.size();i++){
            t = a.get(i) - t;
            if(i<b.size()){
                t -= b.get(i);
            }
            c.push((t+10)%10);
            if(t<0){
                t=1;
            }else{
                t=0;
            }
        }
        while(c.size()>1&&c.peek()==0){
            c.pop();
        }
        return c;
    }

    // C = A * b, A >= 0, b >= 0
    public List<Integer> mul(List<Integer> a, int b){
        LinkedList<Integer> c = new LinkedList<>();
        int t = 0;
        for(int i=0;i<a.size()||t>0;i++){
            if(i<a.size()){
                t += a.get(i) * b;
            }
            c.push(t%10);
            t /= 10;
        }
        while(c.size()>1&&c.peek()==0){
            c.pop();
        }
        return c;
    }
    // A / b = C ... r, A >= 0, b > 0
    public List<Integer> div(){
        return null;
    }
 }
