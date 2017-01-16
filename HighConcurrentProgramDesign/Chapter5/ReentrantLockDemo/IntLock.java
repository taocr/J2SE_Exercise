package HighConcurrentProgramDesign.Chapter5.ReentrantLockDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的等待锁时可中断的实验
 * Created by Taocr on 2016/12/29.
 */
public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;
    public static int value;
    public IntLock (int lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(1000);
                lock2.lockInterruptibly();
                value++;
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(1000);
                lock1.lockInterruptibly();
            }
            value++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread())
                lock1.unlock();
            if (lock2.isHeldByCurrentThread())
                lock2.unlock();
            System.out.println(Thread.currentThread().getId()+"：线程退出"+"..."+value);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new IntLock(1));
        Thread t2 = new Thread(new IntLock(2));
        t1.start();t2.start();
        Thread.sleep(2000);
        t2.interrupt();
    }
}
