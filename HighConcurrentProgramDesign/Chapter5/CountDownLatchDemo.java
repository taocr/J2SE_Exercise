package HighConcurrentProgramDesign.Chapter5;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Taocr on 2016/12/30.
 */
public class CountDownLatchDemo implements Runnable{
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10) * 10000);
            System.out.println("check complete");
            end.countDown();    //将CountDownLatch中的倒数计数-1
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }

        end.await();
        System.out.println("Fire!");
        exec.shutdown();
    }
}
