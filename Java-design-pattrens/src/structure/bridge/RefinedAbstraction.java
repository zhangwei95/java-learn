package structure.bridge;

/**
 * 扩展抽象化角色：
 * 抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法
 */
public class RefinedAbstraction extends Abstraction {

    protected RefinedAbstraction(Implementor imple) {
        super(imple);
    }

    @Override
    public void operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问" );
        imple.operationImpl();
    }
}
