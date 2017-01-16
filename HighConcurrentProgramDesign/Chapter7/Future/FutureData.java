package HighConcurrentProgramDesign.Chapter7.Future;

/**
 * Created by Taocr on 2017/1/14.
 */
public class FutureData implements Data {
    protected RealData realData = null; //FutureData内部有着RealData的实例，从而在从其取出信息的时候具体获取RealData中的信息
    protected boolean isReady = false;

    public synchronized  void setRealData(RealData realdata) {
        if (isReady) {
            return;
        }
        this.realData = realdata;
        isReady = true;
        notifyAll();
    }

    @Override
    public String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realData.result;
    }
}
