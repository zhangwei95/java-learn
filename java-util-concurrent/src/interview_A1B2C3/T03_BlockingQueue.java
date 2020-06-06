package interview_A1B2C3;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class T03_BlockingQueue {

    public static void main(String[] args) {
        BlockingQueue q1 = new LinkedBlockingQueue(1);
        BlockingQueue q2 = new LinkedBlockingQueue(1);
        new Thread(()->{
            for (Integer i : Constant.number){
                try {
                    q1.take();
                    System.out.println(i);
                    q2.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                q2.put("ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            for (char i : Constant.word){
                try {
                    System.out.println(i);
                    q1.put(i);
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                q1.put("ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
