package HighConcurrentProgramDesign.Chapter9;

import javax.swing.plaf.metal.MetalIconFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Taocr on 2017/1/18.
 */
public class ThreadLocalDemo_Gc {
    static volatile ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>() {
        protected void finalize() {
            System.out.println(this.toString() + "is gc");
        }
    };

    static volatile CountDownLatch cd = new CountDownLatch(10000);
    public static class ParseDate implements Runnable {
        int i = 0;
        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            if (tl.get() == null) {
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
                    protected void finalize() {
                        System.out.println(this.toString() + "is gc");
                    }
                });
                System.out.println(Thread.currentThread().getId() + ":create SimpleDateFormat");
            }
            try {
                Date t = tl.get().parse("2015-03-29 19:29:" + i%60);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                cd.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            es.execute(new ParseDate(i));
        }
        cd.await();
        System.out.println("mission complete");
        tl = null;
        System.gc();
        System.out.println("first GC complete");
        tl = new ThreadLocal<SimpleDateFormat>();
        cd = new CountDownLatch(100000);
        for (int i = 0; i < 10000; i++) {
            es.execute(new ParseDate(i));
        }

        cd.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println("second GC complete");
    }
}
