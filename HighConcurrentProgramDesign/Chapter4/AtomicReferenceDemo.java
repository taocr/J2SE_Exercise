package HighConcurrentProgramDesign.Chapter4;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Taocr on 2016/12/25.
 */
public class AtomicReferenceDemo {
    static AtomicReference<Integer> money = new AtomicReference<Integer>();

    public static void main(String[] args) {
        money.set(19);//设置小于20的初始值，即一个需要被充值的账户

        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                        while (true) {
                            Integer m = money.get();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20)) {
                                    System.out.println("余额小于20，充值成功，现余额为" + money.get() + "元");
                                    break;
                                } else {
                                    System.out.println("余额为"+money.get()+"元，无需充值");
                                    break;
                                }
                            }
                        }

                }
            }.start();
        }

        new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        Integer m = money.get();
                        if(m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10)) {
                                System.out.println("成功消费10元，余额："+money.get());
                                break;
                            }
                        } else {
                            System.out.println("没有足够金额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                }
            }
        }.start();
    }
}
