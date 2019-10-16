package create.factorymethod;

public class ProductTwoFactory implements AbstractFactory{
    @Override
    public ProductTwo newProduct(){
        System.out.println("生产 产品2。。。");
        return new ProductTwo();
    }
}
