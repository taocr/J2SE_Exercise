package Net.TCP.TextTransactionDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Taocr on 2016/12/8.
 */
public class UpperTrans implements Runnable {
    private Socket sock;

    public UpperTrans (Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
//        System.out.println("TCP传输连接建立");
        try {
            BufferedReader bufr = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);
            String line = null;

            while((line = bufr.readLine()) != null) {
//                System.out.println("已读取客户端发来数据");
                if (line.equals("over"))
                    break;

                line = line.toUpperCase();
                pw.println(line);
//                System.out.println("已发送转换后数据至客户端");
            }
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
