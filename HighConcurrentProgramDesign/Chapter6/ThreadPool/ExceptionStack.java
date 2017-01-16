package HighConcurrentProgramDesign.Chapter6.ThreadPool;

import java.util.concurrent.*;

/**
 * Created by Taocr on 2017/1/9.
 */
public class ExceptionStack {
    public static class DivTask implements Runnable {
        int a, b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double re = a/b;
            System.out.println(re);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        for (int i = 0; i < 5; i++) {
            pools.execute(new DivTask(100, i));
//            re.get();
        }
    }
}
