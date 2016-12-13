package IOStream;

import java.io.*;

/**
 * 自定义的读取缓冲区，模拟一个BufferedReader
 *
 * 分析：
 * 缓冲区中无非就是封装了一个数组，并对外提供了更多的方法对数组进行访问
 * 其实这些方法最终操作的都是数组的角标
 *
 * 缓冲的原理：
 * 其实就是从源中获取一批数据装进缓冲区中，再从缓冲区中不断的取出一个一个数据，这次取完后再从源中继续取一批数据进缓冲区
 * 当源中的数据区光时，用-1作为结束标记。
 *
 *
 * Created by Taocr on 2016/10/26.
 */
public class MyBufferedReader extends Reader{//装饰设计模式的简单的思想，其实是应该继承FileReader的父类的（以实现关系），但是由于FileReader的父类
// 为InputStreamReader，而InputStreamReader的初始化需要InputStream的对象，而FileReader不是InputStream的子类，因此如果继承
// InputStreamReader，则无法将FileReader作为参数传入，因此选择继承Reader类，将FileReader对象强转型为Reader类型作为参数传入，
// 但是要去实现抽象方法read()和close()，太过麻烦而且已经实现好了自己的myRead()，所以这里就选择不继承

//    private FileReader r;对于Reader的子类，所以不能只增强FileReader，应该增强Reader
    private Reader r;

    //定义一个数组作为缓冲区
    private char[] buf = new char[1024];

    //定义一个指针用于操作这个数组中的元素，当操作到最后一个元素后，指针应该归零
    private int pos = 0;

    //定义一个计数器用于记录缓冲区中的数据个数。当该数据减到0，就从源中继续获取数据到缓冲区中
    private int count = 0;

    public MyBufferedReader(Reader r){
        this.r = r;
    }

    public int myRead() throws IOException {//这里的代码不try是希望能够抛出去让使用者try
        if (count == 0){
            count = r.read(buf);//read(buf)会尝试读取与给定字节数组一样大的字节数其返回值说明了已经读取过的字节数
            pos = 0;
        }
        if(count < 0)
            return -1;

        char ch = buf[pos];

        pos++;
        count--;

        return ch;
    }

    public String myReadLine() throws IOException {//简单的readLine的实现，原理相同，代码较为简单
        StringBuilder sb = new StringBuilder();

        int ch = 0;

        while((ch = myRead()) != -1){
            if(ch == '\r' )
                continue;
            if(ch == '\n')
                return sb.toString();//读到了\n代表了换行，则将之前放入缓冲区的数据全部返回

            sb.append((char)ch);//从缓冲区中读到的字符，存储到缓存行数据的缓冲区中
        }

        if(sb.length() != 0)//出现一行中后面没有回车符的情况，一般来说是文件的最后一行，则将StringBuilder中存的元素都返回出来
            return sb.toString();

        return null;//到达流末尾返回null
    }

    public void myClose() throws IOException {//关缓冲就是在关流对象
        r.close();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {//已经用myRead实现了
        return 0;
    }

    @Override
    public void close() throws IOException {//已经用MyClose实现，这里就不进行操作了

    }
}
