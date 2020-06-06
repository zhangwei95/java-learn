package interview_A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T05_LockCondition {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();
                for (Integer i : Constant.number){
                    c1.await();
                    System.out.println(i);
                    c2.signal();
                }
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        },"t1").start();

        new Thread(()->{
            try {
                lock.lock();
                for (char i : Constant.word){
                    System.out.println(i);
                    c1.signal();
                    c2.await();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
