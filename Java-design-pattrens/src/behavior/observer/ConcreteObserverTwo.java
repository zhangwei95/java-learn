package behavior.observer;

/**
 * 具体观察者
 *  实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态
 */
public class ConcreteObserverTwo implements Observer {

    @Override
    public void response() {
        System.out.println("观察者2 做出反应");
    }
}
