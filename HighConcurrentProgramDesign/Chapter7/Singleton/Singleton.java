package HighConcurrentProgramDesign.Chapter7.Singleton;

/**
 * Created by Taocr on 2017/1/14.
 */
public class Singleton {
    private Singleton() {
        System.out.println("Singleton is create");
    }

    private  static Singleton instance = new Singleton();
    public static Singleton getInstance() {
        return instance ;
    }
}
