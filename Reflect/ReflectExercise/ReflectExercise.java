package Reflect.ReflectExercise;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 电脑运行,主板运行后添加各种设备
 * Created by Taocr on 2016/12/14.
 */
public class ReflectExercise {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Mainboard mb = new Mainboard();
        mb.run();
        //每次添加设备都需要修改代码，传递新创建的对象
//        mb.usePCI(new SoundCard());

        //如何不修改代码就可以完成此动作?
        //不用new来完成，直接获取Class文件，在内部实现创建对象的动作,即用反射来完成，因为没有学过xml配置文件，所以用properties代替
        File configFile = new File(".\\src\\Reflect\\PCI.properties");
        if (configFile == null)
            configFile.createNewFile();

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(configFile);
        prop.load(fis);

        for (int i = 0; i < prop.size(); i++) {
            String pciName = prop.getProperty("PCI" + (i + 1));
            if (pciName == null)
                continue;

            //得到类名后，使用反射的方法运行具体的类中的方法，因其符合PCI接口规范，所以我们可以确定要执行的方法
            Class clazz = Class.forName(pciName);
            PCI pci = (PCI) clazz.newInstance();//因为PCI设备要符合PCI的接口标准，所以这里将其强转成PCI并赋给PCI的引用

            mb.usePCI(pci);
        }
    }
}
