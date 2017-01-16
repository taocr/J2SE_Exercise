package HighConcurrentProgramDesign.Chapter2;

/**
 * Created by Taocr on 2016/12/20.
 */
public class SimpleWN {
    final static Object object = new Object();
    public static class T1 extends Thread {
        public void run(){
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start");
                try {
                    System.out.println(System.currentTimeMillis() + "T1 wait for object");
                    object.wait();  //执行此步时需要首先拿到当前此对象的锁，如果此句移到synchronized语句之外会出错
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "T1 end!");
            }
        }
    }

    public static class T2 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
