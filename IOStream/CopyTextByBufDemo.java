package IOStream;

import java.io.*;

/**
 * Created by Taocr on 2016/10/26.
 */
public class CopyTextByBufDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufr = new BufferedReader(new FileReader("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo.txt"));

        BufferedWriter bufw = new BufferedWriter(new FileWriter("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo_copy.txt"));

//        int ch  = 0;
//
//        while((ch = bufr.read()) != -1)
//            bufw.write(ch);


        String str = null;
        while((str = bufr.readLine()) != null) {//按照行读的方式进行拷贝
            bufw.write(str);
            bufw.newLine();
            bufw.flush();//养成好的习惯，只要用到缓冲区就记得要刷新
        }


        bufw.close();
        bufr.close();

    }
}
