package structure.adapter;

/**
 * 适配者
 */
public class Adaptee {
    /**
     * 适配方法
     */
    public void specificRequest(){
        System.out.println("适配者中的业务代码被调用！");
    }
}
