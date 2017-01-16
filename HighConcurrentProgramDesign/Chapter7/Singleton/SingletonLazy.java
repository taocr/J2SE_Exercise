package HighConcurrentProgramDesign.Chapter7.Singleton;

/**
 * Created by Taocr on 2017/1/14.
 */
public class SingletonLazy {
    private SingletonLazy() {
        System.out.println("Singleton is create");
    }

    private static SingletonLazy instance = null;

    public static SingletonLazy getInstance() {
        if (instance == null)
            instance = new SingletonLazy();
        return instance;
    }
}
