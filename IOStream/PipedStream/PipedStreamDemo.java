package IOStream.PipedStream;

import com.sun.xml.internal.bind.v2.TODO;
import com.sun.xml.internal.ws.api.policy.SourceModel;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by Taocr on 2016/11/7.
 */
public class PipedStreamDemo {
    public static void main(String[] args) throws IOException {
        PipedInputStream input = new PipedInputStream();
        PipedOutputStream output = new PipedOutputStream();//先创建管道输入输出流

        input.connect(output);//将输入与输出流关联起来
        new Thread(new Input(input)).start();//定义线程去执行之前定义好的方法，将之前定义好的输入输出流作为参数进行初始化，从而让线程将信息写入管道中
        new Thread(new Output(output)).start();
    }


}

class Input implements Runnable{

    private PipedInputStream in;

    Input(PipedInputStream in){
        this.in = in;
    }

    @Override
    public void run() {
        try{
            byte[] buf = new byte[1024];
            int len = in.read(buf);

            String s = new String(buf, 0, len);

            System.out.println("s="+s);
            in.close();
        } catch (Exception e){
            // TODO: 2016/11/7 handle exception
        }

    }
}

class Output implements Runnable{
    private PipedOutputStream out;

    Output(PipedOutputStream out){
        this.out = out;
    }


    @Override
    public void run() {
        try {
            out.write("hello pipe".getBytes());
        } catch (Exception e){

        }

    }
}