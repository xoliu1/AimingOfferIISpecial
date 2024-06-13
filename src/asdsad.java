import java.util.*;

public class asdsad {
    static Object o1 = new Object();
    static Object o2 = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (o1){
                System.out.println("占有对象1");
                try {
                    Thread.sleep(1000);
                    synchronized (o2){
                        System.out.println("占有对象2");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (o2){
                System.out.println("占有对象2");

                    synchronized (o1){
                        System.out.println("占有对象1");
                    }

            }
        }).start();
    }

}


class Student {
    private int id;
    private int english;
    private int math;
    public Student(int id, int english, int math) {
        this.id = id;
        this.english = english;
        this.math = math;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEnglish() {
        return english;
    }
    public void setEnglish(int english) {
        this.english = english;
    }
    public int getMath() {
        return math;
    }
    public void setMath(int math) {
        this.math = math;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return id + "\t" + english + "\t" + math;
    }
}