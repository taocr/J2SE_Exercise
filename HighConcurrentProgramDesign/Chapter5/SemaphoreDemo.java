package HighConcurrentProgramDesign.Chapter5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 共享锁，设置多个许可，每一个许可允许一个线程进入临界区
 * Created by Taocr on 2016/12/29.
 */
public class SemaphoreDemo implements Runnable{
    final Semaphore semaphore = new Semaphore(5);//5个许可
    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done!");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);//创建一个线程池
        final SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }
}
