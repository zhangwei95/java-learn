package jmm;


/**
 * Double Checked Locking singleton
 * @author zhangwei
 */
public class DCLSingleton {
//    private static jmm.DCLSingleton instance = null;

    private static Integer count = 10;
    //禁止指令重排
    private volatile static DCLSingleton instance = null;


    private DCLSingleton() {

    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    //一般情况
                    //memory =allocate();       //1：分配对象的内存空间 count = 0;
                    //ctorInstance(memory);     //2：初始化 count = 10
                    //instance =memory;         //3：设置instance指向刚分配的内存地址

                    //指令重排后
                    //memory =allocate();       //1：分配对象的内存空间 count = 0;
                    //instance =memory;         //3：设置instance指向刚分配的内存地址
                    //ctorInstance(memory);     //2：初始化 count = 10

                    //A线程走到3时,B线程走check逻辑 这个时候返回的对象未初始化完成
                    instance = new DCLSingleton();  //非原子操作
                }
            }
        }
        return instance;
    }
}
