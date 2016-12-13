package GUI;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Taocr on 2016/11/8.
 */
public class MouseAndKeyDemo {//一般做界面的时候都是采用先定义组件然后在方法中初始化的做法，另外也不是所有的组件在方法中都需要立刻初始化，可以在用时才去进行初始化
    private Frame f;//首先明确图形界面需要有多少组件，而在后面会对这里定义的每个声明进行初始化
    private TextField tf;
    private Button but;

    public MouseAndKeyDemo(){
        init();
    }

    private void init() {
        f = new Frame("演示鼠标和键盘监听");
        f.setBounds(200, 200, 500, 400);
        f.setLayout(new FlowLayout());//设置布局管理器

        tf = new TextField(15);//添加一个文本框组件
        f.add(tf);

        but = new Button("exit");
        f.add(but);//此时点击此按钮毫无用处，因为没有设置监听器与实现事件发生时如何处理

        myEvent();

        f.setVisible(true);
    }

    private void myEvent() {
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
                System.exit(0);
            }
        });

        //在按钮上增加一个鼠标监听
        but.addMouseListener(new MouseAdapter() {
            int count = 0;

            @Override
            public void mouseClicked(MouseEvent e) {//只有单击没有双击，那么如何实现双击的监听？在MouseEvent中有记录点击次数，因此可以以此来判断是否有达到双击的事件
//                super.mouseClicked(e);
                if (e.getClickCount() == 1)
                    tf.setText("mouse click"+count++);
                else if (e.getClickCount() == 2)
                    tf.setText("mouse double click");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
//                super.mouseEntered(e);
//                int count = 0;在这里定义计数器是无法正常计数的，因为变量的定义位于方法内部，每次监听到事件才会执行，于是相当于每次重新定义这个变量，处理一次事件后就释放
//                System.out.println("mouse enter"+count);//鼠标只要进入都组件之中就会触发
                tf.setText("mouse enter"+count++);//触发按钮的鼠标事件时在文本框中会输入
            }
        });

        //在文本框上增加一个键盘监听
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
//                System.out.println("key run"+"..."+KeyEvent.getKeyText(e.getKeyCode()));

                int code = e.getKeyCode();
                if (!(code >=KeyEvent.VK_0 && code <= KeyEvent.VK_9)) {//KeyEvent类的内部将所有的键盘按键都封装成了一个常量，因此可以直接从中找到想要匹配的按键按下的情况
                    System.out.println("必须输入数字");
                    e.consume();//使用此事件，以便不会按照默认的方式由产生此事件的源代码来处理此事件，默认情况就是会将字符显示在TextField中，而不按默认后文本框中就没有显示不符合的字符了
                }

// TODO: 2016/11/9
//                设计一个方法能够在TextField中按下回车后就出发按钮的点击一下的事件。
//                ActionListener[] al = but.getActionListeners();
//                if (e.getKeyCode() == KeyEvent.VK_ENTER)
//                    al[0].actionPerformed(new ActionEvent());
            }
        });
    }

    public static void main(String[] args) {
        new MouseAndKeyDemo();
    }
}
