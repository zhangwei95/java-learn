package create.factorymethod;

public class ProductOneFactory implements AbstractFactory{
    @Override
    public ProductOne newProduct(){
        System.out.println("生产 产品1。。。");
        return new ProductOne();
    }
}
