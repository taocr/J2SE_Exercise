package IOStream;

import java.io.*;
import java.util.*;

/**
 * Created by Taocr on 2016/11/4.
 */
public class SequenceInputStreamDemo {
    public static void main(String[] args) throws IOException {
        /**
         * 将1.txt、2.txt、3.txt三个文件合并为一个4.txt文件
         */
//        Vector<FileInputStream> v = new Vector<FileInputStream>();
//        v.add(new FileInputStream("1.txt"));
//        v.add(new FileInputStream("2.txt"));
//        v.add(new FileInputStream("3.txt"));

        //1、为了效率的问题，用vector换成使用ArrayList
        ArrayList<FileInputStream> al = new ArrayList<FileInputStream>();
        for (int i = 1; i <= 3; i++) {
            al.add(new FileInputStream(i+".txt"));
        }


        //2、由于SequenceInputStream当定义三个以上的输入流时需要使用Enumeration类，所以这里选择自己创建一个
        //问题在于ArrayList没有Enumeration，但是枚举实际上与迭代器的功能重复
//        Iterator<FileInputStream> it = al.iterator();
//
//        Enumeration<FileInputStream> en = new Enumeration<FileInputStream>() {
//            @Override
//            public boolean hasMoreElements() {
//                return it.hasNext();//这样在枚举接口实现过程中利用了迭代器，因为迭代器与其的功能重复了，所以这样可以实现功能，但是这样显得过于麻烦
//            }
//
//            @Override
//            public FileInputStream nextElement() {
//                return it.next();
//            }
//        };

        //3、查找API后会发现，在Collections中存在着一个enumeration方法可以返回一个Enumeration对象，接收的是Collection，实际上Collections中存在的全都是静态方法，基本像是Collection的一个工具类
        Enumeration<FileInputStream> en = Collections.enumeration(al);
        

//        Enumeration<FileInputStream> en = v.elements();


        SequenceInputStream sis = new SequenceInputStream(en);

        FileOutputStream fos = new FileOutputStream("4.txt");

        byte[] buf = new byte[1024];
        int len = 0;
        while((len = sis.read(buf)) != -1){
            fos.write(buf, 0, len);
        }
        fos.close();
    }
}
