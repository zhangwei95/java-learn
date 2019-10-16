package create.builder;

public class ConcreteBuilder extends Builder {
    @Override
    public void buildPartA() {
        product.setPartA("build A");
    }

    @Override
    public void buildPartB() {
        product.setPartB("build B");
    }

    @Override
    public void buildPartC() {
        product.setPartC("build C");
    }
}
