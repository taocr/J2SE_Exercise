package HighConcurrentProgramDesign.Chapter10;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by Taocr on 2017/1/19.
 */
public class AskThread implements Runnable {
    CompletableFuture<Integer> re = null;
    public AskThread(CompletableFuture<Integer> re) {
        this.re = re;
    }

    @Override
    public void run() {
        int myRe = 0;
        try {
            myRe = re.get() * re.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(myRe);
    }

    public static void main(String[] args) throws InterruptedException {
        final CompletableFuture<Integer> future = new CompletableFuture<Integer>();
        new Thread(new AskThread(future)).start();

        Thread.sleep(1000); //模拟长时间的计算过程，在计算完成后再进行future的完成工作，原本的Future是由线程立刻开始完成工作的，这里可以选择开始完成的时间

        future.complete(60); //设置future完成
    }
}
