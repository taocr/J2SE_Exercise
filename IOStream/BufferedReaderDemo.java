package IOStream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Taocr on 2016/10/26.
 */
public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {

        BufferedReader bufr = new BufferedReader(new FileReader("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo.txt"));

        String str = null;
        while ((str = bufr.readLine())!= null)
            System.out.println(str);

        bufr.close();
//        Demo();
    }



    private static void Demo() {
        //BufferedReader bufr = null;
        FileReader fr = null;
        try {
            fr = new FileReader("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo.txt");
            int ch;
            while((ch = fr.read()) != -1) {//print碰到\r时
                char c = (char)ch;
                System.out.print(c);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
