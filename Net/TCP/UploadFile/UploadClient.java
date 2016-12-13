package Net.TCP.UploadFile;

import java.io.*;
import java.net.Socket;

/**
 * Created by Taocr on 2016/12/8.
 */
public class UploadClient {
    public static void main(String[] args) throws IOException {
        BufferedReader keyInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("请输入目标IP地址");
        String line = keyInput.readLine();
        Socket sock = new Socket(line, 26666);
        System.out.println("连接已建立");

        System.out.println("请输入目标文件名（绝对路径）");
        line = keyInput.readLine();
        BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(line));
        if (bufIn != null)
            System.out.println("文件已找到");


        byte[] buffer = new byte[8192];
        BufferedOutputStream bufOut = new BufferedOutputStream(sock.getOutputStream());
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
        String[] splitLine = line.split("\\\\");//Java转换成\\，正则表达式又转换成\

        System.out.println("请输入文件上传至目标主机的具体位置（绝对路径,凡是目录需要添加分隔符）");
        line = keyInput.readLine()+splitLine[splitLine.length - 1];
        System.out.println("拷贝至"+sock.getInetAddress()+"下的"+line+"文件");
        bufw.write(line);
        bufw.newLine();
        bufw.flush();

        while(bufIn.read(buffer) != -1) {
            bufOut.write(buffer);
            bufOut.flush();
        }

        sock.shutdownOutput();

        line = new BufferedReader(new InputStreamReader(sock.getInputStream())).readLine();
        System.out.println(line);

        bufIn.close();
        sock.close();
    }
}
