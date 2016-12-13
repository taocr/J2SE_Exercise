package IOStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Taocr on 2016/10/25.
 */
public class CopyFileDemo2 {
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        FileReader fr = null;
        FileWriter fw = null;

        try {
            fr = new FileReader("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo.txt");
            fw = new FileWriter("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo_copy.txt");

            char[] buf = new char[BUFFER_SIZE];

            int len = 0;

            while((len = fr.read(buf))!=-1){
                fw.write(buf, 0, len);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();//如果IO流正在操作一个文件，操作完成后没有关闭流操作，此时会无法删除此文件
        } finally {
            if(fw != null)
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (fr != null)
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

    }
}
