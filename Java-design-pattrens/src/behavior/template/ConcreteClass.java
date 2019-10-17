package behavior.template;

/**
 * 具体子类
 *
 */
public class ConcreteClass extends AbstractClass {
    @Override
    public void abstractMethod1() {
        System.out.println("抽象类方法1被调用");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象类方法2被调用");
    }
}
