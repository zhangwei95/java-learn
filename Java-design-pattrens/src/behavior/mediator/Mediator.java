package behavior.mediator;

/**
 * 抽象中介者
 * 提供了同事对象注册与转发同事对象信息的抽象方法
 */
public abstract class Mediator {
    /**
     * 注册
     * @param colleague
     */
    public abstract void register(Colleague colleague);

    /**
     * 转发
     * @param colleague
     */
    public abstract void relay(Colleague colleague);
}
