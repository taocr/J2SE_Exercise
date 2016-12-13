package IOStream;

import java.io.UnsupportedEncodingException;

/**
 * java中，字符串"abcd"与字符串"ab你好"的长度是一样的，都是四个字符。
 * 但是对应的字节数不同，一个汉字占两个字节
 * 定义一个方法，按照最大的字节数来取子串
 * 如：对于"ab你好"，如果取三个字节，那么子串就是ab与"你"字的半个，那么半个就要舍弃。如果取四个师姐就是"ab你"，取五个字节还是"ab你"
 *
 * 中文都是由两个字节组成，而且每个字节的最高位为1，即都是负数
 * Created by Taocr on 2016/11/8.
 */
public class SplitStringWithByte {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "ab你好cd谢谢";
        int len = str.getBytes().length;

        for (int x = 0; x < len; x++) {
//            System.out.println("截取" + (x + 1) + "个字节结果为：" + cutStringByGBKByte(str, x + 1));
            System.out.println("截取" + (x + 1) + "个字节结果为：" + cutStringByUTF8Byte(str, x + 1));
        }
    }

    public static String cutStringByGBKByte(String str, int len) throws UnsupportedEncodingException {
        byte[] buf = str.getBytes("gbk");

        int count = 0;
        for(int x = len-1; x >= 0; x--){
            if(buf[x] < 0)
                count++;
            else
                break;
        }

        if(count %2 == 0)
            return new String(buf, 0, len,"gbk");
        else
            return new String(buf, 0, len-1,"gbk");
    }

    public static String cutStringByUTF8Byte(String str, int len) throws UnsupportedEncodingException {
        byte[] buf = str.getBytes("utf-8");

        int count = 0;
        for(int x = len-1; x >= 0; x--){
            if(buf[x] < 0)
                count++;
            else
                break;
        }

        if(count % 3 == 0)
            return new String(buf, 0, len,"utf-8");
        else if (count % 3 == 1)
            return new String(buf, 0, len-1,"utf-8");
        else
            return new String(buf, 0, len-2, "utf-8");
    }
}
