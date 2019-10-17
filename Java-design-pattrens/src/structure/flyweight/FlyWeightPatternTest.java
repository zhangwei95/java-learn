package structure.flyweight;

public class FlyWeightPatternTest {
    public static void main(String[] args) {
        FlyWeightFactory factory=new FlyWeightFactory();
        FlyWeight f1 = factory.getFlyWeight("a");
        FlyWeight f2 = factory.getFlyWeight("a");
        FlyWeight f3 = factory.getFlyWeight("b");
        FlyWeight f4 = factory.getFlyWeight("c");
        FlyWeight f5 = factory.getFlyWeight("d");
        f1.operation(new UnShareConcreteFlyWeight("call a first"));
        f2.operation(new UnShareConcreteFlyWeight("call a second"));
        f3.operation(new UnShareConcreteFlyWeight("call b first"));
        f4.operation(new UnShareConcreteFlyWeight("call c first"));
        f5.operation(new UnShareConcreteFlyWeight("call d first"));
    }
}
