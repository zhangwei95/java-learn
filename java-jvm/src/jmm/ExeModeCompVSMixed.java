package jmm;

/**
 * 解释执行  编译执行  混合执行
 * -Xcomp的作用，开启编译模式 JIT  启动快（不需要编译），执行慢
 * -Xint 纯解析模式  启动慢（编译过程较慢），执行快
 * 默认模式  -Xmixed 混合模式  对热点代码进行编译  默认10000 编译  -XX:CompileThreshold=10000
 *      多次被调用的方法（方法计数器：监测方法执行频率）
 *      多次被调用的循环（循环计数器：监测循环执行频率）
 */

public class ExeModeCompVSMixed {
    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        for(int i=0; i<1000; i++)
            getMemoryInfo();
        System.out.println(System.currentTimeMillis() - t);
    }

    static String getMemoryInfo() {
        double pi = 3.14;

        for (long i = 0; i < 1_0000L; i++) {
           pi = 3.14/2.58;
           pi = 3.14;
           long t = Runtime.getRuntime().totalMemory();
           new ExeModeCompVSMixed();
        }

        return "";

    }
}
