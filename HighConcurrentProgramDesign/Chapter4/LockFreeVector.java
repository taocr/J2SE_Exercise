package HighConcurrentProgramDesign.Chapter4;

import java.util.AbstractList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created by Taocr on 2016/12/25.
 */
public class LockFreeVector<E> extends AbstractList<E>{
    private static final boolean debug = false;

    private final AtomicReferenceArray<AtomicReferenceArray<E>> buckets;//用二维数组来做一维数组，这样在Vector扩展的时候
    // 更方便，整个二维数组不均衡，即第一个数组元素为8个，之后不够用再进行扩展，扩展得出的新数组就是第二个数组，元素为20个从而使得
    // 整个二维数组当做一维数组来使用
    //为了确保线程之间同步的效率，尽量少修改原本的数组中的内容

    private static final int N_BUCKET = 30;
    private static final int FIRST_BUCKET_SIZE = 8;

    public LockFreeVector() {
        buckets = new AtomicReferenceArray<AtomicReferenceArray<E>>(N_BUCKET);
        buckets.set(0, new AtomicReferenceArray<E>(FIRST_BUCKET_SIZE));
        descriptor = new AtomicReference<Descriptor<E>>(new Descriptor<E>(0, null));
    }



    static class Descriptor<E> {    //对writeDescriptor进行了封装，封装了当前Descriptor总的大小
        public int size;
        volatile WriteDescriptor<E> writeop;

        public Descriptor(int size, WriteDescriptor<E> writeop) {
            this.size = size;
            this.writeop = writeop;
        }
        public void completeWrite() {
            WriteDescriptor<E> tmpOp = writeop;
            if (tmpOp != null) {
                tmpOp.doIt();
                writeop = null;
            }
        }
    }
    private AtomicReference<Descriptor<E>> descriptor;  //每次都是一个Descriptor，因此这样声明能够使得此操作在多线程中有安全保证
    private static final int zeroNumFirst = Integer.numberOfLeadingZeros(FIRST_BUCKET_SIZE);

    static class WriteDescriptor<E> {   //写的时候的辅助参数
        public E oldV;
        public E newV;
        public AtomicReferenceArray<E> addr;
        public int addr_ind;

        public WriteDescriptor(AtomicReferenceArray<E> addr, int addr_ind, E oldV, E newV) {
            this.addr = addr;   //对某个元素进行操作，即目前所使用的那个一维数组，在二维数组中存储于
            this.addr_ind = addr_ind;
            this.oldV = oldV;
            this.newV = newV;
        }

        public void doIt() {
            addr.compareAndSet(addr_ind, oldV, newV);   //重试代码在另外的代码中实现
        }
    }


    //将数据压入Vector最后一个位置上
    public void push_back(E e) {
        Descriptor<E> desc;
        Descriptor<E> newd;;
        do {
            desc = descriptor.get();//将当前的descriptor取出，这个descriptor就是由下面的代码决定的
            desc.completeWrite();//将数据写入

            int pos = desc.size + FIRST_BUCKET_SIZE;//当前Vector的大小，与FIRST_BUCKET_SIZE相加得到pos
            int zeroNumPos = Integer.numberOfLeadingZeros(pos);
            int bucketInd = zeroNumFirst - zeroNumPos;//确定当前是二维数组中的哪一个一维数组，即二维数组本身的索引
            if (buckets.get(bucketInd) == null) {//对于第一次使用一个数组的情况，需要将前面的数组的长度得到后乘2，实现数组长度每次按倍数增长的效果
                int newLen = 2 * buckets.get(bucketInd - 1).length();
                if(debug)
                    System.out.println("New Length is:"+ newLen);
                buckets.compareAndSet(bucketInd, null, new AtomicReferenceArray<E>(newLen));
            }

            int idx = (0x80000000 >>> zeroNumPos) ^ pos;//获取pos在确定的一维数组中的哪一个位置上，即二维数组中具体的一位数组的索引，从而得到了二维数组的两个索引，确定一个位置

            newd = new Descriptor<E>(desc.size + 1, new WriteDescriptor<E>(buckets.get(bucketInd), idx,  null, e));
        } while (!descriptor.compareAndSet(desc, newd));
        descriptor.get().completeWrite();//重复完成写入的工作，这里是无所谓的，因为CAS只要成功就行了，失败也无伤大雅
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E get(int index) {
        int pos = index + FIRST_BUCKET_SIZE;
        int zeroNumPos = Integer.numberOfLeadingZeros(pos);
        int bucketInd = zeroNumFirst - zeroNumPos;
        int idx = (0x80000000 >>> zeroNumPos) ^ pos;
        return buckets.get(bucketInd).get(idx);
    }
}
