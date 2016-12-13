package Net.UDP.UdpChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 思路：
 * 接收方的构造函数只需要接收构造号的DatagramSocket对象即可，因为端口是在构造DatagramSocket的时候就需要指定的
 *
 * 主循环中首先创建byte数组用来存放接收到的数据，其作为参数构造DatagramPacket对象，之后调用DatagramSocket的receive进行接收数据，这一
 * 步是阻塞式的，接收到具体数据才会返回，通过DatagramPacket对象的方法读取具体的发送端IP地址、端口以及发送来的具体信息，按照一定格式显示
 * 出来。
 * 每次接收到的信息部分需要判断是否为"over"，因为接收到over代表对方要结束发送功能，因此可以退出接收的主循环
 *
 * 退出主循环后，对接收部分的DatagramSocket对象进行回收
 * Created by Taocr on 2016/12/7.
 */
public class Receive implements Runnable {
    private DatagramSocket ds;

    public Receive (DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {

        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);

            try {
                ds.receive(dp);

                String ip = dp.getAddress().getHostAddress();
                int port = dp.getPort();
                String line = new String(dp.getData(), 0, dp.getLength());

                System.out.println(ip+" : "+port);
                System.out.println("    "+line);

                if (line.equals("over")) {
                    System.out.println("对方退出聊天");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ds.close();
    }
}
