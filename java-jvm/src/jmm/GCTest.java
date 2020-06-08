package jmm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * test GC
 * -Xms60m -Xmx60m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
 * vmoption
 * gc type    gcԭ��         ���         ����ǰ������ռ�  ���պ�������ռ� ����ǰ��ռ�ÿռ�   ���պ��ռ�ÿռ�  ����ʱ��         Linux ��ʱ   �û�̬  �ں�̬  ��
 * [GC (Allocation Failure) [PSYoungGen: 17189K----------->2552K(17920K)] 28773K------------>28512K(58880K), 0.0024852 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * gc type    gcԭ��              ���     ����ǰ������ռ�  ���պ�������ռ�  ����ǰ������ռ�  ���պ�������ռ�   GCǰheap7807K  GC�� heap7549K   metaspace   �������
 * [Full GC (Allocation Failure) [PSYoungGen: 14336K------->14336K(17920K)] [ParOldGen: 40580K->40555K(40960K)] 54916K------->54892K(58880K), [Metaspace: 3467K->3467K(1056768K)], 0.0105787 secs] [Times: user=0.11 sys=0.03, real=0.01 secs]
 */
public class GCTest {
    private static final int M=1024*1024;
    public static void main(String[] args) {

        System.out.println("maxMemory:" + ( Runtime.getRuntime().maxMemory()/M) + "M");
        System.out.println("freeMemory:" + (Runtime.getRuntime().freeMemory()/M) + "M");
        System.out.println("totalMemory:" + (Runtime.getRuntime().totalMemory()/M) + "M");
        List linklist=new LinkedList<>();
        System.gc();
        for(;;){
            linklist.add(new byte[M]);
        }


    }
}
