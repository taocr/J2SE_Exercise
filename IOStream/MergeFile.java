package IOStream;

import com.sun.scenario.effect.Offset;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

/**
 * 合并文件，将指定目录下的所有.part文件合并为一个指定文件名的文件
 * Created by Taocr on 2016/11/5.
 */
public class MergeFile {
    public static void main(String[] args) {

    }

    public static void mergeFile(File dir, String name) throws IOException {
        //首先将多个源用一个序列流进行封装
        ArrayList<FileInputStream> al = new ArrayList<FileInputStream>();
        File[] list = dir.listFiles();
        for (File currFile : list)
            if (currFile.getName().endsWith(".part"))
                al.add(new FileInputStream(currFile));

        //此时ArrayList中存在着所有需要合并的文件的输入流，由于序列流需要使用枚举类型，因此使用Collections来获取
        Enumeration<FileInputStream> en = Collections.enumeration(al);
        SequenceInputStream sis = new SequenceInputStream(en);

        //创建目标文件
        FileOutputStream fos = new FileOutputStream(name);

        byte[] b = new byte[1024];
        int len = 0;

        while((len = sis.read(b)) != -1)
            fos.write(b, 0, len);
    }

}
