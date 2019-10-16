package create.factorymethod;

public class AbstactFactoryTest {
    public static void main(String[] args) {
        AbstractFactory af = new ProductOneFactory();
        af.newProduct().show();
        af = new ProductTwoFactory();
        af.newProduct().show();
    }
}
