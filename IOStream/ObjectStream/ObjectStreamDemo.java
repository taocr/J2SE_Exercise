package IOStream.ObjectStream;

import java.io.*;

/**
 * Created by Taocr on 2016/11/6.
 */
public class ObjectStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String name = "Person";

//        Person p = new Person("Taocr", 25, "Programer");//创建一个Person类的实例
//        writeObj(p, name);//写入本地存储中

        Person readp = (Person) readObj(name);
        System.out.println(readp.getPerson());
        System.out.println(readp.getAge());
        System.out.println(readp.getJob());
    }

    public static Object readObj(String name) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name+".object"));
        Object buf = ois.readObject();
        return buf;
    }

    public static void writeObj(Object obj, String name) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name + ".object"));
        oos.writeObject(obj);
        oos.close();
    }
}
