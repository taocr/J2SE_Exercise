package Net.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP服务端建立，服务端接收客户端发送过来的数据并打印在后台上
 * 思路：
 * 1、创建服务端ServerSocket对象，这个对象在监听一个端口。服务端必须对外提供一个端口，否则客户端无法连接
 * 2、从ServerSocket对象获取客户端连接过来的客户端对象
 * 3、从客户端对象获取输入，读取客户端发来的数据，并打印出来
 * 4、关闭资源，需要关两个，一个是客户端Socket，一个是服务端的ServerSocket
 * Created by Taocr on 2016/12/8.
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        //1、创建服务端ServerSocket对象
        ServerSocket ssock = new ServerSocket(10000);
        //2、获取连接过来的客户端对象
        Socket c1 = ssock.accept();
        //3、从客户端Socket对象获取输入并打印
        BufferedReader bufr = new BufferedReader(new InputStreamReader(c1.getInputStream()));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(c1.getOutputStream()));

        String line = bufr.readLine();
        System.out.println(c1.getInetAddress()+" : "+c1.getPort());
        System.out.println(line);

        write(bufw, "TCP传输通道建立");

//        4、关闭资源
        c1.close();
        ssock.close();
    }

    private static void write (BufferedWriter bufw, String line) throws IOException {
        bufw.write(line);
        bufw.newLine();
        bufw.flush();
    }
}
