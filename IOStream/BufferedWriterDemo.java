package IOStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Character.LINE_SEPARATOR;

/**
 * Created by Taocr on 2016/10/26.
 */
public class BufferedWriterDemo {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\java_source\\javaStudyExercise\\src\\IOStream\\demo.txt"));

        bw.write("abcdef");
        bw.write(LINE_SEPARATOR);//输出java.lang.Character.LINE_SEPARATOR中所记录的当前平台的换行符
        bw.newLine();//功能就是上面的那句输出一个换行符，不过它只有BufferedWriter有这个功能，而上面的那句具有通用性
        bw.write("abcdef");


        bw.flush();//将缓冲区刷入磁盘，不过在close时也会先调用一次flush
        bw.close();//关闭缓冲区的时候，流也会关闭，因此不需要专门再对流进行关闭
    }
}
