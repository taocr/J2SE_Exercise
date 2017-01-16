package HighConcurrentProgramDesign.Chapter7.ProducerConsumerPattern.DisruptorExercise;

import HighConcurrentProgramDesign.Chapter7.ProducerConsumerPattern.DisruptorExercise.PCData;
import com.lmax.disruptor.EventFactory;

/**
 * Created by Taocr on 2017/1/15.
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {//PCDataFactory即数据工厂，因为Disruptor内部实际上是一个数组，所以每次实例化一个Disruptor的时候数组就已经分配好，这里就是用来定义具体每个数组中的PCData元素如何生成
        return new PCData();
    }
}
