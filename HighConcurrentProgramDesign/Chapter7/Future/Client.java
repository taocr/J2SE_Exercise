package HighConcurrentProgramDesign.Chapter7.Future;

/**
 * Created by Taocr on 2017/1/14.
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread() {
            public void run() {
                RealData realdata = new RealData(queryStr);
                future.setRealData(realdata);
            }
        }.start();
        return future;
    }
}
