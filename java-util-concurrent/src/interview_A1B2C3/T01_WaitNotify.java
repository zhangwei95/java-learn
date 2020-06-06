package interview_A1B2C3;

public class T01_WaitNotify {


    public static void main(String[] args) {
        final Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                for (char i : Constant.word) {
                    System.out.println(i);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {

                    }
                }
                o.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                for (Integer i : Constant.number) {
                    System.out.println(i);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                o.notify();
            }
        }).start();
    }
}
