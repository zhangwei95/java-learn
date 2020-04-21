import org.openjdk.jol.info.ClassLayout;

/**
 * 通过JOL分析java对象  64bits
 * 需要依赖 org.openjdk.jol这个包
 */
public class ObjectSize {
    static Object object ;
    /**
     * 偏向锁 轻量锁 对象mark word
     * Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
     * 对应：
     * [Oop(Ordinary Object Pointer), boolean, byte, char, short, int, float, long, double]大小
     * @param args
     */
//    public static void main(String[] args) throws InterruptedException {
//        object = new Object();
//        //睡眠5000ms
////        Thread.sleep(5000);
//        /*
//         * java.lang.Object object internals:
//         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//         *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//         *      12     4        (loss due to the next object alignment)
//         * Instance size: 16 bytes
//         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
//         * 一个空对象内存占用16字节，12 object header 4 字节对齐（8字节对齐，java对象 必须是8字节的整数倍）
//         * object header 分两部分：
//         * mark word：8byte
//         * 锁的信息，hashcode, gc信息
//         *
//         *
//         *
//         * klass pointer：4byte默认开启指针压缩  不压缩为8byte
//         * 指向对象的元数据
//         */
//        System.out.println("before hash");
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//
//        /*
//         * 大端模式，是指数据的高字节保存在内存的低地址中，而数据的低字节保存在内存的高地址中，这样的存储模式有点儿类似于把数据当作字符串顺序处理：地址由小向大增加，而数据从高位往低位放；这和我们的阅读习惯一致。
//         * 小端模式，是指数据的高字节保存在内存的高地址中，而数据的低字节保存在内存的低地址中，这种存储模式将地址的高低和数据位权有效地结合起来，高地址部分权值高，低地址部分权值低。
//         * 一般在网络中用的大端；本地用的小端；
//         */
//
//        //hash前 mark word  00000001 00000000 00000000 00000000 00000000 00000000 00000000 00000000
//        //JVM 计算的hashcode 转换为16进制
//        System.out.println("//计算完hashcode 转为16进制：");
//        //0x593634ad
////        System.out.println("jvm hashcode------------0x"+Integer.toHexString(object.hashCode()));
//        //hash后 mark word  00000001 10101101 00110100 00110110 01011001 00000000 00000000 00000000
//        //当计算完hashcode之后，我们可以查看对象头的信息变化
//        System.out.println("after hash");
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//
//        /*
//         * mark word第一个字节
//         * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 禁用偏向锁延迟
//         * jvm 在启动的时候需要加载资源，这些对象加上偏向锁没有任何意义，减少了大量偏向锁撤销的成本；所以默认就把偏向锁延迟了4000ms
//         * jdk 1.6之后，关于使用偏向锁和轻量级锁，jvm是有优化的，在没有禁止偏向锁延迟的情况下，使用的是轻量级锁；禁止偏向锁延迟的话，使用的是偏向锁
//         *
//         * 对象状态一共分为五种状态 ： 无锁001 、偏向锁101、轻量锁00、重量锁、GC标记
//         * 对象的状态 ：
//         * 无锁01 、偏向锁01
//         * 未使用      | GC分代年龄 |   偏向锁标识      | 对象的状态
//         * unused:1bit |  age:4bit | biased_lock:1bit | lock:2bit
//         *
//         * 轻量锁：状态00
//         * ptr_to_lock_record:62 | lock:2
//         * 指向栈中锁记录的指针    | 对象的状态
//         *
//         * 重量锁：
//         *
//         * 偏向锁和无锁状态表示为同一个状态
//         */
//        //00000001
//        System.out.println("before lock thread:"+Thread.currentThread().getName());
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        //11010000
//        sysn();
//        //00000001
//        System.out.println("after lock thread:"+Thread.currentThread().getName());
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        /*
//         * SObject object internals:
//         *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
//         *       0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//         *       4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//         *       8     4                    (object header)                           d2 cb 00 f8 (11010010 11001011 00000000 11111000) (-134165550)
//         *      12     4                int SObject.aInt                              0
//         *      16     8               long SObject.aLong                             0
//         *      24     8             double SObject.aDouble                           0.0
//         *      32     4              float SObject.aFloat                            0.0
//         *      36     2               char SObject.aChar
//         *      38     2              short SObject.aShort                            0
//         *      40     1            boolean SObject.aBoolean                          false
//         *      41     1               byte SObject.aByte                             0
//         *      42     2                    (alignment/padding gap)
//         *      44     4   java.lang.Object SObject.oSize                             null
//         * Instance size: 48 bytes
//         * Space losses: 2 bytes internal + 0 bytes external = 2 bytes total
//         */
//        System.out.println(ClassLayout.parseInstance(new SObject()).toPrintable());
//
//    }


    /**
     * 重量级锁 对象mark word
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        object = new Object();
        System.out.println("befor lock");//无锁01
        System.out.println(ClassLayout.parseInstance(object).toPrintable());


        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        t1.start();
        System.out.println("t1 lock ing");//轻量级锁00
        System.out.println(ClassLayout.parseInstance(object).toPrintable());


        sysn();


        System.out.println("after lock");//重量级锁10
        System.out.println(ClassLayout.parseInstance(object).toPrintable());


        System.gc();
        System.out.println("after gc");//无锁01  age + 1
        System.out.println(ClassLayout.parseInstance(object).toPrintable());

    }

    public static void sysn(){
        synchronized (object){
            System.out.println("lock ing thread:"+Thread.currentThread().getName());//重量级锁10
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}

class SObject{
    /**1bytes*/
    private boolean aBoolean;
    /**1bytes*/
    private byte aByte;
    /**2bytes*/
    private char aChar;
    /**2bytes*/
    private short aShort;
    /**4bytes*/
    private int aInt;
    /**4bytes*/
    private float aFloat;
    /**8bytes*/
    private long aLong;
    /**8bytes*/
    private double aDouble;
    /**4bytes*/
    private Object oSize;

}



