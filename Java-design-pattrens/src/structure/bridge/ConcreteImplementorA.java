package structure.bridge;

/**
 * 具体实现者：给出实现化角色接口的具体实现
 */
public class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}
