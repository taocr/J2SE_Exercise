package Net.TCP.URLDemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Taocr on 2016/12/12.
 */
public class URLDemo {
    public static void main(String[] args) throws IOException {
        String line = "http://127.0.0.1:8080/myweb/1.html?name=Taocr";
        URL url = new URL(line);

        System.out.println("getProtocol:"+url.getProtocol());
        System.out.println("getHost:"+url.getHost());
        System.out.println("getPort:"+url.getPort());
        System.out.println("getFile:"+url.getFile());
        System.out.println("getPath:"+url.getPath());
        System.out.println("getQuery:"+url.getQuery());

//        InputStream in = url.openStream();
        //获取url对象的Url连接器对象。将连接封装成了对象：java中内置的可以解析的具体协议的对象+socket
        URLConnection conn = url.openConnection();
        System.out.println(conn);   //sun.net.www.protocol.http.HttpURLConnection:http://127.0.0.1:8080/myweb/1.html
                                    //前面的包没有看过，这是一种底层实现，url解析字符串找到了http于是去找http的具体实现，这个包就是http的底层实现，这个包没有对外提供，这个对象就可以解析服务器发回的http应答头，
        //之前自己写socket能获取了服务器返回的http的所有信息，而这个URLConnection在此基础上将进行一次解析，将http头去掉了，只剩下体的部分

        InputStream in = conn.getInputStream();

        byte[] buf = new byte[1024];
        int len = in.read(buf);

        String str = new String(buf, 0, len);;
        System.out.println(str);

        in.close();
    }
}
