package HighConcurrentProgramDesign.Chapter8.NIOEchoServer;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

/**
 * Created by Taocr on 2017/1/17.
 */
public class HandleMsg implements Runnable {
    SelectionKey sk;
    ByteBuffer bb;

    public HandleMsg(SelectionKey sk, ByteBuffer bb) {
        this.sk = sk;
        this.bb = bb;
    }

    @Override
    public void run() {
        EchoClient echoClient = (EchoClient) sk.attachment();
        echoClient.enqueue(bb);
        sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        //selector.wakeup();  //强迫selector立即返回
    }
}
