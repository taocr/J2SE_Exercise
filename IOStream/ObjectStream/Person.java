package IOStream.ObjectStream;

import java.io.Serializable;

/**
 * Created by Taocr on 2016/11/7.
 */
public class Person implements Serializable{
    private static final long serialVersionUID = 9527L;

    public String person;
    private int age;
    private transient String Job;//transient关键字保证不会被序列化写入到本地存储，即瞬态值

    public Person(){};

    public Person(String person, int age, String job){
        this.age = age;
        this.person = person;
        Job = job;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }
}
