package Reflect;

/**
 * Created by Taocr on 2016/12/14.
 */
public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    public Person() {
        super();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void test() {
        System.out.println("Method test execute success");
    }
}
