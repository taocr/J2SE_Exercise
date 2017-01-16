package HighConcurrentProgramDesign.Chapter7.Singleton;

/**
 * Created by Taocr on 2017/1/14.
 */
public class SingletonFinal {
    private SingletonFinal() {
        System.out.println("SingletonFinal is create");
    }

    private static class SingletonHolder {
        private static SingletonFinal instance = new SingletonFinal();
    }

    public static SingletonFinal getInstance() {
        return SingletonHolder.instance;
    }
}
