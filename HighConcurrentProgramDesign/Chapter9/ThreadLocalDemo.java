package HighConcurrentProgramDesign.Chapter9;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Taocr on 2017/1/18.
 */
public class ThreadLocalDemo {
    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>();  //实例化一个ThreadLocal，存储时会用作Map中的key
    public static class ParseDate implements Runnable {
        int i = 0;
        public ParseDate(int i ) throws ParseException {
            this.i = i;
        }

        @Override
        public void run() {
            if(tl.get() == null)    //获取当前线程持有的SimpleDateFormat对象
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));    //多线程一般用SimpleDateFormat来解析字符串类型的日期，这里对于当前线程没有SimpleDateFormat实例的情况，新建一个实例并存入ThreadLocalMap中
            try {
                Date t = tl.get().parse("2015-03-29 19:29:" + i%60);    //获取SimpleDateFormat实例并进行i的解析
                System.out.println(i + ":" + t);    //显示解析出的时间
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
