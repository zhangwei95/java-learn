


/**
 * Double Checked Locking singleton
 * @author zhangwei
 */
public class DCLSingleton {
//    private static DCLSingleton instance = null;
    //禁止指令重排
    private volatile static DCLSingleton instance = null;


    private DCLSingleton() {

    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    //一般情况
                    //memory =allocate();       //1：分配对象的内存空间
                    //ctorInstance(memory);     //2：初始化对象
                    //instance =memory;         //3：设置instance指向刚分配的内存地址

                    //指令重排后
                    //memory =allocate();       //1：分配对象的内存空间
                    //instance =memory;         //3：设置instance指向刚分配的内存地址
                    //ctorInstance(memory);     //2：初始化对象

                    //A线程走到3时,B线程走check逻辑 这个时候返回的对象就有问题了
                    instance = new DCLSingleton();  //非原子操作
                }
            }
        }
        return instance;
    }
}
