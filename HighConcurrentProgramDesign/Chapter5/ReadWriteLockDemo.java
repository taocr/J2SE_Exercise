package HighConcurrentProgramDesign.Chapter5;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的实验，使用的时候需要先定义读写锁，然后通过定义的读写锁分别获取读锁写锁、读与读不互斥，读与写、写与写都相互互斥
 * Created by Taocr on 2016/12/29.
 */
public class ReadWriteLockDemo {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void handleWrite(Lock lock, int index) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                    demo.handleRead(readLock);  //在handleRead中已经处理中断异常，这里可以不用处理了
//                    demo.handleRead(lock);
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                demo.handleWrite(writeLock, new Random().nextInt());
//                demo.handleWrite(lock, new Random().nextInt());
            }
        };

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }

        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
