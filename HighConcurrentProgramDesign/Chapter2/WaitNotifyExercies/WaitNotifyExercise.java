package HighConcurrentProgramDesign.Chapter2.WaitNotifyExercies;

import javax.xml.ws.soap.Addressing;

/**
 * 需求：
 * 使用 wait notify 实现一个队列，队列有2个方法，add 和 get 。add方法往队列中添加元素，get方法往队列中获得元素。队列必须
 * 是线程安全的。如果get执行时，队列为空，线程必须阻塞等待，直到有队列有数据。如果add时，队列已经满，则add线程要等待，直
 * 到队列有空闲空间。
 *
 * 使用LinkedList完成，定好容量大小，明天实现。
 * 参考
 * http://f.dataguru.cn/forum.php?mod=viewthread&tid=672565&highlight=%CA%B5%D5%BDJava%B8%DF%B2%A2%B7%A2%B3%CC%D0%F2%C9%E8%BC%C6
 * Created by Taocr on 2016/12/20.
 */
public class WaitNotifyExercise {
    private static  MyQueue myQueue = new MyQueue ();

    public static class Add implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                myQueue.add((int)(Math.random() * 100));
            }
        }
    }

    public static class Get implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                myQueue.get();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Add());
        Thread t2 = new Thread(new Add());
        Thread t3 = new Thread(new Get());
        Thread t4 = new Thread(new Get());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
