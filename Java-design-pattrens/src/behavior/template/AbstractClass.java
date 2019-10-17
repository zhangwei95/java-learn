package behavior.template;

/**
 * 抽象类
 * 定义了一个公共接口，各种不同的算法以不同的方式实现这个接口，环境角色使用这个接口调用不同的算法，一般使用接口或抽象类实现。
 */
public abstract class AbstractClass {
    public void templateMethod(){
        specificMethod();
        abstractMethod1();
        abstractMethod2();
    }

    /**
     * 具体方法
     */
    public void specificMethod() {
        System.out.println("抽象类中的具体方法被调用...");
    }

    /**
     * 抽象方法1
     */
    public abstract void abstractMethod1();

    /**
     * 抽象方法2
     */
    public abstract void abstractMethod2();
}
