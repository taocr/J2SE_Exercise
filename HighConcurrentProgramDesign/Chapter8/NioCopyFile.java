package HighConcurrentProgramDesign.Chapter8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Taocr on 2017/1/16.
 */
public class NioCopyFile {
    public static void nioCopyFile(String resource, String destination) throws IOException {
        FileInputStream fis = new FileInputStream(resource);
        FileOutputStream fos = new FileOutputStream(destination);
        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer);
            if (len == -1) {
                System.out.println("File read complete");
                break;
            }
            buffer.flip();  //重置position
            writeChannel.write(buffer);
        }

        readChannel.close();
        writeChannel.close();
    }
}
