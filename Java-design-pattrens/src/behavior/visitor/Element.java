package behavior.visitor;

/**
 * 抽象元素
 * 声明一个包含接受操作 accept() 的接口，被接受的访问者对象作为 accept() 方法的参数
 */
public interface Element {
    void accept(Visitor visitor);
}
