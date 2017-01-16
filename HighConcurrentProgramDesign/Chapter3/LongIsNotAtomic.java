package HighConcurrentProgramDesign.Chapter3;

/**
 * Created by Taocr on 2016/12/21.
 */
public class LongIsNotAtomic {
    private static long buf = 0;

    public static class Add implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                buf = buf + 1;
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Add());
        Thread t2 = new Thread(new Add());
        Thread t3 = new Thread(new Add());
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(buf);
    }
}
