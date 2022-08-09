package common;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @author chenzw
 * @date 2022/8/7
 */
public class IOProcessor {
    BufferedReader bf;
    StringTokenizer st;
    BufferedWriter bw;

    public IOProcessor() {
        bf = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer("");
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public String nextLine() throws IOException {
        return bf.readLine();
    }

    public String next() throws IOException {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(bf.readLine());
        }
        return st.nextToken();
    }

    public char nextChar() throws IOException {
        return next().charAt(0);
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public float nextFloat() throws IOException {
        return Float.parseFloat(next());
    }

    public byte nextByte() throws IOException {
        return Byte.parseByte(next());
    }

    public short nextShort() throws IOException {
        return Short.parseShort(next());
    }

    public BigInteger nextBigInteger() throws IOException {
        return new BigInteger(next());
    }

    public void println(int a) throws IOException {
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }

    public void print(int a) throws IOException {
        bw.write(String.valueOf(a));
        return;
    }

    public void println(String a) throws IOException {
        bw.write(a);
        bw.newLine();
        return;
    }

    public void print(String a) throws IOException {
        bw.write(a);
        return;
    }

    public void println(long a) throws IOException {
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }

    public void print(long a) throws IOException {
        bw.write(String.valueOf(a));
        return;
    }

    public void println(double a) throws IOException {
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }

    public void print(double a) throws IOException {
        bw.write(String.valueOf(a));
        return;
    }

    public void flush() throws IOException {
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        IOProcessor p = new IOProcessor();
        int n = p.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i] = p.nextInt();
        }
        int maxCnt = 0;
        List<Integer> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i:a){
            set.add(i);
        }
        for(int i:a){
            if(maxCnt == 3){
                break;
            }
            for(int j=0;j<31;j++){
                int cnt = 1;
                int b = 1<<j;
                if(set.contains(i-b)){
                    cnt++;
                }
                if(set.contains(i+b)){
                    cnt++;
                }
                if(cnt>maxCnt){
                    maxCnt = cnt;
                    ans = new ArrayList<>();
                    if(set.contains(i-b)){
                        ans.add(i-b);
                    }
                    ans.add(i);
                    if(set.contains(i+b)){
                        ans.add(i+b);
                    }
                }
            }
        }

        p.println(maxCnt);
        for(int i:ans){
            p.print(i);
            p.print(" ");
        }
        p.flush();
    }

}
