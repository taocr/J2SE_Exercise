package HighConcurrentProgramDesign.Chapter7.Future.JDKFuture;

import java.util.concurrent.Callable;

/**
 * Created by Taocr on 2017/1/14.
 */
public class RealData implements Callable<String> {
    private String para;
    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
            }
        }
        return sb.toString();
    }
}
