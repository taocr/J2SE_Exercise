package IOStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Taocr on 2016/10/29.
 */
public class ByteStreamDemo {
    public static void main(String[] args) throws IOException {
        demo_write();
        demo_read();
    }

    private static void demo_read() throws IOException {
        //创建读取流对象，和指定文件关联
        FileInputStream fis = new FileInputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\bytedemo.txt");
        //一次读取一个字节
        int ch;
        while((ch = fis.read()) != -1)
            System.out.print((char)ch);


        fis.close();
    }

    private static void demo_write() throws IOException {
        //创建字节输出流对象，用于操作文件
        FileOutputStream fos = new FileOutputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\bytedemo.txt");

        //写数据
        fos.write("abcdefg".getBytes());

//        FileOutputStream的flush方法继承自OutputStream，而在OutputStream中的flush是个空方法，因此FileOutputStream中实际上不需要执行flush
//        fos.flush();

        fos.close();
    }
}
