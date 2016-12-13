package Net.UDP.UdpChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;

/**
 * 思路：
 * 每次开始聊天，输入两个参数，第一个为对方的接收端口，第二个为自己的接收端口，指定后第一个参数代入Send类中构建对象，
 * 另一个则代入Receive类中构建对象，之后开启两个线程分别实现发送与接收的功能
 *
 * 关于最后的关闭，可以实现半关闭，当对方发送"over"后，对方的发送线程会关闭，而本端的接收线程也会关闭。因此当对方关闭聊天后，
 * 本方还是能够发送消息，对方也能接收到
 * * Created by Taocr on 2016/12/7.
 */
public class ChatCreate {
    public static void main(String[] args) throws IOException {
        String oppositePort = new BufferedReader(new InputStreamReader(System.in)).readLine();
        String receivePort = new BufferedReader(new InputStreamReader(System.in)).readLine();

        Send s = new Send(new DatagramSocket(), Integer.parseInt(oppositePort));
        Receive re = new Receive(new DatagramSocket(Integer.parseInt(receivePort)));

        new Thread(s).start();
        new Thread(re).start();
    }
}
