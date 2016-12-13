package IOStream.TransStream;

import java.io.*;

/**
 * Created by Taocr on 2016/11/1.
 */
public class TransStreamDemo2 {
    public static void main(String[] args) throws IOException {
        writeText2();
    }

    private static void writeText2() throws FileNotFoundException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("gbk_2.txt"));
        //以上的OutputStreamWriter(new FileOutputStream)其实是转换流指定了本机默认码表的表现，而且这个转换流的子类对象，可以方便操作文本文件，这里也可以指定不同的码表
        //其实就等于FileWriter，是一样的效果
        //如果操作文本文件需要明确具体的编码，那么FileWriter就不行了，必须使用转换流
    }


    public static void writeText() throws IOException {
        FileWriter fw = new FileWriter("gbk_1.txt");
        fw.write("你好");
    }
}
