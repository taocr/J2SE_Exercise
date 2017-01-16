package HighConcurrentProgramDesign.Chapter5.ReentrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个线程获取重入锁后，睡眠6秒，此时锁不释放，另一个线程通过tryLock的时间设置来尝试获取锁，因为超过了设定时间的5秒仍然没有获取到锁，所以一个线程会输出“get lock failed”的错误
 * Created by Taocr on 2016/12/29.
 */
public class TimeLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println("get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        TimeLock tl = new TimeLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();t2.start();
    }
}
