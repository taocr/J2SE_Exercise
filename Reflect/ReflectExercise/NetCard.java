package Reflect.ReflectExercise;

/**
 * Created by Taocr on 2016/12/14.
 */
public class NetCard implements PCI{

    @Override
    public void open() {
        System.out.println("net card open");
    }

    @Override
    public void close() {
        System.out.println("net card close");
    }
}
