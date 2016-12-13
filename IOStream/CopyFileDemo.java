package IOStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Taocr on 2016/10/25.
 * 需求：将C盘的一个文本文件复制到D盘
 *
 * 思路：
 * 1、需要读取源；
 * 2、将读到的元数据写入到目的地
 * 3、既然是操作文本数据，使用字符流
 */
public class CopyFileDemo {
    public static void main(String[] args) throws IOException {
        //1、读取一个已有的文本文件，使用字符读取流和文件相关联
        FileReader fr = new FileReader("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo.txt");

        //2、创建一个目的，用于存储读到的数据
        FileWriter fw = new FileWriter("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo_copy.txt");

        //3、频繁的读写操作
        int ch = 0;
        while((ch = fr.read())!= -1){
            fw.write((char)ch);
        }

        //4、关闭流资源
        fr.close();
        fw.close();//一开始没有关闭写的流，因此数据写入到缓存中没有刷新，导致没有数据在生成的文件中
    }
}
