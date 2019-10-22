package behavior.visitor;

/**
 * 具体元素
 * 实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么
 */
public class ConcreteElementA implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operateA(){
        return "具体元素A的操作。";
    }
}
