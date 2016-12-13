package IOStream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Taocr on 2016/10/27.
 */
public class MyBufferedReaderDemo {
    public static void main(String[] args) throws IOException {

        MyBufferedReader bufr = new MyBufferedReader(new FileReader("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo.txt"));

        String str = null;
        while ((str = bufr.myReadLine()) != null)
            System.out.println(str);

        bufr.myClose();
    }
}
