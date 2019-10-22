package behavior.visitor;

/**
 * 具体访问者B
 * 实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么
 */
public class ConcreteVisitorB implements Visitor {

    @Override
    public void visit(ConcreteElementA elementA) {
        System.out.println("具体访问者B访问-->"+elementA.operateA());
    }

    @Override
    public void visit(ConcreteElementB elementB) {
        System.out.println("具体访问者B访问-->"+elementB.operateB());
    }
}
