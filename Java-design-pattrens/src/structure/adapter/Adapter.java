package structure.adapter;

/**
 * 适配器
 */
public class Adapter extends Adaptee implements Target{
    /**
     * 方法适配
     */
    @Override
    public void request() {
        specificRequest();
    }
}
