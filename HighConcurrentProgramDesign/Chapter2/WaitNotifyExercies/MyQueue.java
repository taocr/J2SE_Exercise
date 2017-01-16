package HighConcurrentProgramDesign.Chapter2.WaitNotifyExercies;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Taocr on 2016/12/21.
 */
public class MyQueue<Integer> {
    private List list = new LinkedList<Integer>();
    private final int capacity;

    public MyQueue() {
        this(5);
    }

    public MyQueue(int capacity) {
        this.capacity = capacity;
    }

    public Integer get() {
        synchronized (list) {
            Integer buf = null;
            while (list.isEmpty()) {
                try {
                    System.out.println("Queue is null,get Thread " + Thread.currentThread().getName() + "stop");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            buf = (Integer) list.get(0);
            list.remove(0);
            System.out.println("get" + buf);

            if (list.size() < capacity) {
                list.notifyAll();
                //System.out.println("Queue is not full now, notifyAll");
            }

            return buf;
        }
    }

    public void add(Integer obj) {
        synchronized (list) {
            try {
                while (list.size() == capacity) {
                    System.out.println("Queue is full, add Thread " + Thread.currentThread().getName() + "stop");
                    list.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list.add(obj);
            System.out.println("add" + obj);

            if (!list.isEmpty()) {
                list.notifyAll();
                //System.out.println("Queue is not null now, notifyAll");
            }
        }
    }

}