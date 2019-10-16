package create.abstact.factory;

import create.factorymethod.ProductOne;
import create.factorymethod.ProductTwo;

public interface AbstactFactory {
    public ProductOne newProductOne();

    public ProductTwo newProductTwo();
}
