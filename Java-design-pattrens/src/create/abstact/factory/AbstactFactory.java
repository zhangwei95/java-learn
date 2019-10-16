package create.abstact.factory;

import create.factorymethod.ProductOne;
import create.factorymethod.ProductTwo;

/**
 * 抽象工厂
 */
public interface AbstactFactory {
    public ProductOne newProductOne();

    public ProductTwo newProductTwo();
}
