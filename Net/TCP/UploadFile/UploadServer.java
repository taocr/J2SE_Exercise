package Net.TCP.UploadFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Taocr on 2016/12/8.
 */
public class UploadServer {
    public static void  main(String[] args) throws IOException {
        ServerSocket ssock = new ServerSocket(26666);
        Socket sock = ssock.accept();
        System.out.println("连接已建立");

        byte[] buffer = new byte[8192];
        BufferedInputStream bufIn = new BufferedInputStream(sock.getInputStream());
        BufferedReader bufr = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        String line = bufr.readLine();
        BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(line));

        while (bufIn.read(buffer) != -1) {
            bufOut.write(buffer);
            bufOut.flush();
        }

        System.out.println("文件已拷贝完毕");
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
        bufw.write("文件已拷贝完毕");
        bufw.newLine();
        bufw.flush();

        bufOut.close();
        sock.close();
        ssock.close();
    }
}
