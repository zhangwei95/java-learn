package structure.adapter;


public class ClassAdapterTest {
    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        Target target = new Adapter();
        target.request();
    }
}
