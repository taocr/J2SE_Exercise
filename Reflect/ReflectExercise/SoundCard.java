package Reflect.ReflectExercise;

/**
 * Created by Taocr on 2016/12/14.
 */
public class SoundCard implements PCI{

    @Override
    public void open() {
        System.out.println("sound open");
    }

    @Override
    public void close() {
        System.out.println("sound close");
    }
}
