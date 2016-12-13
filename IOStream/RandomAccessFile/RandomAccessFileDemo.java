package IOStream.RandomAccessFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Created by Taocr on 2016/11/7.
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException {
//        writeFile();
        readFile();
    }

    private static void readFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("randomAccess.txt","rw");

        byte[] buf = new byte[10];
        raf.read(buf);
        System.out.println(Arrays.toString(buf));
    }

    public static void writeFile() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("randomAccess.txt", "rw");


        raf.seek(5);
        raf.write("Taocr".getBytes());
        raf.writeInt(97);
        System.out.println(raf.getFilePointer());
        raf.close();
    }
}
