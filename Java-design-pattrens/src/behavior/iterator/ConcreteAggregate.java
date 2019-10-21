package behavior.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚合
 * 实现抽象聚合类，返回一个具体迭代器的实例
 */
public class ConcreteAggregate implements Aggregate {

    private List<Object> list = new ArrayList<>();

    @Override
    public void add(Object o) {
        list.add(o);
    }

    @Override
    public void remove(Object o) {
        list.remove(o);
    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(list);
    }
}
