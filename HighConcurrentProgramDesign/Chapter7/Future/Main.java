package HighConcurrentProgramDesign.Chapter7.Future;

/**
 * Created by Taocr on 2017/1/14.
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        System.out.println("数据=" + data.getResult());
    }
}
