package behavior.reponsibility;

public class ChainOfReponsibilityPatternTest {
    public static void main(String[] args) {
        Handler h1=new ConcreteHandler1();
        Handler h2=new ConcreteHandler2();
        h1.setNext(h2);
        h1.handleRequest("two");
    }
}
