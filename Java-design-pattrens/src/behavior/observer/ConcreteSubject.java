package behavior.observer;

/**
 * 具体主题
 * 实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象
 */
public class ConcreteSubject extends Subject {


    @Override
    public void notifyObserver() {
        System.out.println("目标改变");
        observers.forEach(Observer::response);
    }
}
