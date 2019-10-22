package behavior.visitor;

public class VisitPatternTest {
    public static void main(String[] args) {
        ObjectStructure os=new ObjectStructure();
        ConcreteElementA concreteElementA = new ConcreteElementA();
        os.add(concreteElementA);
        os.add(new ConcreteElementB());
        os.remove(concreteElementA);
        Visitor visitor=new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor=new ConcreteVisitorB();
        os.accept(visitor);
    }


}
