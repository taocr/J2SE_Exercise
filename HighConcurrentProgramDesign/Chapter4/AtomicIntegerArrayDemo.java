package HighConcurrentProgramDesign.Chapter4;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by Taocr on 2016/12/25.
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray array = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                array.getAndIncrement(i % array.length());//将第i%arr.length()下标的元素+1，即每个线程对数组中的元素进行1000次遍历加1的操作，一个线程完成的结果会是整个数组每个元素值为1000，而10个线程完成的结果对于线程安全的情况下就是每个元素值为10000
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new Thread(new AddThread());
        }
        for (int i = 0; i < 10; i++) {
            ts[i].start();
        }
        for (int i = 0; i < 10; i++) {
            ts[i].join();
        }
        System.out.println(array);
    }
}
