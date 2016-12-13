package Net.TCP.ie_server;

import javafx.scene.input.InputMethodTextRun;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Taocr on 2016/12/12.
 */
public class MyBrowser {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("123.206.16.60", 8080);

        //模拟浏览器，给tomcat服务器端发送符合http协议的请求消息
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

        out.println("GET / HTTP/1.1");
        out.println("Host: 123.206.16.60:8080");
        out.println("Connection: keep-alive");
        out.println("Cache-Control: max-age=0");
        out.println("Upgrade-Insecure-Requests: 1");
        out.println("User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36");
        out.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        out.println("Accept-Encoding: gzip, deflate, sdch, br");
        out.println("Accept-Language: zh-CN,zh;q=0.8");

        InputStream in =sock.getInputStream();

        byte[] buf = new byte[1024];
        int len = in.read(buf);
        String line = new String(buf, 0, len);
        System.out.println(line);

        sock.close();
    }
}
