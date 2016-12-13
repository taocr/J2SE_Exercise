package Net.UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 创建Udp传输的发送端
 *
 * 思路：
 * 1、建立udp的socket服务，即socket端点
 * 2、将要发送的数据封装到数据包中
 * 3、通过udp的socket服务奖数据包发送出去
 * 4、关闭socket服务（因为发送需要调用资源，用完要关闭释放资源）
 *
 * Created by Taocr on 2016/12/7.
 */
public class ChatUdpSendDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("发送端启动......");

        //1、udpsocket服务，使用DatagramSocket对象
        DatagramSocket ds = new DatagramSocket(8888);

        //2、将要发送的数据封装到数据包中
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = bufr.readLine()) != null) {
            //使用DatagramPacket将数据进行封装
            byte[] buf = line.getBytes();
            DatagramPacket dp =
                    new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 10000);//将数据发回本端地址，端口为10000

            //3、通过udp的socket服务将数据包发送出去，使用send方法
            ds.send(dp);

            if ("over".equals(line))
                break;

        }
        //关闭资源
        ds.close();
    }
}
