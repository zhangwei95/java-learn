package create.prototype;

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType obj1=new RealizeType();
        RealizeType obj2=(RealizeType)obj1.clone();
        System.out.println("obj1==obj2?"+(obj1==obj2));
    }
}
