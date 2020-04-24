package create.prototype;

public class RealizeType implements Cloneable {
    RealizeType(){
        System.out.println("原型创建成功");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("原型复制成功");
        return super.clone();
    }
}
