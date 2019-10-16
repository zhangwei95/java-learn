package create.abstact.factory;

import create.abstact.factory.AbstactFactory;

import create.factorymethod.Product;
import create.factorymethod.ProductOne;
import create.factorymethod.ProductTwo;

/**
 * 具体工厂
 */
public class AbstactFactoryImpl implements AbstactFactory {

    @Override
    public ProductOne newProductOne() {
        System.out.println("具体工厂 1 生成-->具体产品 11...");
        return new ProductOne();
    }

    @Override
    public ProductTwo newProductTwo() {
        System.out.println("具体工厂 2 生成-->具体产品 21...");
        return new ProductTwo();
    }
}
