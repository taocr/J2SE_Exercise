package Net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 建立Udp传输的接收端
 *
 * 思路：
 * 1、创建udp socket服务，
 * 2、创建数据包，用于存储接受到的数据，方便用数据包对象的方法对数据进行解析
 * 3、使用socket服务的receive方法，将接收到的数据存储到数据包中
 * 4、通过数据包的方法解析数据包中的数据
 * 5、关闭资源
 * Created by Taocr on 2016/12/7.
 */
public class ChatReceiveDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("接收端启动......");

        //1、建立udp socket服务
        DatagramSocket ds = new DatagramSocket(10000);

        //2、创建数据包
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);

        //3、使用接收方法将数据存储到数据包
        while (true) {
            ds.receive(dp);//阻塞式

            //4、通过数据包对象的方法解析数据
            String ip = dp.getAddress().getHostAddress();
            int port = dp.getPort();//此处端口是指发送端的端口，因为发送端创建时候没有指定发送端口是多少，所以随机指定即可
            String data = new String(dp.getData(), 0, dp.getLength());

            System.out.println(ip + " : " + port + " : " + data);
        }
    }
}
