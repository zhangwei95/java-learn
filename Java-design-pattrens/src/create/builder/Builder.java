package create.builder;

/**
 * 抽象建造者
 * 它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法 getResult()
 */
public abstract class Builder {
    protected  Product product=new Product();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();

    public Product getResult(){
        return product;
    }
}
