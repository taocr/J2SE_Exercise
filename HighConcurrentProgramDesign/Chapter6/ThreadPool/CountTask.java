package HighConcurrentProgramDesign.Chapter6.ThreadPool;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Taocr on 2017/1/9.
 */
public class CountTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start , long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD; //查看中间的值是否小于10000，小于则可以作为一个小任务的计算量
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //将整个大任务分为100个小任务
            long step = (start + end)/100;  //计算每次小任务要计算的步数
            ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
            long pos = start;
            for (int i = 0; i < 100; i++) { //分成100个小任务
                long lastOne = pos + step;  //计算当前这个小任务最后要从什么多少偏移量计算到多少偏移量
                if (lastOne > end)  //当出现要计算的偏移量结束位置>整个数的结束位置时，将结束位置进行修正
                    lastOne = end;
                CountTask subTask = new CountTask(pos, lastOne);    //新建一个小任务
                pos += step + 1;
                subTasks.add(subTask); //将这个小任务放入到ArrayList中方便整合
                subTask.fork(); //子任务推向线程池
            }
            for (CountTask t : subTasks) {
                sum += t.join();    //等待子任务结束
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(); //定义一个ForkJoin线程池
        CountTask task = new CountTask(0, 20000L);    //生成一个大任务，即对一个数的递归算阶乘
        ForkJoinTask<Long> result = forkJoinPool.submit(task);  //将大任务翻入到线程池中去，返回的是一个ForkJoinTask任务，即大任务的实例
        long res = 0;
        try {
            res = result.get(); //获取运算结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("sum="+res);
    }
}
