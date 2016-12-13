package Net.TCP.ie_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Taocr on 2016/12/12.
 */
public class MyTomcat {
    public static void main(String[] args) throws IOException {
        ServerSocket ssock = new ServerSocket(8080);

        Socket sock  = ssock.accept();

        InputStream in = sock.getInputStream();

        byte[] buf = new byte[1024];

        int len = in.read(buf);
        String line = new String(buf, 0, len);
        System.out.println(line);

        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
        out.println("<font color='red' size='7'>欢迎光临</font>");

        sock.close();
        ssock.close();
    }
}
