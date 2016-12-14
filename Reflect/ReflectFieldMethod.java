package Reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Taocr on 2016/12/14.
 */
public class ReflectFieldMethod {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

//        getField();
        getMethod();

    }
    public static void getField() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName("Reflect.Person");
        Field field = clazz.getDeclaredField("age");//注意：由于已经获取了类的Class对象了，那么我们可以直接在其中找寻相应字段了，即字段名不需要全称
        System.out.println(field);
        //获取具体的字段后我们希望能够给其赋值，但是注意这里我们只是获取了字段，并没有实际地对象，我们赋值的前提是给某对象的某字段赋值，所以这里先需要获取对象
        Object obj = clazz.newInstance();
//        Object objField = field.get(obj);

        //出现错误，java.lang.IllegalAccessException: Class Reflect.ReflectFieldMethod can not access a member of class Reflect.Person with modifiers "private"，因为age这个字段在类中是私有的
        //Constructor，Method、Field三个类有着共同的父类AccessibleObject，它提供了将反射的对象标记为在使用时取消默认 Java 语言访问控制检查的能力。
        // 对于公共成员、默认（打包）访问成员、受保护成员和私有成员，在分别使用 Field、Method 或 Constructor 对象来设置或获取字段、调用方法，或者创建和初始化类的新实例的时候，会执行访问检查。
        //从以上描述可以看出对于一些私有的等方法声明的成员，其会关闭掉访问检查能力，从而正常得到

        //对私有字段的访问，取消权限检查。暴力访问，不建议使用，因为选择私有就代表不希望你能够访问
        field.setAccessible(true);
//        Object objField = field.get(obj);
//        objField = 30;//设置值为30
        field.set(obj, 89);//通过字段的对应方法对本类一个实例中的具体字段进行赋值
        Object o = field.get(obj);//通过get方法获取到了一个实例中的具体字段的实例

        System.out.println();

//        Field[] fields = clazz.getDeclaredFields();//getFields只能获取公有的
//        for (Field currentField : fields)
//            System.out.println(currentField);
    }

    public static void getMethod() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Class.forName("Reflect.Person");

        Method method = clazz.getMethod("test");
        System.out.println(method.getName());

        Object obj = clazz.newInstance();
        method.invoke(obj,null);//运行此方法，需要指定对象，因为没有对象方法无从运行，且参数没有则需要指定为null

//        对于静态方法，只需要将invoke的第一个参数设置为null即可，因为静态方法不需要实例就可以执行
//        method.invoke(null,null);
    }
}
