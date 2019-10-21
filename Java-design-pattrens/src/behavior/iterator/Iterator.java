package behavior.iterator;

/**
 * 抽象迭代器
 * 定义访问和遍历聚合元素的接口，通常包含 hasNext()、first()、next() 等方法
 */
public interface Iterator {
    Object first();

    Object next();

    boolean hasNext();
}
