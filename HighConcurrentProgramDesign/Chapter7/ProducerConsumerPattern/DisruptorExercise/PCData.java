package HighConcurrentProgramDesign.Chapter7.ProducerConsumerPattern.DisruptorExercise;

/**
 * Created by Taocr on 2017/1/15.
 */
public class PCData {
    private long value;

    public long get() {
        return value;
    }

    public void set(long value) {
        this.value = value;
    }
}
