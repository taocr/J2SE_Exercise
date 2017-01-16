package HighConcurrentProgramDesign.Chapter4;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Taocr on 2016/12/25.
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static class Candidate{
        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater
            = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");//前者表示要操作的类，后者表示要操作的具体的域

    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread() {
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(stu);//通过scoreUpdater来修改Candidate中的stu值
                        allScore.incrementAndGet();
                    }
                }
            };
        }
        for (int i = 0; i < 10000; i++) {
            t[i].join();
        }
        System.out.println("score="+stu.score);
        System.out.println("allScore"+allScore);
    }
}
