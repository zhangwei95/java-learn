/**
 * 指令重排
 */
public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;
    public static void main(String[] args) throws InterruptedException {
        for (int i= 0;i<1000;i++){
            Thread one = new Thread(new Runnable() {
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();other.start();
            one.join();other.join();
            System.out.println("(" + x + "," + y + ")");
            x = 0; y = 0;
            a = 0; b = 0;
        }

//        a = 1;
//        x = b;
//        b = 1;
//        y = a;
        System.out.println("(" + x + "," + y + ")");
    }
}
