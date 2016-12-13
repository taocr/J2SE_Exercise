package IOStream.Properties;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Taocr on 2016/11/3.
 */
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
//        propertiesDemo();
//        propertiesDemo2();
//        propertiesDemo3();
//        propertiesDemo4();
//        myLoad();
//        propertiesDemo4();
        setETCFile();
    }

    private static void setETCFile() throws IOException {
        //文件不一定存在，所以封装成File对象
        File f = new File("PropertiesStore");
        if(!f.exists())
            f.createNewFile();
        Properties prop = new Properties();

        prop.load(new FileInputStream(f));

        prop.setProperty("a","100");

        prop.store(new FileOutputStream(f), "name+age");


    }

    //对已有的配置文件中的信息进行修改
    /*
    * 读取这个文件，并将这个文件中的键值数据存储到集合中，再通过集合对数据进行修改
    * */

    private static void propertiesDemo4() throws IOException {
//        集合中的数据来自一个文件
//        注意：必须保证此文件中的数据是键值对
//        因此需要使用到读取流，即Properties的load方法
        Properties  prop = new Properties();

        prop.load(new FileInputStream("PropertiesStore"));

        prop.list(System.out);
    }



    public static void myLoad() throws IOException {
//        模拟一个自己的load方法，就是读取配置文件的内容每次读一行，然后按照=号进行split，前面作为键后面作为值

        Properties prop = new Properties();

        BufferedReader bufr = new BufferedReader(new FileReader("PropertiesStore"));

        String line = null;
        while((line = bufr.readLine()) != null){
            if(line.startsWith("#"))
                continue;
            String[] array = line.split("=");
            prop.setProperty(array[0],array[1]);
        }

        prop.list(System.out);
        bufr.close();//打开一个IO流就要关闭它
    }

    private static void propertiesDemo3() throws IOException {
        //创建一个Properties集合
        Properties prop = new Properties();

        //存储元素
        prop.setProperty("a","30");
        prop.setProperty("b","31");
        prop.setProperty("c","32");
        prop.setProperty("d","33");

        //修改元素
        prop.setProperty("b","45");

        //将集合中数据存储到文件中，使用store方法
        prop.store(new FileWriter("PropertiesStore"), "name++age");
    }




    public static void propertiesDemo(){
        //创建一个Properties集合
        Properties prop = new Properties();

        //存储元素
        prop.setProperty("a","30");
        prop.setProperty("b","31");
        prop.setProperty("c","32");
        prop.setProperty("d","33");

        //修改元素
        prop.setProperty("b","45");

        //取出所有元素
        Set<String> names = prop.stringPropertyNames();

        for(String name : names){
            String value = prop.getProperty(name);
            System.out.println(name+"---"+value);
        }
    }




    public static void propertiesDemo2(){
        //创建一个Properties集合
        Properties prop = new Properties();

        //存储元素
        prop.setProperty("a","30");
        prop.setProperty("b","31");
        prop.setProperty("c","32");
        prop.setProperty("d","33");

        System.getProperties();//获取本机信息，即虚拟机的，跟下面的list很像，可以理解为虚拟机的信息存在prop中，然后调用getProperties一次全部输出，list也是这个功能
        prop.list(System.out);

    }
}
