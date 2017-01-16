package HighConcurrentProgramDesign.Chapter4;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by Taocr on 2016/12/25.
 */
public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);//0用来表示时间戳的初始值

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final int timestamp = money.getStamp();//先获取时间戳，后面的赠予充值操作以此时间戳为依据
            new Thread() {
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.getReference();//获取具体的钱
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) { //若余额充值成功，则会修改时间戳
                                    System.out.println("余额小于20，充值成功，现余额为" + money.getReference() + "元");
                                    break;
                                } else {
                                    System.out.println("余额为" + money.getReference() + "元，无需充值");
                                    break;
                                }
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
                        int timestamp = money.getStamp();
                        Integer m = money.getReference();
                        if(m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
                                System.out.println("成功消费10元，余额："+money.getReference());
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
