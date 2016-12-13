package Net.TCP.TextTransactionDemo;

import java.io.*;
import java.net.Socket;

/**
 * 思路：
 * 1、先建立Socket对象
 * 2、客户端数据源：键盘，目的：socket
 * 3、接收服务端的数据，源：socket
 * 4、 将数据显示打印出来，目的：控制台
 * 5、在这些流中操作的数据都为文本数据
 * Created by Taocr on 2016/12/8.
 */
public class TransClient {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("127.0.0.1", 10010);
        System.out.println("TCP传输连接建立");

        BufferedReader keyInput = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader bufr = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);//用PrintWriter来进行字符流转字节流，后面的true是自动刷新功能

        String line= null;

        //每次输入完一句，即发送了一句以后就希望能收到一个数据，因此发送完成再进行收取，且收取过程放在循环中
        while((line = keyInput.readLine()) != null) {
            if ("over".equals(line))
                break;
//            System.out.println("已读取键盘输入");
            out.println(line);
//            System.out.println("已发送服务器端");
            //读取服务端发回的数据
            line = bufr.readLine();
//            System.out.println("已读取服务器端发来的转换后数据");
            System.out.println(line);
        }

        sock.close();
        //问题1：客户端执行了close()方法，就算服务器端没有验证over，也会结束，且这里当输入为over时，根本不会把over发送出去，因
        // 此实际上就是靠的close来关闭服务器端的相应线程，这是因为close()后，服务器端会读到一个null的数据，而服务器的循
        // 环的判断条件是读取的字符串不为null，因此结束了循环，从而让服务器端执行了Socket的关闭工作

        //问题2：如去调PrintWriter构造语句中的第二个true参数，且执行PrintWriter对象out的写方法时使用print，那么结果会变
        // 成输入一句话回车后，客户端没有返回大写数据且也不让输入任何数据了？
        //print会把所有的数据都写入了PrintWriter中去，但是并没有发送，因为没有进行刷新动作，因此进入了下面的阻塞读取服务
        // 器返回的内容的过程中，因此也就无法输入了，即成了一种服务器、客户端双方都在等待的情况，一般遇到这样的情况百分
        // 之百都是阻塞调用产生的问题
        // 但是就算使用了刷新动作，实际还是会出现问题，因为print没有输出后面的换行符，而BufferedReader因为没有读到换行符
        // 所以认为没有读完，因此服务器端的BufferedReader认为没有读完所以没有返回
    }
}
