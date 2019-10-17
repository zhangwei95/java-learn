package structure.flyweight;

/**
 * 非享元角色
 */
public class UnShareConcreteFlyWeight {
    private String info;
    UnShareConcreteFlyWeight(String info)
    {
        this.info=info;
    }
    public String getInfo()
    {
        return info;
    }
    public void setInfo(String info)
    {
        this.info=info;
    }
}
