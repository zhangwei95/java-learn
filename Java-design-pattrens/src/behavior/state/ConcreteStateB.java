package behavior.state;

/**
 * 具体状态类
 * 实现抽象状态所对应的行为。
 */
public class ConcreteStateB extends State {
    @Override
    public void handle(Context context) {
        System.out.println("当前状态是 B.");
        context.setState(new ConcreteStateA());
    }
}
