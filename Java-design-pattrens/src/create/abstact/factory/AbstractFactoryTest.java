package create.abstact.factory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstactFactory af=new AbstactFactoryImpl();
        af.newProductOne();
        af.newProductTwo();
    }
}
