package HighConcurrentProgramDesign.Chapter8.NIOEchoServer;

import java.nio.ByteBuffer;
import java.util.LinkedList;

/**
 * Created by Taocr on 2017/1/17.
 */
public class EchoClient {   //封装一个队列，保存需要回复给客户端的所有信息，于是再进行回复的时候，只要从outq对象中弹出元素即可
    private LinkedList<ByteBuffer> outq;
    EchoClient() {
        outq = new LinkedList<ByteBuffer>();
    }

    public LinkedList<ByteBuffer> getOutputQueue() {
        return outq;
    }

    public void enqueue(ByteBuffer bb) {
        outq.addFirst(bb);
    }
}
