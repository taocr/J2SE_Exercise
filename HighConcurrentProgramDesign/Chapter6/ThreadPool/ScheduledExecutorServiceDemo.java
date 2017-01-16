package HighConcurrentProgramDesign.Chapter6.ThreadPool;

import java.sql.Time;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Taocr on 2017/1/9.
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        //如果前面的任务没有完成，调度就不会启动，即如果一个任务的执行时间要比调度周期长的话，那么也不会出现两次调度执行的结果重合在一起的情况
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()/1000);
            }
        }, 0, 2, TimeUnit.SECONDS); //参数分别为：调度执行的任务， 第一次调度执行的推迟时间，之后每次调度执行的推迟时间， 时间单位
    }
}
