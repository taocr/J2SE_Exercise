package Net.TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP传输，客户端建立过程，客户端发送数据到服务端
 * 1、创建tcp客户端socket服务，使用Soocket对象，且建议对象一创建就明确目的地，即要连接的服务端
 * (对于socket不带有任何参数的构造方法，可以使用connect(SocketAddress s)的方法进行与服务端的连接，前面的建议中的方法就不
 * 需要再传入这个SocketAddress地址了，SocketAddress是一个抽象类，主要实现其子类InetSocketAddress，这实际上就是IP地址与端
 * 口号的集合)
 *
 * 2、如果连接建立成功，说明数据传输通道已建立，即服务端与客户端之间建立了一个传输流，这是socket流，既可以读，也可以写
 * 是底层建立好的，说明这里既有输入又有输出，想要输入or输出流对象，可以找Socket来获取。
 * 因此Socket对象能够返回其的输入流(getOOutputStream)，也能返回其的输出流(getInputStream)，于是可以进行输入or输出的操作
 *
 * 3、使用输出流，将数据写出
 *
 * 4、关闭资源
 *
 * Created by Taocr on 2016/12/8.
 */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        //创建客户端服务
        Socket sock = new Socket(InetAddress.getLocalHost(), 10000);//指定服务端的IP地址与端口,直接进行连接

        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
        BufferedReader bufr = new BufferedReader(new InputStreamReader(sock.getInputStream()));

        write(bufw, "TCP传输通道建立");

        //读取服务端返回的数据
        String line = bufr.readLine();
        System.out.println(sock.getInetAddress()+" : "+sock.getPort());//这里的端口没有任何意义，因为数值是建立sock的时候指定的端口，且此因为服务器端监听socket的端口，而不是服务器端发送的端口
        System.out.println(line);

        sock.close();//如果sock资源被关闭，那么流不用关，因为关闭sock的时候会直接关闭所有的流
    }

    private static void write (BufferedWriter bufw, String line) throws IOException {
        bufw.write(line);
        bufw.newLine();
        bufw.flush();
    }
}
