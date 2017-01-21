package HighConcurrentProgramDesign.Chapter9;

/**
 * Created by Taocr on 2017/1/18.
 */
public class IntegerLock {
    static Integer i = 0;
    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (int j = 0; j < 100000; j++) {
                synchronized (i) {
                    i++;
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        AddThread t1 = new AddThread();
        AddThread t2 = new AddThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
