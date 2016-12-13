package IOStream.Properties;

import java.io.*;
import java.util.Properties;

/**
 * 功能：获取一个应用程序运行的次数，如果超过5此，给出使用次数已到请注册的提示，并不要再运行程序
 *
 * 思路：
 * 1、应该有计数器
 * 每次程序启动都需要计数一次，并且是在原有的次数上进行计数
 *
 * 2、计数器就是一个变量。程序启动时进行计数，计数器必须存在于内存并进行计算
 *
 * 3、程序结束，计数器需要保存在磁盘上，以免再次启动程序的时候计数器被清空
 *
 * 4、文件中信息如何存储
 * 直接存储次数不明确数据的含义，所以起名字变得很重要，即名字和值的对应，即使用键值对
 *
 * Created by Taocr on 2016/11/3.
 */
public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        getAppCount();
    }

    public static void getAppCount() throws IOException {
//        将配置文件封装成File对象
        File confile = new File("count.ini");//ini在Windows中较为常用，作为Java来说直接为.properties，在Tomcat中就很常见

        if (!confile.exists())
            confile.createNewFile();

        FileInputStream fis = new FileInputStream(confile);

        Properties prop = new Properties();
        prop.load(fis);

//        从集合中通过键获取次数
        String value = prop.getProperty("time");

        int count = 0;//因为获取出来的是String类型的值，因此需要定义一个计数器来进行自增操作
        if(value != null) {
            count = Integer.parseInt(value);
            if (count >= 5){
//                System.out.println("使用次数已到，请购买正版");
//                return;
                throw new RuntimeException("使用次数已到，请购买正版");//相比于前面的方法，return返回这个方法，而抛出异常则会让整个程序退出，所以这样操作更加合适
                //注意不要抛出Error，因为Error是虚拟机底层抛出的，不适合
            }
        }
        count++;
//        将改变后的次数重新存储到集合中
        prop.setProperty("time", count+"");

        FileOutputStream fos = new FileOutputStream(confile);
        prop.store(fos, "");

        fos.close();
        fis.close();
    }
}
