package IOStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Created by Taocr on 2016/10/29.
 */
public class LineNumberReaderDemo {


    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo.txt");
        LineNumberReader lnr = new LineNumberReader(fr);

        String line = null;
        lnr.setLineNumber(3);
        while((line = lnr.readLine()) != null) {
            System.out.println(lnr.getLineNumber()+":"+line);
        }


        lnr.close();
    }
}
