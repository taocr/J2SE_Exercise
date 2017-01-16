package HighConcurrentProgramDesign.Chapter7.ProducerConsumerPattern.DisruptorExercise;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by Taocr on 2017/1/15.
 */
public class Consumer implements WorkHandler<PCData> {
    @Override
    public void onEvent(PCData pcData) throws Exception {   //onEvent即为已定义好的方法，为消费者处理数据的方法，这里需要实现它
        System.out.println(Thread.currentThread().getId() + ":Event:--" + pcData.get() * pcData.get() + "--");
    }
}
