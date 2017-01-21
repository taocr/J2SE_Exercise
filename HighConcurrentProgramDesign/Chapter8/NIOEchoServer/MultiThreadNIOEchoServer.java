package HighConcurrentProgramDesign.Chapter8.NIOEchoServer;

import sun.plugin2.main.client.DisconnectedExecutionContext;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Taocr on 2017/1/16.
 */
public class MultiThreadNIOEchoServer {
    private Selector selector;  //用于处理所有的网络连接
    private ExecutorService tp = Executors.newCachedThreadPool();   //用于对每一个客户端进行相应的处理，每一个请求都会委托给线程池中的线程进行实际处理

    public static Map<Socket, Long> time_stat = new HashMap<Socket, Long>(10240);   //定义一个与时间统计有关的类，用来统计服务器线程在一个客户端上花了多少时间

    private void startServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();  //通过工厂方法获得一个Selector的对象实例
        ServerSocketChannel ssc = ServerSocketChannel.open();   //获得表示服务器端的SocketChannel实例
        ssc.configureBlocking(false);   //将这个SocketChannel设置为非阻塞模式，在这种模式下可以向Channel注册感兴趣的时间，在数据准备好以后，得到必要的通知

        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), 8000);
        ssc.socket().bind(isa);

        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);    //将SocketServerChannel绑定到Selector上，注册其感兴趣的事件为Accept，从而让Selector为这个Channel服务
        //返回值是一个SelectionKey，是一个Selector和Channel的关系，当Channel注册到Selector上时，这个就是确立了两者服务关系的契约

        for (;;) {
            selector.select();  //阻塞的方法，当出现所有监控的Channel数据都没有准备好时就会阻塞，返回值为准备就绪的SelectionKey的数量
            Set readyKeys = selector.selectedKeys();    //因为准备就绪的Channel可能为多个，所以这里获取一个集合
            Iterator i = readyKeys.iterator();  //用迭代器进行集合的遍历
            long e = 0;
            while (i.hasNext()) {   //每次循环都会获取一个已经准备好的SelectionKey，但是具体是什么准备好了不知道
                SelectionKey sk = (SelectionKey)i.next();   //获取迭代器的当前实例
                i.remove(); //将此实例移除，否则就会重复处理相同的SelectionKey

                if (sk.isAcceptable()) {    //检查当前SelectionKey所代表的Channel是否处于Acceptable状态
                    doAccept(sk);
                } else if (sk.isValid() && sk.isReadable()) {   //判断Channel是否可读
                    if(!time_stat.containsKey(((SocketChannel)sk.channel()).socket()))  //记录时间戳
                        time_stat.put(((SocketChannel)sk.channel()).socket(), System.currentTimeMillis());
                    doRead(sk);
                } else if (sk.isValid() && sk.isWritable()) {   //判断Channel是否可写
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend:" + (e - b) + "ms");  //在写入完成后，根据读取前的时间戳信息，输出处理这个Socket连接的耗时
                }

            }
        }
    }

    private void doWrite(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        EchoClient echoClient = (EchoClient)sk.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb = outq.getLast();
        try {
            int len = channel.write(bb);
            if (len == -1) {
                //disconnect(sk);
                return;
            }
            if (bb.remaining() == 0) {
                outq.removeLast();  //buffer全部被写入了，移除掉这个buffer
            }
        } catch (IOException e) {
            System.out.println("Failed to write to client.");
            e.printStackTrace();
            //disconnect(sk);
        }

        if (outq.size() == 0) {
            sk.interestOps(SelectionKey.OP_READ);   //outq大小为0，证明要写入的数据没有了，因此将写兴趣删除，只留读兴趣。
        }
    }

    private void doRead(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;

        try{
            len = channel.read(bb);
            if (len < 0) {
                //disconnect(sk);
                return;
            }
        } catch (IOException e) {
            System.out.println("Failed to read from client.");
            e.printStackTrace();
            //disconnect(sk);
            return;
        }

        bb.flip();
        tp.execute(new HandleMsg(sk, bb));
    }

    private void doAccept(SelectionKey sk) {    //每当有一个连接产生即可以调用accept，就会调用doAccept，将具体的server所在的Channel取出，然后用此server的Channel的accept获取客户端具体连接的Channel，
        // 从而将其登记在selector上，此时登记的兴趣事件即为Read，之后当可以读时就会在服务器的流程中检测到从而跳转至doRead的方法执行

        ServerSocketChannel server = (ServerSocketChannel) sk.channel();    //获取与此SelectionKey有关联的Channel
        SocketChannel clientChannel;

        try {
            clientChannel = server.accept();    //通过accept获取新建的那个与客户端连接的channel
            clientChannel.configureBlocking(false); //将此Channel配置为非阻塞模式，即系统准备好IO后，在通知线程读取or写入

            SelectionKey clientKey = clientChannel.register(selector,SelectionKey.OP_READ); //将当前这个Channel登记在selectcor上，关心其是否可读，当可读时会对线程产生通知

            EchoClient echoClient = new EchoClient();   //对象实例，一个EchoClient代表一个客户端
            clientKey.attach(echoClient);   //将客户端实例作为附件附加到SelectionKey上，于是整个连接的处理中，都可以共享这个实例

            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted connection from" + clientAddress.getHostAddress() + ".");

        } catch (IOException e) {
            System.out.println("Failed to accept new client");
            e.printStackTrace();
        }
    }
}
