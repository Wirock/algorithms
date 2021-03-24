package leetcode;

/**
 * 1114. 按序打印
 * 我们提供了一个类：
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 *
 * 一个将会调用 first() 方法
 * 一个将会调用 second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * @author chenzw
 * @date 2021/3/25
 */
public class Solution1114 {
    static class Foo {
        volatile  int count=0;
        public Foo() {

        }


        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            count++;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while(count!=1);
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            count++;
        }

        public void third(Runnable printThird) throws InterruptedException {
            while(count!=2);
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            count=0;
        }
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        Runnable first = new Runnable() {
            @Override
            public void run() {
                System.out.println("first");
            }
        };
        Runnable second = new Runnable() {
            @Override
            public void run() {
                System.out.println("second");
            }
        };
        Runnable third = new Runnable() {
            @Override
            public void run() {
                System.out.println("third");
            }
        };
        Thread t1 = new Thread(()->{
            try {
                foo.first(first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            try {
                foo.second(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(()->{
            try {
                foo.third(third);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(()->{
            try {
                foo.first(first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t5 = new Thread(()->{
            try {
                foo.second(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t6 = new Thread(()->{
            try {
                foo.third(third);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t3.start();
        t5.start();
        t6.start();
        t4.start();
    }
}
