package Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Taocr on 2016/12/14.
 */
public class ReflectConstructor {
    public static void createNewObject() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //早期：new时，先根据被new的类的名称找寻该类的字节码文件，并加载进内存，创建该字节码文件对象，并接着创建该字节码文件对应的Person对象
        //写法简单
        Reflect.Person p = new Reflect.Person();

        //现在只有名称，没有其他的东西，即不清楚有没有这个类，也想完成这个动作
        //扩展性强
        String name = "Reflect.Persoon";
        Class clazz = Class.forName(name);//找寻名称的类文件，加载金内存，产生Class对象
        //如何产生该类的对象？
        Object obj = clazz.newInstance();//newInstance的作用就是调用空参的构造函数，且如果类没有进行初始化就会进行初始化

        //对于类中没有空参的构造函数的情况
        //newInstance只能调用空参的构造函数，一般被反射的类都带空参，获取实例方便
        Reflect.Person person = new Reflect.Person(123, "abc");
        //对于没有空参构造函数的情况，既然是通过指定构造函数进行对象初始化，那么应该先获取该构造函数，getConstuctor()可以根据参数列表的具体的数据类型获取所有的公共构造方法，getDeclaredConstructor可以获取所有的构造方法，即使是private的
        //构造函数本身也是一个对象Constructor
        Constructor constructor = clazz.getConstructor(int.class, String.class);//获取指定的构造函数（构造器）对象
        Object obj2 = constructor.newInstance(123, "abc");
    }
}
