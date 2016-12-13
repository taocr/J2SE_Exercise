package IOStream.TransStream;

import java.io.*;

/**
 * Created by Taocr on 2016/10/30.
 */
public class TransStreamDemo {
    public static void main(String[] args) throws IOException {
//        //字节流
//        InputStream in = System.in;//数据在这个输入流中
//
//        //将字节转成字符的桥梁，转换流
//        InputStreamReader isr = new InputStreamReader(in);
//        //其实这里已经将字节流转为了字符流，于是我们已经可以使用字符流的方法进行读取，只不过使用BufferedReader更为高效
//
//        //字符流
//        BufferedReader bufr = new BufferedReader(isr);//希望使用的是BufferedReader中的readLine方法，无法直接将in对象作为BufferedReader构造函数的参数，因为其为字符流装饰类而不是字节流，于是思考如何将字节流转为字符流
//
//        OutputStream out = System.out;//定义一个标准输出流
//
//        OutputStreamWriter osw = new OutputStreamWriter(out);//读入的为字节流，通过InputStreamReader已经转换为了字符流并组成了一个字符串，而现在我们能够输出的是字节流，所以两者之间需要再有一个转换流进行转换
//        BufferedWriter bufw = new BufferedWriter(osw);//对转换流进行一个装饰，从而方便输出，也有了newLine方法，也可以不进行装饰，实际上这里的调用构造函数如果参数是FileWriter流就会将数据写入到文件中
//        String buf = null;
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));//将上面的代码整合起来就成为了这两句，即首先获取标准输入输出流、用转换流将字节流转为字符流，最后用缓冲的方案进行装饰，从而方便操作
        String buf = null;

        while((buf = bufr.readLine()) != null){
            if(buf.equals("over"))
                break;
//            System.out.println(buf.toUpperCase());//实际上这也是一个流，向PrintStream流中写入一个数据，其为OutputStream的孙子类
            bufw.write(buf.toUpperCase());
            bufw.newLine();
            bufw.flush();
        }
    }
}
