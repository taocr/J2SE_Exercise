package HighConcurrentProgramDesign.Chapter7.ProducerConsumerPattern;

/**
 * Created by Taocr on 2017/1/14.
 */
public final class PCData {
    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getData() {
        return intData;
    }

    @Override
    public String toString(){
        return "data:"+intData;
    }
}
