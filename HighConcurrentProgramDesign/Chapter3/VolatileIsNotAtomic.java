package HighConcurrentProgramDesign.Chapter3;

/**
 * Created by Taocr on 2016/12/24.
 */
public class VolatileIsNotAtomic {
    static volatile int i = 0;
    public static class PlusTask implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new PlusTask());
            threads[j].start();
        }

        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }

        System.out.println(i);

    }
}
