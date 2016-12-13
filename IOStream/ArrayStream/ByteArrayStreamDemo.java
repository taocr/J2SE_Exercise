package IOStream.ArrayStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Taocr on 2016/11/7.
 */
public class ByteArrayStreamDemo {
    public static void main(String[] args) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream("abcdefg".getBytes());

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int ch = 0;
        while((ch = bis.read()) != -1){
            bos.write(ch);
        }
//        bis.close();不需要关闭，所以这两步不需要
//        bos.close();
        System.out.println(Arrays.toString(bos.toByteArray()));
    }

}
