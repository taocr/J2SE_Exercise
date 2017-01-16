package HighConcurrentProgramDesign.Chapter2;

/**
 * Created by Taocr on 2016/12/24.
 */
public class SleepInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!!!");
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted When Sleep");
//                        Thread.currentThread().interrupt();//sleep因为interrupt而引起异常的时候，执行到catch的语句中时，
                        // 会将相应的Interrupt造成的线程的中断位清空，所以如果没有此句，就会产生线程发生InterruptedException
                        // 的时候只会输出“Interrupted When Sleep”的语句，而不会输出循环一开始判断是否中断而后会输出
                        // 的Interrupted语句，且线程一直处于循环中无法结束
                    }

                    Thread.yield();//谦让
                }
            }
        };

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
