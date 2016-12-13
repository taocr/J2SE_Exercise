package IOStream;

import java.io.*;

/**
 * Created by Taocr on 2016/10/29.
 */
public class CopyMediaFileDemo {
    public static void main(String[] args) throws IOException {
        copy1();//常规拷贝方式
        copy2();//使用已有缓冲区进行拷贝
        copy3();//建立刚刚好大小的缓冲区然后一次性读入，之后一次性写出。如果文件过大，则数组创建就存在了问题
//        copy4();//读一个字节写一个字节，这种方式很慢，此种方式不用
    }

    private static void copy4() throws IOException {
        FileInputStream fis = new FileInputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\picture.jpg");

        FileOutputStream fos = new FileOutputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\picture1.jpg");

        int ch = 0;

        while((ch = fis.read())!= -1)
            fos.write(ch);

        fos.close();
        fis.close();
    }

    private static void copy3() throws IOException {
        FileInputStream fis = new FileInputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\picture.jpg");

        FileOutputStream fos = new FileOutputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\picture3.jpg");

        byte[] buf = new byte[fis.available()];

        fis.read(buf);
        fos.write(buf, 0 ,buf.length);

        fos.close();
        fis.close();
    }

    private static void copy2() throws IOException {
        BufferedInputStream bufis = new BufferedInputStream(new FileInputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\picture.jpg"));
        BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\picture2.jpg"));

        int ch = 0;

        while((ch = bufis.read())!= -1) {
            bufos.write(ch);
//            bufos.flush();//使用刷新的话，每次写入一个字节都需要刷，会很慢
        }

        bufos.close();
        bufis.close();
    }

    private static void copy1() throws IOException {
        FileInputStream fis = new FileInputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\picture.jpg");

        FileOutputStream fos = new FileOutputStream("E:\\java_source\\javaStudyExercise\\src\\IOStream\\picture1.jpg");

        byte[] buf = new byte[1024];
        int len = 0;

        while((len = fis.read(buf))!= -1)
            fos.write(buf, 0, len);

        fos.close();
        fis.close();
    }
}
