package IOStream.Properties.Exerice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 此为MyGetExtensionFilePath的更优化版本
 * Created by Taocr on 2016/11/4.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        File aimDir = new File("E:\\java_source\\javaStudyExercise");

        ArrayList<File> fileList = new ArrayList<File>();

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("java");
            }
        };

        getFiles(aimDir, filter, fileList);

        File aimFile = new File("pathOfJava");

        write2File(fileList, aimFile);
    }



    public static void getFiles(File dir, FilenameFilter filter, List<File> list){
        File[] files = dir.listFiles();

        for (File file : files) {
            if(file.isDirectory())
                getFiles(file, filter, list);
            else if(filter.accept(dir, file.getName()))
                list.add(file);
        }
    }

    public static void write2File(List<File> list, File destFile) throws IOException {
        BufferedWriter bufw = new BufferedWriter(new FileWriter(destFile));

        for (File file : list) {
                bufw.write(file.getAbsolutePath());
                bufw.newLine();
                bufw.flush();
        }

        bufw.close();
    }
}
