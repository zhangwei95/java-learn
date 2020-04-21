import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * test GC
 * -Xms60m -Xmx60m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
 * vmoption
 * gc type    gc原因         年代         回收前年轻代空间  回收后年轻代空间 回收前堆占用空间   回收后堆占用空间  回收时长         Linux 耗时   用户态  内核态  总
 * [GC (Allocation Failure) [PSYoungGen: 17189K----------->2552K(17920K)] 28773K------------>28512K(58880K), 0.0024852 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * gc type    gc原因              年代     回收前年轻代空间  回收后年轻代空间  回收前老年代空间  回收后老年代空间   GC前heap7807K  GC后 heap7549K   metaspace   回收情况
 * [Full GC (Allocation Failure) [PSYoungGen: 14336K------->14336K(17920K)] [ParOldGen: 40580K->40555K(40960K)] 54916K------->54892K(58880K), [Metaspace: 3467K->3467K(1056768K)], 0.0105787 secs] [Times: user=0.11 sys=0.03, real=0.01 secs]
 */
public class GCTest {
    private static final int M=1024*1024;
    public static void main(String[] args) {

        System.out.println("maxMemory:" + ( Runtime.getRuntime().maxMemory()/M) + "M");
        System.out.println("freeMemory:" + (Runtime.getRuntime().freeMemory()/M) + "M");
        System.out.println("totalMemory:" + (Runtime.getRuntime().totalMemory()/M) + "M");
        List linklist=new LinkedList<>();
        for(;;){
            linklist.add(new byte[M]);
        }

    }

    static class Obj {
        String obId;
        Obj(String obId){
            this.obId = obId;
        }
        @Override
        public int hashCode(){
            return 1;
        }
    }
}
