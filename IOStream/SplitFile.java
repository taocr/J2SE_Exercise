package IOStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 文件切割器，按照要求将一个大文件切割为多个碎片文件
 *
 * 思路：
 * 1、按照什么方式切？按照文件大小切or按照文件个数切
 * Created by Taocr on 2016/11/5.
 */
public class SplitFile {
    private static final int SIZE = 1048576;

    public static void mian(String[] args) throws IOException{
        File file = new File("");
        splitFile(file, "", "");
    }

    public static void splitFile(File file, String aimDir, String name) throws IOException {
        FileInputStream fis = new FileInputStream(file);

        //定义1M的缓冲区
        byte[] buf = new byte[SIZE];

        //创建目的
        FileOutputStream fos = null;
        int len = 0;
        int count = 0;

        File dir = new File(aimDir);
        if (!dir.exists())
            dir.mkdir();

        while ((len = fis.read(buf)) != -1) {
            fos = new FileOutputStream(name + "_" + (count++) + ".part");
            fos.write(buf);
        }

        fos.close();
        fis.close();
    }
}
