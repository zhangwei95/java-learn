package interview_A1B2C3;

import java.util.concurrent.locks.LockSupport;

public class T02_LockSupport {


    static Thread t1=null;
    static Thread t2=null;
    public static void main(String[] args) {


        t1 = new Thread(()->{
            for(Integer i : Constant.number){
                LockSupport.park();
                System.out.println(i);
                LockSupport.unpark(t2);
            }
            LockSupport.unpark(t2);
        });

        t2 = new Thread(()->{
            for(char i : Constant.word){
                System.out.println(i);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
            LockSupport.unpark(t1);
        });
        t1.start();t2.start();

    }
}
