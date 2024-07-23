import java.util.*;

public class asdsad {
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + "获取lock1");
                Thread.yield();
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + "获取lock2");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + "获取lock2");
                    Thread.yield();
                    synchronized (lock1){
                        System.out.println(Thread.currentThread().getName() + "获取lock1");
                    }
                }
            }
        }).start();
    }
}

