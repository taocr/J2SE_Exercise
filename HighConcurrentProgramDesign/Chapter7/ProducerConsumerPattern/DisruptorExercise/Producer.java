package HighConcurrentProgramDesign.Chapter7.ProducerConsumerPattern.DisruptorExercise;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by Taocr on 2017/1/15.
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;    //Disruptor内部实现的一个数据结构

    public Producer(RingBuffer<PCData> ringBuffer) {    //放置缓冲区，让生产者知道往哪里放数据
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb) {   //放置数据的方法，将ByteBuffer中的数据提取出来，装载到环形缓冲区
        long sequence = ringBuffer.next();
        try {
            PCData event = ringBuffer.get(sequence);    //获取下一个sequence上的PCData
            event.set(bb.getLong(0));   //放置数据
        } finally {
            ringBuffer.publish(sequence);   //数据发布，只有发布了的数据才能够被看到
        }
    }
}
