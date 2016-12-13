package Net.UDP.UdpChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 思路：
 * 构造函数中接收两个参数，一个为创建的udp socket，另一个为对方的接收端口
 * 开始运行后首先输出提示信息
 * 建立键盘监听，并用BufferedReader进行封装提升效率，并声明line来记录每次要发送的数据
 *
 * 进入主要循环的部分，定义一个byte数组用来将line的内容进行转化，因为DatagramPacket中只能接收byte[]的数据，将之前代入的参数代入
 * Datagram的构造函数中指明发送的目标端口，调用DatagramSocket的send方法进行发送。每次需要判断发送的数据是否为"over"，因为此时代表结束交谈
 * 即跳出主循环部分
 *
 * 最后只有跳出主循环部分才会执行这条语句，即调用DatagramSocket的close()方法关闭资源
 * Created by Taocr on 2016/12/7.
 */
public class Send implements Runnable {
    private DatagramSocket ds;

    private int receivePort;

    public Send(DatagramSocket ds, int port){
        this.ds = ds;
        receivePort = port;
    }

    @Override
    public void run() {//run方法中不能声明异常
        System.out.println("start chatting now");
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        try {
            while ((line = bufr.readLine()) != null) {
                byte[] buf = line.getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), receivePort);//对于要群聊的情况，可以往局域网的广播地址发送数据，就能实现群聊

                ds.send(sendPacket);

                if("over".equals(line))
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ds.close();
    }
}
