package structure.flyweight;

public class ConcreteFlyWeight implements  FlyWeight {
    private String key;
    ConcreteFlyWeight(String key) {
        this.key=key;
        System.out.println("具体享元"+key+"被创建！");
    }

    @Override
    public void operation(UnShareConcreteFlyWeight outState) {
        System.out.print("具体享元"+key+"被调用，");
        System.out.println("非享元信息是:"+outState.getInfo());
    }
}
