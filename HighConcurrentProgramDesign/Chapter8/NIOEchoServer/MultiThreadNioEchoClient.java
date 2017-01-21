package HighConcurrentProgramDesign.Chapter8.NIOEchoServer;

import IOStream.ReadKey;
import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

/**
 * Created by Taocr on 2017/1/17.
 */
public class MultiThreadNioEchoClient {
    private Selector selector;

    public void init(String ip , int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);   //创建一个SocketChannel实例并设置为非阻塞模式
        this.selector = SelectorProvider.provider().openSelector();     //实例化一个Selector用于IO复用
        channel.connect(new InetSocketAddress(ip, port));   //channel连接具体指定的ip地址：端口，但此时不一定就连接上了
        channel.register(selector, SelectionKey.OP_CONNECT);    //将此通道登记在selector，感兴趣的事件是connect
    }

    public void working() throws IOException {  //正式工作的客户端代码
        while (true) {
            if (!selector.isOpen()) //对于selector不处于开启状态，即我们后面关闭了selector的时候，整个客户端也就跳出了它具体运行的代码
                break;
            selector.select();  //开始监控
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();    //监控返回时代表有事件满足了，获取准备好的事件的集合的迭代器
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                ite.remove();   //remove一定要，因为selector不会自己将准备好的事件处理后就删去重新监控，如果不执行remove，下一次生成的时候无论SelectionKey是否就绪它都会存在于这个Set中
                if (key.isConnectable()) {  //根据SelectionKey具体情况进行判断执行哪种方法
                    connect(key);
                } else if (key.isReadable()) {
                    read(key);
                }
            }
        }
    }

    public void connect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel)key.channel();   //通过SelectionKey来获取具体对应的channel
        if (channel.isConnectionPending()) {    //确认当前channel是否真的连接上了
            channel.finishConnect();    //如未连接，那么调用finishConnect完成连接
        }
        channel.configureBlocking(false);   //设置这个channel为非阻塞状态，奇怪的是，应该在之前设置了，为什么这里还要设置
        channel.write(ByteBuffer.wrap(new String("hello server!\r\n").getBytes())); //将一个语句放入ByteBuffer后写入，即写想了服务器端
        channel.register(this.selector, SelectionKey.OP_READ);  //写入完成后就需要等待Server的回复，因此将此channel登记为对读感兴趣，此时已经连接上了就不需要再对connect感兴趣了
    }

    public void  read(SelectionKey sk) throws IOException {
        SocketChannel channel = (SocketChannel) sk.channel();   //获取具体的channel
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);   //直接读取对于读数据已经准备好的channel
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("客户端收到信息：" + msg);
        channel.close();    //读取完毕后设置客户端功能需求结束，因此将连接的channel关闭
        sk.selector().close();  //将使用的selector也关闭。
    }
}
