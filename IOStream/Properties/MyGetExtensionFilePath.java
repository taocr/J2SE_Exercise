package IOStream.Properties;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 获取指定目录下，指定扩展名的文件（包含子目录中的）
 * 这些文件的绝对路径写入到一个文本文件中
 *
 * 简单来说，就是建立一个指定扩展名的文件的列表
 *
 * 思路：
 * 首先需要进行递归打开目录，首先打开文件的File对象，然后对其使用listFiles从而返回其下所有文件的File对象，再进行一个个判断
 * Created by Taocr on 2016/11/4.
 */
public class MyGetExtensionFilePath {
    private static final String OUT_FILE = "aimFilePath.out";

    public static void main(String[] args) throws IOException {
        getExtensionFilePath("E:\\java_source", "xml");
    }

    /**
     * 递归搜索指定目录下的所有文件，检测扩展名是否匹配，将所有匹配的文件的绝对路径输入到“aimFilePath.out”文件下
     *
     * 注意扩展名的形式为 “.xxx”
     *
     * 方法中使用了Properties进行存储及保存在磁盘上生成输出文件，但是存在问题，其中要保证键的唯一，所以如果目录下的文件名称一样的话就无法存储
     * 因此修改一下，将存储文件的键改为路径，而值则变为文件名
     * @param aimDir 目标目录名称
     * @param aimExtendsion 目标扩展名名称
     */
    public static void getExtensionFilePath(String aimDir, String aimExtendsion) throws IOException {
        File file = new File(aimDir);
        if (!file.exists())
            throw new RuntimeException("The aim directory is not exist");

        Properties pathList = new Properties();

        listAll(file, aimExtendsion,pathList);

        FileOutputStream fos = new FileOutputStream(OUT_FILE);

        pathList.store(fos, "filepath+filename");

        fos.close();
    }

    private static void listAll(File aimDir, String aimExtendsion, Properties pathList) {
        File[] files = aimDir.listFiles();

        for (File file : files) {
            if(file.isFile()){
                if(file.getName().endsWith(aimExtendsion))
                    pathList.setProperty(file.getAbsolutePath(), file.getName());
            } else if (file.isDirectory())
                listAll(file, aimExtendsion, pathList);
        }
    }


}
