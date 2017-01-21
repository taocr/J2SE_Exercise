package HighConcurrentProgramDesign.Chapter10;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by Taocr on 2017/1/19.
 */
public class Point {
    private double x, y;
    private final StampedLock sl = new StampedLock();   //获取一个StampedLock，每次写操作可能会对时间戳加上一个数

    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();    //每次写入的时候加锁，stamp都会进行加一个数的变化（可能不是1）
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);  //每次写入结束解锁，stamp也会进行加一个数的变化（不能不是1）
            //注意，这里发生加的操作是会溢出的，在溢出之前是绝对不会发生stamp重复的现象的，溢出后就会从头开始
        }
    }

    double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();    //乐观读，不会阻塞写，当乐观读读取邮戳后
        double currentX = x, currentY = y;  //将x，y读出来，可能最后读出来x是原来的，y是修改后的，所以数据不一定是一致的
        if (!sl.validate(stamp)) {  //验证刚读出的stamp是否可用，其成功的条件是sl读取新的stamp于之前读到的stamp相同，当写锁被占用那么stamp的值一定会变化，因此乐观读就一定失败。
            stamp = sl.readLock();  //对于验证不成功的情况，stamp的值不一致，有着很多中处理方法，可以重新读取一次（写一个死循环，直到成功为止），这里的策略是乐观锁失败的话就锁上读锁，即将StampedLock退化成了读写锁的形式，之后进行数据的读，最后释放读锁（悲观的读法）
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentX * currentX);
    }
}
