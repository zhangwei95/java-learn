package behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题
 * 提供了一个用于保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法
 */
public abstract class Subject {
    protected List<Observer> observers= new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    /**
     * 通知观察者方法
     */

    public abstract void notifyObserver();

}
