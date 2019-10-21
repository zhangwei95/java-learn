package behavior.iterator;

/**
 * 抽象聚合
 * 定义存储、添加、删除聚合对象以及创建迭代器对象的接口
 */
public interface Aggregate {
    void add(Object o);

    void remove(Object o);

    Iterator iterator();
}
