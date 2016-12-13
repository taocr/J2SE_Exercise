package IOStream.DataStream;


import java.io.*;

/**
 * Created by Taocr on 2016/11/7.
 */
public class DataStreamDemo {
    public static void main(String[] args) throws IOException {
        writeData();
        readData();

    }

    private static void readData() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));

        String s = dis.readUTF();

        System.out.println(s);
        dis.close();
    }

    private static void writeData() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));

        dos.writeUTF("你好");

        dos.close();
    }
}
