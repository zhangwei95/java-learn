package behavior.state;

/**
 * 具体抽象类
 * 实现抽象状态所对应的行为。
 */
public class ConcreteStateA extends State {
    @Override
    public void handle(Context context) {
        System.out.println("当前状态是 A.");
        context.setState(new ConcreteStateB());
    }
}
