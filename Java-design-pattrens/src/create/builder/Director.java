package create.builder;

/**
 * 指挥者
 * 它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息
 */
public class Director {
    private Builder builder;
    public Director(Builder builder){
        this.builder=builder;
    }
    public Product construct(){
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}
