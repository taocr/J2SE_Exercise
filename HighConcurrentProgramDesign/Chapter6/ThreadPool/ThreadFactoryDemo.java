package HighConcurrentProgramDesign.Chapter6.ThreadPool;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Taocr on 2017/1/9.
 */
public class ThreadFactoryDemo implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        System.out.println("create" + t);
        return t;
    }
}
