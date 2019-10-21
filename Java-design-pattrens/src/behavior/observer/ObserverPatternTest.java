package behavior.observer;

public class ObserverPatternTest {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer observer1=new ConcreteObserverOne();
        Observer observer2=new ConcreteObserverTwo();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.notifyObserver();
    }
}
