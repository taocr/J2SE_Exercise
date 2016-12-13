package IOStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 读取一个键盘录入的数据，并打印在控制台上.
 *
 * 对于java而言，对于输入设备都有对应的对象
 * Created by Taocr on 2016/10/29.
 */
public class ReadKey {
    public static void main(String[] args) throws IOException {
//        readKey();//用容器接收输入的字符
        readKey2();//练习：用数组来接收

    }

    private static void readKey2() throws IOException {
        char[] buf = new char[1024];
        InputStream in = System.in;

        int ch = 0;
        int j = 0;
//        for(int j = 0;ch != -1; ch = in.read(),j++) {
        while((ch = in.read()) != -1){
            if (ch == '\r')
                continue;
            if (ch == '\n') {
                String tmp = Arrays.toString(buf);
                tmp= tmp.replace(" ","");
                tmp = tmp.replace(",", "");
                tmp = tmp.substring(1,tmp.length()-1);
                if (tmp.equals("over"))
                    break;
                System.out.println(tmp.toUpperCase());
                for (int i = 0; i < buf.length; i++) {
                    buf[i] = 0;
                    j = 0;
                }
            } else {
                buf[j] = (char) ch;
                j++;
            }
        }

    }

    private static void readKey() throws IOException {
        /*
        *获取用户键盘录入的数据，并将数据变成大写显示在控制台上，如果用户输入的是over，结束键盘录入
        *
        * 因为键盘录入只读取一个字节，要判断是否为over,需要将读取到的字节拼接成字符串，于是需要一个容器,因为长度不知道，所以选用StringBuilder或者StringBuffer比较好
        * 在用户回车之前将录入的数据变成字符串判断即可
        * */

        StringBuilder sb = new StringBuilder();
        InputStream in = System.in;//从System类中直接获取键盘的输入流对象

        int ch = 0;
        while ((ch = in.read()) != -1) {
            //换行标记不存，所以存储前要0判断是否是换行标记
            if(ch == '\r')
                continue;
            if(ch == '\n') {
                String tmp = sb.toString();

                if(sb.equals("over"))
                    break;
                System.out.println(tmp.toUpperCase());
                sb.delete(0, sb.length());
            } else
                sb.append((char)ch);

//            if(ch >= 97 && ch <= 122)
//                ch -= 32;
//            sb.append((char)ch);
        }
        //键盘录入一般情况下无法结束，
    }
}
