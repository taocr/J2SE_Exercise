package Reflect;

/**
 * 获取Class文件对象的三种方式
 * Created by Taocr on 2016/12/13.
 */
public class ReflectDemo  {
    public static void main(String[] args) throws ClassNotFoundException {
        getClassObject_3();
    }

//    Object.getClass()方法获取Class文件，
    public static void getClassObject_1() {
        Person p = new Person();
        Class calzz = p.getClass();
    }

//    .class
    public static void getClassObject_2() {
        Class clazz = Person.class;
        Class clazz1 = Person.class;
        System.out.println(clazz == clazz1);
    }

//    Class.forName
    public static void getClassObject_3 () throws ClassNotFoundException {
        String className = "Reflect.Person";//此处要注意需要有包名的存在，即要写出类的全名称，不可以有class的扩展名，否则即使类文件存在也找不到
        Class clazz = Class.forName(className);


        System.out.println(clazz);
    }
}
