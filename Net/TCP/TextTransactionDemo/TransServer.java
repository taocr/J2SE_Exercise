package Net.TCP.TextTransactionDemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 思路：
 * 1、创建ServerSocket
 * 2、获取Socket对象
 * 3、读取数据并进行转换，需要匹配"over"，源：socket
 * 4、目的：显示在控制台上
 * 5、将数据征程大写发给客户端
 * Created by Taocr on 2016/12/8.
 */
public class TransServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ssock = new ServerSocket(10010);
        boolean close = false;

        while(true) {
            Socket sock = ssock.accept();
            new Thread(new UpperTrans(sock)).start();
        }
    }
}
