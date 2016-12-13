package IOStream.File;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

/**
 * Created by Taocr on 2016/11/2.
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
//        getMessageDemo();
//        createAndDeleteDemo();
//        renameDemo();
//        listRootDemo();
//        listDemo();
        listDemo2();
    }

    private static void listDemo2() {
        File dir = new File("src"+File.separator+"IOStream");
        SuffixFilter sf = new SuffixFilter(".java");//定义一个方便一些的filter类，根据输入的字符串进行匹配
        String[] list = dir.list(sf);

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }


    private static void listDemo() {
        /*需求：获取IOStream目录下所有.java文件的文件名*/
        //首先需要一个filter
        File dir = new File("src"+File.separator+"IOStream");
        FilterByJava fbj = new FilterByJava();//FilterByJava文件中定义的类，其实现了FilenameFilter的接口
        String[] list = dir.list(fbj);
        for(int i = 0; i < list.length; i++)
            System.out.println(list[i]);

//        获取IOStream下的所有文件及目录名称
//        File f = new File("src"+File.separator+"IOStream");
//        String[] list = f.list();//获取当前目录下的文件以及目录的名称，包含隐藏文件
//        for(int i = 0; i < list.length; i++)
//            System.out.println(list[i]);
    }

    private static void listRootDemo() {
        File[] files = File.listRoots();

        for(File file:files){
            System.out.println(file);
        }
    }

    private static void renameDemo() {
        File f1 = new File("c://test1");
        File f2 = new File("c://test2");

        boolean b = f1.renameTo(f2);
        System.out.println(b);

    }


    public static void getMessageDemo() {
        File f = new File("E:\\java_source\\javaStudyExercise\\src\\IOStream", "demo.txt");
        String name = f.getName();
        String absPath = f.getAbsolutePath();
        String path = f.getPath();
        long len = f.length();
        long time = f.lastModified();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);

        System.out.println(name);
        System.out.println(absPath);
        System.out.println(path);
        System.out.println(len);
        System.out.println(time);

        System.out.println(f.getTotalSpace());
        System.out.println(f.getUsableSpace());
        System.out.println(f.getFreeSpace());
    }

    public static void createAndDeleteDemo() throws IOException {
//        目录的创建与删除
        File dir = new File("abc");
        boolean b = dir.mkdir();//创建目录，不过是用于创建单个目录的，无法创建多个目录，多级目录需要使用mkdirs();

        b = dir.delete();//注意，一个目录中如果有文件，那么这条语句是无法将其删除的，因为需要从里往外删;另外多级目录删除的时候，会将最后一个目录进行删除，前面的那么多级是无法删除的


//        文件的创建与删除
//        File f = new File("E:\\java_source\\javaStudyExercise\\src\\IOStream", "demo.txt");
//
//        //创建文件，和输出流的文件存在就覆盖不一样，这里的创建为文件存在就不创建，文件不存在的才会创建
//        boolean b = f.createNewFile();
//
//        System.out.println(b);
//        boolean b = f.delete();
//        System.out.println(b);


    }
}
