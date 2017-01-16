package HighConcurrentProgramDesign.Chapter2;

/**
 * Created by Taocr on 2016/12/20.
 */
public class SynchronizedUse {
    static SynchronizedUse instance = new SynchronizedUse();
    static int i = 0;

    public static class Increase implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 100000; j++) {
                synchronized (instance) {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Increase());
        Thread t2 = new Thread(new Increase());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
