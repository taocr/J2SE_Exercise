package Net.UDP;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Taocr on 2016/12/7.
 */
public class IPDemo {
    public static void main(String[] args) throws UnknownHostException {
        //获取本地主机ip地址对象
        InetAddress ip = InetAddress.getLocalHost();//需要抛出为知主机异常

        //获去其他主机的ip地址对象
        ip = InetAddress.getByName("119.75.218.70");//or InetAddress.getByName("my_think");
        //这步之后的显示名称的部分是需要解析的，对于给的IP地址所对应的列表中的项，那么就无法找到对应的主机名
        //通过域名可以解析出IP地址，通过IP地址却没有解析出域名
        //getAllByName()这个方法是因为有的主机对应的Ip地址不唯一，因此可以通过它返回一个InetAddress的数组

        System.out.println(ip.getHostAddress()+"..."+ip.getHostName());
    }
}
