package jmm;

import org.openjdk.jol.info.ClassLayout;


/**
 * ͨ��JOL����java����  64bits
 * ��Ҫ���� org.openjdk.jol�����
 *
 * -XX:-UseCompressedOops �ر�ָ��ѹ��  Ĭ�Ͽ���
 */
public class ObjectSize {
    static Object object ;
    /**
     * ƫ���� ������ ����mark word
     * Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
     * ��Ӧ��
     * [Oop(Ordinary Object Pointer), boolean, byte, char, short, int, float, long, double]��С
     * @param args
     */
//    public static void main(String[] args) throws InterruptedException {
//        object = new Object();
//        //˯��5000ms
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
//         * һ���ն����ڴ�ռ��16�ֽڣ�12 object header 4 �ֽڶ��루8�ֽڶ��룬java���� ������8�ֽڵ���������
//         * object header �������֣�
//         * mark word��8byte
//         * ������Ϣ��hashcode, gc��Ϣ
//         *
//         *
//         *
//         * klass pointer��4byteĬ�Ͽ���ָ��ѹ��  ��ѹ��Ϊ8byte
//         * ָ������Ԫ����   ->  .class
//         */
//        System.out.println("before hash");
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//
//        /*
//         * ���ģʽ����ָ���ݵĸ��ֽڱ������ڴ�ĵ͵�ַ�У������ݵĵ��ֽڱ������ڴ�ĸߵ�ַ�У������Ĵ洢ģʽ�е�������ڰ����ݵ����ַ���˳������ַ��С������ӣ������ݴӸ�λ����λ�ţ�������ǵ��Ķ�ϰ��һ�¡�
//         * С��ģʽ����ָ���ݵĸ��ֽڱ������ڴ�ĸߵ�ַ�У������ݵĵ��ֽڱ������ڴ�ĵ͵�ַ�У����ִ洢ģʽ����ַ�ĸߵͺ�����λȨ��Ч�ؽ���������ߵ�ַ����Ȩֵ�ߣ��͵�ַ����Ȩֵ�͡�
//         * һ�����������õĴ�ˣ������õ�С�ˣ�
//         */
//
//        //hashǰ mark word  00000001 00000000 00000000 00000000 00000000 00000000 00000000 00000000
//        //JVM �����hashcode ת��Ϊ16����
//        System.out.println("//������hashcode תΪ16���ƣ�");
//        //0x593634ad
////        System.out.println("jvm hashcode------------0x"+Integer.toHexString(object.hashCode()));
//        //hash�� mark word  00000001 10101101 00110100 00110110 01011001 00000000 00000000 00000000
//        //��������hashcode֮�����ǿ��Բ鿴����ͷ����Ϣ�仯
//        System.out.println("after hash");
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//
//        /*
//         * mark word��һ���ֽ�
//         * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 ����ƫ�����ӳ�
//         * jvm ��������ʱ����Ҫ������Դ����Щ�������ƫ����û���κ����壬�����˴���ƫ���������ĳɱ�������Ĭ�ϾͰ�ƫ�����ӳ���4000ms
//         * jdk 1.6֮�󣬹���ʹ��ƫ����������������jvm�����Ż��ģ���û�н�ֹƫ�����ӳٵ�����£�ʹ�õ���������������ֹƫ�����ӳٵĻ���ʹ�õ���ƫ����
//         *
//         * ����״̬һ����Ϊ����״̬ �� ����001 ��ƫ����101��������00��������10��GC���
//         * �����״̬ ��
//         * ����01 ��ƫ����01
//         * δʹ��      | GC�ִ����� |   ƫ������ʶ      | �����״̬
//         * unused:1bit |  age:4bit | biased_lock:1bit | lock:2bit
//         *
//         * ��������״̬00
//         * ptr_to_lock_record:62 | lock:2
//         * ָ��ջ������¼��ָ��    | �����״̬
//         *
//         * ��������
//         *
//         * ƫ����������״̬��ʾΪͬһ��״̬

            // �ڼ����identityHashCode ���޷�����ƫ��״̬
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
//         * jmm.SObject object internals:
//         *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
//         *       0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//         *       4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//         *       8     4                    (object header)                           d2 cb 00 f8 (11010010 11001011 00000000 11111000) (-134165550)
//         *      12     4                int jmm.SObject.aInt                              0
//         *      16     8               long jmm.SObject.aLong                             0
//         *      24     8             double jmm.SObject.aDouble                           0.0
//         *      32     4              float jmm.SObject.aFloat                            0.0
//         *      36     2               char jmm.SObject.aChar
//         *      38     2              short jmm.SObject.aShort                            0
//         *      40     1            boolean jmm.SObject.aBoolean                          false
//         *      41     1               byte jmm.SObject.aByte                             0
//         *      42     2                    (alignment/padding gap)
//         *      44     4   java.lang.Object jmm.SObject.oSize                             null
//         * Instance size: 48 bytes
//         * Space losses: 2 bytes internal + 0 bytes external = 2 bytes total
//         */
//        System.out.println(ClassLayout.parseInstance(new jmm.SObject()).toPrintable());
//
//    }

    /**
     * GC age
     * @param args
     */
//    public static void main(String[] args) throws InterruptedException {
//        object = new Object();
//        System.out.println("before gc");
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        for(int i =0; i < 17; i++){
//            System.gc();
//            Thread.sleep(3000);
//            System.out.println("gc "+i+" ��");
//            System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        }
//    }


    /**
     * �������� ����mark word
     * @param args
     * @throws InterruptedException
     */
//    public static void main(String[] args) throws InterruptedException {
//        object = new Object();
//        System.out.println("befor lock");//����01
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//
//
//        Thread t1 = new Thread(() -> {
//            synchronized (object) {
//                try {
//                    System.out.println("t1 lock ing 1");//��������00
//                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
//                    Thread.sleep(5000);
//                    System.out.println("t1 lock ing 2");//��������00
//                    System.out.println(ClassLayout.parseInstance(object).toPrintable());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//
//        t1.start();
//
//        Thread.sleep(3000);
//        sysn();
//        System.out.println("after lock");//��������10
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//
//
//        System.gc();
//        System.out.println("after gc");//����01  age + 1
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        System.gc();
//        System.out.println("after gc");//����01  age + 1
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        System.gc();
//        System.out.println("after gc");//����01  age + 1
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//        System.gc();
//        System.out.println("after gc");//����01  age + 1
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//    }

    public static void sysn(){
        synchronized (object){
            System.out.println("lock ing thread:"+Thread.currentThread().getName());//��������10
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

    public static void main(String[] args) {
        SObject object = new SObject();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
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
    /**����ָ��ѹ��4bytes  ������ָ��ѹ��8bytes*/
    private Object oSize;

}



