package HighConcurrentProgramDesign.Chapter3;

/**
 * Created by Taocr on 2016/12/24.
 */
public class NoVisibility {
    private static boolean /*volatile*/ ready;
    private  static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                System.out.println(number);
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread readerThread = new Thread(new ReaderThread());
            Thread.sleep(1000);

            number = 42;
            ready = true;
            Thread.sleep(100000);
        }
    }
}
