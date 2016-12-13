package IOStream.File;

import java.io.File;
/**
 * 深度遍历文件夹，即显示指定文件夹下的所有文件名，并且如果存在多级目录则全部遍历
 * Created by Taocr on 2016/11/2.
 */
public class DeepErgodic {
    public static void main(String[] args){
        File dir = new File("E:\\java_source\\javaStudyExercise");

        listAll(dir,0);
    }

    private static void listAll(File dir, int level) {
        level++;
        //获取指定目录下所有指定目录下当前的所有文件夹or文件对象
        File[] files = dir.listFiles();

        //遍历当前这个目录
        for (int i = 0; i < files.length; i++) {
            if(files[i].isFile())
                System.out.println(files[i]);
            else if(files[i].isDirectory())
                listAll(files[i], level);
        }
    }
}
