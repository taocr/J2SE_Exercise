package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Taocr on 2016/11/8.
 */
public class FrameDemo {
    public static void main(String[] args) {
        Frame f = new Frame("my frame");//负责图形化界面运行的线程，从这里开始显示一个Frame，所以即使主线程结束，负责图形化界面的线程也不会结束

        f.setSize(500, 400);//一般第一个参数为横轴，第二个参数为纵轴
        f.setLocation(200, 150);//设置位置

//        f.setBounds(1500, 150, 500, 400);//先设置位置，然后设置宽和高的属性,效果等同于上面的两句

        f.setLayout(new FlowLayout());//设置流式布局，改变Frame窗体的布局方式
        /**
         * 添加一个按钮
         */
        Button b = new Button("exit");

        //在按钮上添加监听，一点按钮就退出

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        f.add(b);//添加完成后由于Frame默认的边界式布局，在没有改变布局方式且没有指定东南西北的情况下会让组件居中并填充整个Frame，因此会显示一个极大的按钮


        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {//运用匿名内部类的方法，实际上实现了WindowAdapter的子类，并在其中覆写了windowClosing方法，因为WindowAdapter是一个没有抽象方法的抽象类，所以这里只需要覆写所需的方法即可
//                super.windowClosing(e);父类就是WindowAdapter这个抽象类，其的几个方法都为空，即没有具体实现，因此实际上不需要使用父的方法，直接覆盖了自己选择功能完成即可
                System.out.println("closing"+e);
                System.exit(0);
            }
        });
        //当发生了窗体事件时会自动将窗体事件打包成e传给windowClosing这个方法

//        f.show();//已经过时了，1.1版本后就改为使用setVisible
        f.setVisible(true);//此时显示后无法关闭


    }
}
